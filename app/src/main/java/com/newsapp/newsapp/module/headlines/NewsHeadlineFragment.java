package com.newsapp.newsapp.module.headlines;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.newsapp.mylibrary.BaseFragment;
import com.newsapp.mylibrary.ViewModelFactory;
import com.newsapp.newsapp.App;
import com.newsapp.newsapp.R;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.module.home.HomeActivity;
import com.newsapp.newsapp.module.home.adapter.NewsAdapter;
import com.newsapp.newsapp.module.newsdetail.NewsDetailFragment;
import com.newsapp.newsapp.util.NetworkManager;
import com.newsapp.newsapp.util.PageScrollListener;
import javax.inject.Inject;

public class NewsHeadlineFragment extends BaseFragment
    implements NewsAdapter.NewsItemSelectedListener {

  private static final String COUNTRY = "in";
  private static int pageNo = 1;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;

  @Inject ViewModelFactory viewModelFactory;
  private HeadlineViewModel viewModel;
  private NewsAdapter adapter;
  private News news;
  private boolean isLoading;

  public static NewsHeadlineFragment newInstance() {
    Bundle args = new Bundle();
    NewsHeadlineFragment fragment = new NewsHeadlineFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void initDataCalls() {
    viewModel.getNewsFeed(COUNTRY, pageNo, NetworkManager.isNetworkAvailable(getContext()));
  }

  @Override protected void initObservers() {
    viewModel.getNewsMutableLiveData().observe(this, news -> {
      isLoading = false;
      if (adapter != null && news != null && news.isSuccess()) {
        this.news = news;
        if (news.articles().size() > 0) {
          adapter.addData(news.articles());
        }
      }
    });
  }

  @Override protected void initViewModels() {
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(HeadlineViewModel.class);
  }

  @Override public int getContentLayout() {
    return R.layout.fragment_headlines;
  }

  @Override protected void initDependencies() {
    App.get(getContext()).getComponent().inject(this);
    adapter = new NewsAdapter(this);
  }

  @Override protected void onReady(Bundle savedInstanceState) {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);
    recyclerView.addOnScrollListener(new PageScrollListener() {
      @Override protected void loadMoreItems() {
        isLoading = true;
        viewModel.getNewsFeed(COUNTRY, ++pageNo, NetworkManager.isNetworkAvailable(getContext()));
      }

      @Override protected long getTotalItems() {
        return news != null ? news.totalItems() : 0;
      }

      @Override protected boolean isLoading() {
        return isLoading;
      }
    });
  }

  @Override public void onNewsItemSelected(int selectedItemPosition) {
    ((HomeActivity) getActivity()).replaceFragment(
        NewsDetailFragment.newInstance(adapter.getNewsArticleData().get(selectedItemPosition)));
  }
}
