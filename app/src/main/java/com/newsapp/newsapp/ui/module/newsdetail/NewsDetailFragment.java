package com.newsapp.newsapp.ui.module.newsdetail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.newsapp.mylibrary.BaseFragment;
import com.newsapp.newsapp.R;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.ui.module.home.HomeActivity;
import com.newsapp.newsapp.ui.module.newssourceview.NewsSourceWebView;
import com.newsapp.newsapp.util.DateUtil;

public class NewsDetailFragment extends BaseFragment {

  private static final String BUNDLE_NEWS = "bundle_news";
  @BindView(R.id.tv_source) TextView tvSource;
  @BindView(R.id.tv_date) TextView tvDate;
  @BindView(R.id.img_news) ImageView imgNews;
  @BindView(R.id.tv_headline) TextView tvHeadlines;
  @BindView(R.id.tv_description) TextView tvDescription;
  @BindView(R.id.btn_source) Button btnSource;

  @BindView(R.id.toolbar) androidx.appcompat.widget.Toolbar toolbar;
  private News.Article newsArticle;

  public static NewsDetailFragment newInstance(News.Article newsArticle) {
    Bundle args = new Bundle();
    args.putParcelable(BUNDLE_NEWS, newsArticle);
    NewsDetailFragment fragment = new NewsDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void initDataCalls() {

  }

  @Override protected void initObservers() {

  }

  @Override protected void initViewModels() {

  }

  @Override public int getContentLayout() {
    return R.layout.fragment_news_detail;
  }

  @Override protected void initDependencies() {
  }

  @Override protected void onReady(Bundle savedInstanceState) {
    if (getArguments() != null) {
      newsArticle = (News.Article) getArguments().getParcelable(BUNDLE_NEWS);
    }
    if (newsArticle == null) {
      getFragmentManager().popBackStack();
    }

    toolbar.setNavigationOnClickListener(v -> {
      getActivity().onBackPressed();
    });

    Glide.with(getContext())
        .load(newsArticle.imageURL())
        .fitCenter()
        .error(R.drawable.ic_launcher_background)
        .into(imgNews);
    tvHeadlines.setText(newsArticle.title());
    tvSource.setText(newsArticle.author());
    tvDate.setText(DateUtil.formaReadableDate(newsArticle.publishedAt()));
    tvDescription.setText(newsArticle.description());
    btnSource.setVisibility(newsArticle.url() != null ? View.VISIBLE : View.GONE);
    btnSource.setOnClickListener(v -> {
      if (newsArticle.url() != null) {
        ((HomeActivity) getActivity()).replaceFragment(
            NewsSourceWebView.newInstance(newsArticle.url()));
      }
    });
  }
}
