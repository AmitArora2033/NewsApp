package com.newsapp.newsapp.di;

import androidx.lifecycle.ViewModel;
import com.newsapp.newsapp.di.anotation.ViewModelKey;
import com.newsapp.newsapp.module.home.HomeViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module public abstract class ViewModelModule {

  @Binds @IntoMap @ViewModelKey(HomeViewModel.class)
  abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);
}
