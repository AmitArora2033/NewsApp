package com.newsapp.newsapp.remote;

import android.util.Log;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.model.newsresponse.dto.newsresponse.Article;
import com.newsapp.newsapp.util.AppConstants;
import com.newsapp.newsapp.util.DateUtil;
import io.reactivex.Single;
import java.text.ParseException;
import java.util.ArrayList;

public class Repository {

  private static final String TAG = Repository.class.getSimpleName();
  private final RemoteService remoteService;

  public Repository(RemoteService remoteService) {
    this.remoteService = remoteService;
  }

  public Single<News> getNewsHeadlines(String country) {
    return remoteService.getNewsHeadlines(country, AppConstants.API_KEY).map(newsResponse -> {
      News news = null;
      if (newsResponse.getStatus().equals("ok")) {
        ArrayList<News.Article> articles = new ArrayList<>();

        long date = 0;
        for (Article article : newsResponse.getArticles()) {
          try {
            date = DateUtil.parseDate(article.getPublishedAt());
          } catch (ParseException e) {
            Log.e(TAG, e.toString());
          }

          articles.add(News.Article.create(article.getSource().getName(), article.getAuthor(),
              article.getTitle(), article.getDescription(), article.getUrl(),
              article.getUrlToImage(), date,
              article.getContent()));
        }
        news = News.create(true, newsResponse.getTotalResults(), articles, "");
      } else {
        news = News.create(false, 0, null, newsResponse.getErrorMessage());
      }
      return news;
    });
  }
}
