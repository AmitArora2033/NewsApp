package com.newsapp.newsapp;

import android.util.Log;
import com.newsapp.newsapp.db.AppDatabase;
import com.newsapp.newsapp.mapper.NewsMapper;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.remote.Repository;
import io.reactivex.Single;
import java.util.ArrayList;

public class DataManager {

  private static final String TAG = DataManager.class.getSimpleName();
  private final Repository repository;
  private final AppDatabase appDataBase;

  public DataManager(Repository repository, AppDatabase appDatabase) {
    this.repository = repository;
    this.appDataBase = appDatabase;
  }

  public Single<News> getNewsData(String country, int pageNo, boolean isOnline) {

    if (isOnline) {
      return repository.getNewsHeadlines(country, pageNo);
    } else {
      return appDataBase.articleDao().getArticles()
          .map(NewsMapper.mapEntityArticleToDomainArticle()).map((articles -> {
            if (articles != null) {
              return News.create(true, articles.size(), (ArrayList<News.Article>) articles, "");
            } else {
              Log.d(TAG, "ARTICLES NULL IN DB");
              return News.create(false, 0, new ArrayList<>(), "Article is null");
            }
          }));
    }
  }
}
