package com.newsapp.newsapp.data.db;

import android.util.Log;
import com.newsapp.newsapp.data.db.entity.ArticleEntity;
import com.newsapp.newsapp.model.mapper.NewsMapper;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;

public class LocalDatabase {

  private static final String TAG = LocalDatabase.class.getSimpleName();
  private final AppDatabase appDataBase;

  public LocalDatabase(AppDatabase appDatabase) {
    this.appDataBase = appDatabase;
  }

  public Single<News> getNewsFromLocalDb() {
    return appDataBase.articleDao().getArticles()
        .map(NewsMapper.mapEntityArticleToDomainArticle()).map((articles -> {
          if (articles != null && articles.size() > 0) {
            return News.create(true, articles.size(), (ArrayList<News.Article>) articles, "");
          } else {
            Log.d(TAG, "ARTICLES NULL IN DB");
            return News.create(false, 0, new ArrayList<>(), "Failed to Refresh Feeds");
          }
        }));
  }

  public void clearData() {
    appDataBase.articleDao().clearAll();
  }

  public void insertArticlesToDb(List<News.Article> articles) {
    for (News.Article article : articles) {
      appDataBase.articleDao()
          .insert(
              new ArticleEntity(article.sourceName(), article.author(), article.title(),
                  article.description(), article.url(), article.imageURL(),
                  article.publishedAt(), article.content(), System.currentTimeMillis()));
    }
  }
}
