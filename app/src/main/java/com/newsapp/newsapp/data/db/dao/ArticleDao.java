package com.newsapp.newsapp.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.newsapp.newsapp.data.db.entity.ArticleEntity;
import io.reactivex.Single;
import java.util.List;

@Dao public abstract class ArticleDao {

  @Transaction
  public boolean upsert(ArticleEntity article) {
    delete(article);
    insert(article);
    return true;
  }

  @Insert
  public abstract void insert(ArticleEntity article);

  @Delete
  public abstract void delete(ArticleEntity articleEntity);

  @Query("select * from ArticleEntity")
  public abstract Single<List<ArticleEntity>> getArticles();


  @Query("delete from ArticleEntity")
  public abstract void clearAll();


}
