package com.newsapp.newsapp.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity public class ArticleEntity {

  public static final String SOURCE_NAME = "source_name";
  public static final String AUTHOR = "author";
  public static final String TITLE = "title";
  private static final String DESCRIPTION = "description";
  private static final String URL = "url";
  private static final String IMAGE_URL = "image_url";
  public static final String PUBLISHED_AT = "published_at";
  private static final String CONTENT = "content";
  private static final String LAST_UPDATED = "last_updated";

  @PrimaryKey(autoGenerate = true) public long id;

  @ColumnInfo(name = SOURCE_NAME) public String sourceName;
  @ColumnInfo(name = AUTHOR) public String author;
  @ColumnInfo(name = TITLE) public String title;
  @ColumnInfo(name = DESCRIPTION) public String description;
  @ColumnInfo(name = URL) public String url;
  @ColumnInfo(name = IMAGE_URL) public String imageURL;
  @ColumnInfo(name = PUBLISHED_AT) public long publishedAt;
  @ColumnInfo(name = CONTENT) public String content;
  @ColumnInfo(name = LAST_UPDATED) public long lastUpdated;

  public ArticleEntity(String sourceName, String author, String title, String description, String url,
      String imageURL, long publishedAt, String content,long lastUpdated) {
    this.sourceName = sourceName;
    this.author = author;
    this.title = title;
    this.description = description;
    this.url = url;
    this.imageURL = imageURL;
    this.publishedAt = publishedAt;
    this.content = content;
    this.lastUpdated = lastUpdated;
  }
}
