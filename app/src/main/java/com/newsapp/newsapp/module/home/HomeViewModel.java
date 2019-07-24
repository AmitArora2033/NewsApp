package com.newsapp.newsapp.module.home;

import com.newsapp.mylibrary.BaseViewModel;
import com.newsapp.newsapp.remote.Repository;
import javax.inject.Inject;

public class HomeViewModel extends BaseViewModel {

  private final Repository repository;

  @Inject public HomeViewModel(Repository repository) {
    this.repository = repository;
  }
}
