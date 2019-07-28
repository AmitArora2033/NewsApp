package com.newsapp.newsapp.module.headlines;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.newsapp.mylibrary.BaseViewModel;
import com.newsapp.newsapp.DataManager;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

public class HeadlineViewModel extends BaseViewModel {

  private static final String TAG = HeadlineViewModel.class.getSimpleName();
  private final DataManager dataManager;
  MutableLiveData<News> newsMutableLiveData = new MutableLiveData<>();
  private Disposable disposable;


  @Inject public HeadlineViewModel(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  public MutableLiveData<News> getNewsMutableLiveData() {
    return newsMutableLiveData;
  }

  public void getNewsFeed(String country, int pageNo, boolean isOnline) {

    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }

    disposable = dataManager.getNewsData(country, pageNo, isOnline)
        .compose(appSingleTransformer())
        .subscribe((news) -> newsMutableLiveData.postValue(news),
            throwable -> Log.e(TAG, throwable.getMessage()));
    addCompositeDisposable(disposable);
  }
}
