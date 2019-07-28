package com.newsapp.newsapp.mapper;

import android.util.Log;
import com.newsapp.newsapp.db.entity.ArticleEntity;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.model.newsresponse.dto.newsresponse.Article;
import com.newsapp.newsapp.model.newsresponse.dto.newsresponse.NewsResponse;
import com.newsapp.newsapp.util.DateUtil;
import io.reactivex.functions.Function;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NewsMapper {

  private static final String TAG = NewsMapper.class.getSimpleName();

  public static Function<NewsResponse, News> mapDtoNewsToDomain() {
    return newsResponse -> {
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
    };
  }

  public static Function<List<ArticleEntity>, List<News.Article>> mapEntityArticleToDomainArticle() {
    return articleEntities -> {

      List<News.Article> articleList = new ArrayList<>();
      for (ArticleEntity articleEntity : articleEntities) {
        articleList.add(News.Article.create(articleEntity.sourceName, articleEntity.author,
            articleEntity.title, articleEntity.description, articleEntity.url,
            articleEntity.imageURL, articleEntity.publishedAt, articleEntity.content));
      }
      return articleList;
    };
  }
}
