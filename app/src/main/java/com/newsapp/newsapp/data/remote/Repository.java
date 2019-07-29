package com.newsapp.newsapp.data.remote;

import com.newsapp.newsapp.data.db.LocalDatabase;
import com.newsapp.newsapp.model.mapper.NewsMapper;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.util.AppConstants;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class Repository {

  private static final String TAG = Repository.class.getSimpleName();
  private final RemoteService remoteService;
  private final LocalDatabase localDatabase;

  public Repository(RemoteService remoteService, LocalDatabase localDatabase) {
    this.remoteService = remoteService;
    this.localDatabase = localDatabase;
  }

  public Single<News> getNewsHeadlines(String country, int pageNo) {

    return remoteService.getNewsHeadlines(country, pageNo, AppConstants.API_KEY)
        .subscribeOn(Schedulers.io())
        .map(NewsMapper.mapDtoNewsToDomain()).map(news -> {
          if (news.isSuccess() && pageNo == 1) {
            localDatabase.clearData();
          }
          localDatabase.insertArticlesToDb(news.articles());
          return news;
        });
  }
}
