package com.newsapp.newsapp.ui.module.headlines;

import com.newsapp.mylibrary.BaseViewState;
import com.newsapp.newsapp.model.newsresponse.domain.News;

public class NewsViewState extends BaseViewState<News> {

  public static final NewsViewState SUCCESS_STATE = new NewsViewState(null, State.SUCCESS, null);
  public static final NewsViewState LOADING_STATE = new NewsViewState(null, State.LOADING, null);
  public static final NewsViewState FAILED_STATE = new NewsViewState(null, State.FAILED, null);

  private NewsViewState(News data, State currentState, String error) {
    this.data = data;
    this.currentState = currentState;
    this.error = error;
  }
}
