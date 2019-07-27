package com.newsapp.newsapp.di;

import com.newsapp.newsapp.App;
import com.newsapp.newsapp.module.headlines.NewsHeadlineFragment;
import com.newsapp.newsapp.module.home.HomeActivity;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { AppModule.class })
public interface AppComponent {

  void inject(App app);

  void inject(HomeActivity mainActivity);

  void inject(NewsHeadlineFragment newsHeadlineFragment);

  @Component.Builder interface Builder {

    @BindsInstance Builder application(App application);

    AppComponent build();
  }
}
