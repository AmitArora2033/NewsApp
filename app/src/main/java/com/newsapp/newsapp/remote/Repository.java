package com.newsapp.newsapp.remote;

import com.newsapp.newsapp.remote.RemoteService;

public class Repository {

  private final RemoteService remoteService;

  public Repository(RemoteService remoteService) {
    this.remoteService = remoteService;
  }
}
