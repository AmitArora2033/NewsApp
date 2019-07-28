package com.newsapp.newsapp;

import com.newsapp.newsapp.remote.Repository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class) public class NewsViewModelUnitTest {

  @Mock Repository repository;
  @Mock DataManager dataManager;
}
