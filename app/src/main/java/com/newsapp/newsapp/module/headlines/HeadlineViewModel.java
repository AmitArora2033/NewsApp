package com.newsapp.newsapp.module.headlines;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.newsapp.mylibrary.BaseViewModel;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.remote.Repository;
import javax.inject.Inject;

public class HeadlineViewModel extends BaseViewModel {

  private static final String TAG = HeadlineViewModel.class.getSimpleName();
  private final Repository repository;
  MutableLiveData<News> newsMutableLiveData = new MutableLiveData<>();

  @Inject public HeadlineViewModel(Repository repository) {
    this.repository = repository;
  }

  public MutableLiveData<News> getNewsMutableLiveData() {
    return newsMutableLiveData;
  }

  public void getNewsFeed(String country) {
    addCompositeDisposable(repository.getNewsHeadlines(country)
        .compose(appSingleTransformer())
        .subscribe((news) -> newsMutableLiveData.postValue(news),
            throwable -> Log.e(TAG, throwable.getMessage())));
  }
}
