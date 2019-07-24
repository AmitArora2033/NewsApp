package com.newsapp.newsapp;

import android.app.Application;
import android.content.Context;
import com.newsapp.newsapp.di.AppComponent;
import com.newsapp.newsapp.di.DaggerAppComponent;

public class App extends Application {

  public AppComponent appComponent;

  public static App get(Context context) {
    return (App) context.getApplicationContext();
  }

  public AppComponent getComponent() {
    return appComponent;
  }

  @Override public void onCreate() {
    super.onCreate();
    appComponent = DaggerAppComponent.builder().application(this).build();

    appComponent.inject(this);

  }
}
