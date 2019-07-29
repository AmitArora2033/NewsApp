package com.newsapp.newsapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.newsapp.newsapp.data.DataManager;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.ui.module.headlines.HeadlineViewModel;
import com.newsapp.newsapp.ui.module.headlines.NewsViewState;
import com.newsapp.newsapp.util.RxSingleSchedulers;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class NewsViewModelUnitTest {

  @Rule
  public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

  @Mock DataManager dataManager;
  @Mock androidx.lifecycle.Observer observer;
  private HeadlineViewModel viewModel;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    viewModel = new HeadlineViewModel(dataManager, RxSingleSchedulers.TEST_SCHEDULER);
    viewModel.getNewsMutableLiveData().observeForever(observer);
  }

  @Test public void testNull() {
    when(dataManager.getNewsData("in", 1, true)).thenReturn(null);
    assertNotNull(viewModel.getNewsMutableLiveData());
    assertTrue(viewModel.getNewsMutableLiveData().hasObservers());
  }

  @Test public void testApiCallSuccess() {
    ArrayList<News.Article> articles = new ArrayList<>();
    articles.add(
        News.Article.create("ABC", "ABC", "Soccer", "Match today", null, null, new Date().getTime(),
            "some content"));

    when(dataManager.getNewsData("in", 1, true)).thenReturn(
        Single.just(News.create(true, 20, articles, null)));
    viewModel.getNewsFeed("in", 1, true);
    //verify(observer).onChanged(NewsViewState.LOADING_STATE);
    verify(observer).onChanged(NewsViewState.SUCCESS_STATE);
  }

  @Test public void testAPICallError() {
    when(dataManager.getNewsData("in", 1, true)).thenReturn(
        Single.just(News.create(false, 0, null, "Error Occured in API Call")));
    viewModel.getNewsFeed("in", 1, true);
    verify(observer).onChanged(NewsViewState.FAILED_STATE);
  }

  @Test public void testLocalDbCallSuccess() {
    ArrayList<News.Article> articles = new ArrayList<>();
    articles.add(
        News.Article.create("ABC", "ABC", "Soccer", "Match today", null, null, new Date().getTime(),
            "some content"));

    when(dataManager.getNewsData("in", 1, false)).thenReturn(
        Single.just(News.create(true, 20, articles, null)));
    viewModel.getNewsFeed("in", 1, false);
    verify(observer).onChanged(NewsViewState.SUCCESS_STATE);
  }

  @Test public void testLocalDbCallError() {
    when(dataManager.getNewsData("in", 1, false)).thenReturn(
        Single.just(News.create(false, 0, null, "Error Occured in API Call")));
    viewModel.getNewsFeed("in", 1, false);
    verify(observer).onChanged(NewsViewState.FAILED_STATE);
  }

  @After
  public void tearDown() throws Exception {
    dataManager = null;
    viewModel = null;
  }
}
