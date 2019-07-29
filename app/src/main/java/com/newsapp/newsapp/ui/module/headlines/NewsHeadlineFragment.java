package com.newsapp.newsapp.ui.module.headlines;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.newsapp.mylibrary.BaseFragment;
import com.newsapp.mylibrary.ViewModelFactory;
import com.newsapp.newsapp.App;
import com.newsapp.newsapp.R;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.ui.module.headlines.adapter.NewsAdapter;
import com.newsapp.newsapp.ui.module.home.HomeActivity;
import com.newsapp.newsapp.ui.module.newsdetail.NewsDetailFragment;
import com.newsapp.newsapp.util.NetworkManager;
import com.newsapp.newsapp.util.PageScrollListener;
import javax.inject.Inject;

public class NewsHeadlineFragment extends BaseFragment
    implements NewsAdapter.NewsItemSelectedListener {

  private static final String COUNTRY = "in";
  private static final String TAG = NewsHeadlineFragment.class.getSimpleName();
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  @BindView(R.id.tv_error) TextView tvError;
  @Inject ViewModelFactory viewModelFactory;
  private int pageNo = 1;
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
    viewModel.getNewsMutableLiveData().observe(this, newsViewState -> {
      isLoading = false;

      switch (newsViewState.getCurrentState()) {
        case LOADING:
          Log.d(TAG, "Loading");
          break;
        case FAILED:
          Log.e(TAG, "Failed " + newsViewState.getError());
          recyclerView.setVisibility(View.GONE);
          tvError.setVisibility(View.VISIBLE);
          tvError.setText(newsViewState.getError());
          break;
        case SUCCESS:
          recyclerView.setVisibility(View.VISIBLE);
          tvError.setVisibility(View.GONE);

          if (adapter != null && newsViewState.getData() != null && newsViewState.getData()
              .isSuccess()) {
            this.news = newsViewState.getData();
            if (news.articles().size() > 0) {
              adapter.addData(news.articles());
            }
          }
          break;
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
