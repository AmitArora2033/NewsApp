package com.newsapp.newsapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.newsapp.newsapp.db.dao.ArticleDao;
import com.newsapp.newsapp.db.entity.ArticleEntity;

@Database(entities = { ArticleEntity.class }, version = 1, exportSchema = false)
public abstract class AppDatabase extends
    RoomDatabase {

  public abstract ArticleDao articleDao();
}
