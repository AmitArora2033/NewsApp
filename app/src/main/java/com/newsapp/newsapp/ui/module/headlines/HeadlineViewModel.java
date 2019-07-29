package com.newsapp.newsapp.ui.module.headlines;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.newsapp.mylibrary.BaseViewModel;
import com.newsapp.newsapp.data.DataManager;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.util.RxSingleSchedulers;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

public class HeadlineViewModel extends BaseViewModel {

  private static final String TAG = HeadlineViewModel.class.getSimpleName();
  private final DataManager dataManager;
  private final RxSingleSchedulers rxSingleSchedulers;
  MutableLiveData<NewsViewState> newsMutableLiveData = new MutableLiveData<>();
  private Disposable disposable;

  @Inject public HeadlineViewModel(DataManager dataManager, RxSingleSchedulers rxSingleSchedulers) {
    this.dataManager = dataManager;
    this.rxSingleSchedulers = rxSingleSchedulers;
  }

  public MutableLiveData<NewsViewState> getNewsMutableLiveData() {
    return newsMutableLiveData;
  }

  public void getNewsFeed(String country, int pageNo, boolean isOnline) {

    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }

    disposable = dataManager.getNewsData(country, pageNo, isOnline)
        .compose(rxSingleSchedulers.applySchedulers())
        .subscribe((news) ->
        {
          if (news != null) {
            if (news.isSuccess()) {
              onSuccess(news);
            } else {
              onFailed(news);
            }
          } else {
            onError(new Throwable("Null Response From Server"));
          }
        }, throwable -> {
          onError(throwable);
        });

    addCompositeDisposable(disposable);
  }

  public void onSuccess(News news) {
    NewsViewState.SUCCESS_STATE.setData(news);
    newsMutableLiveData.postValue(NewsViewState.SUCCESS_STATE);
  }

  public void onLoading(News news) {
    newsMutableLiveData.postValue(NewsViewState.LOADING_STATE);
  }

  public void onFailed(News news) {
    NewsViewState.FAILED_STATE.setError(news.errorMessage());
    newsMutableLiveData.postValue(NewsViewState.FAILED_STATE);
  }

  public void onError(Throwable throwable) {
    Log.e(TAG, throwable.toString());
    NewsViewState.FAILED_STATE.setError("Unknown Error Occurred!");
    newsMutableLiveData.postValue(NewsViewState.FAILED_STATE);
  }
}
