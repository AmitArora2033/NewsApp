package com.newsapp.newsapp.di;

import com.newsapp.newsapp.App;
import com.newsapp.newsapp.ui.module.headlines.NewsHeadlineFragment;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { AppModule.class })
public interface AppComponent {

  void inject(App app);

  void inject(NewsHeadlineFragment newsHeadlineFragment);

  @Component.Builder interface Builder {

    @BindsInstance Builder application(App application);

    AppComponent build();
  }
}
