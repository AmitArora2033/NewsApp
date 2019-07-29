package com.newsapp.newsapp.data;

import com.newsapp.newsapp.data.db.LocalDatabase;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.data.remote.Repository;
import io.reactivex.Single;

public class DataManager {

  private static final String TAG = DataManager.class.getSimpleName();
  private final Repository repository;
  private final LocalDatabase localDataBase;

  public DataManager(Repository repository, LocalDatabase localDatabase) {
    this.repository = repository;
    this.localDataBase = localDatabase;
  }

  public Single<News> getNewsData(String country, int pageNo, boolean isOnline) {

    if (isOnline) {
      return repository.getNewsHeadlines(country, pageNo);
    } else {
      return localDataBase.getNewsFromLocalDb();
    }
  }





}
