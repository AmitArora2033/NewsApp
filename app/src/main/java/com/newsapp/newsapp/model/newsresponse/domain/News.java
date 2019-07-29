package com.newsapp.newsapp.model.newsresponse.domain;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;

@AutoValue public abstract class News implements Parcelable {

  public static News create(boolean isSuccess, long totalItems, ArrayList<Article> articles,
      String errorMessage) {
    return new AutoValue_News(isSuccess, totalItems, articles, errorMessage);
  }

  public abstract boolean isSuccess();

  public abstract long totalItems();

  @Nullable public abstract ArrayList<Article> articles();

  @Nullable public abstract String errorMessage();

  @AutoValue public abstract static class Article implements Parcelable {

    public static Article create(String sourceName, String author, String title, String description,
        String url, String imageURL, long publishedAt, String content) {
      return new AutoValue_News_Article(sourceName, author, title, description, url, imageURL,
          publishedAt, content);
    }

    public abstract String sourceName();

    @Nullable public abstract String author();

    public abstract String title();

    @Nullable public abstract String description();

    @Nullable public abstract String url();

    @Nullable public abstract String imageURL();

    @Nullable public abstract Long publishedAt();

    @Nullable public abstract String content();
  }
}
