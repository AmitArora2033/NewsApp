package com.newsapp.newsapp.remote;

import com.newsapp.newsapp.db.AppDatabase;
import com.newsapp.newsapp.db.entity.ArticleEntity;
import com.newsapp.newsapp.mapper.NewsMapper;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.util.AppConstants;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class Repository {

  private static final String TAG = Repository.class.getSimpleName();
  private final RemoteService remoteService;
  private final AppDatabase appDataBase;

  public Repository(RemoteService remoteService, AppDatabase appDatabase) {
    this.remoteService = remoteService;
    this.appDataBase = appDatabase;
  }

  public Single<News> getNewsHeadlines(String country, int pageNo) {

    return remoteService.getNewsHeadlines(country, pageNo, AppConstants.API_KEY)
        .subscribeOn(Schedulers.io())
        .map(NewsMapper.mapDtoNewsToDomain()).map(news -> {
          if (news.isSuccess() && pageNo == 1) {
            appDataBase.articleDao().clearAll();
          }
          for (News.Article article : news.articles()) {
            appDataBase.articleDao()
                .insert(
                    new ArticleEntity(article.sourceName(), article.author(), article.title(),
                        article.description(), article.url(), article.imageURL(),
                        article.publishedAt(), article.content(), System.currentTimeMillis()));
          }
          return news;
        });
  }
}
