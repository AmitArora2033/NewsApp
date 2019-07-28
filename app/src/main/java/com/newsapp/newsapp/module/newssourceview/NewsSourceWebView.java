package com.newsapp.newsapp.module.newssourceview;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import com.newsapp.mylibrary.BaseFragment;
import com.newsapp.newsapp.R;
import com.newsapp.newsapp.module.home.HomeActivity;

public class NewsSourceWebView extends BaseFragment {

  private static final String BUNDLE_URL = "web_view_url";
  @BindView(R.id.web_view) WebView webView;
  @BindView(R.id.toolbar) androidx.appcompat.widget.Toolbar toolbar;

  public static NewsSourceWebView newInstance(String url) {
    Bundle args = new Bundle();
    args.putString(BUNDLE_URL, url);
    NewsSourceWebView fragment = new NewsSourceWebView();
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
    return R.layout.fragment_news_source_webview;
  }

  @Override protected void initDependencies() {

  }

  @Override protected void onReady(Bundle savedInstanceState) {
    String url = null;

    if (getArguments() != null) {
      url = getArguments().getString(BUNDLE_URL);
    }
    toolbar.setTitle(R.string.app_name);
    toolbar.setNavigationOnClickListener(v -> {
      getFragmentManager().popBackStack();
    });
    ((HomeActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
    if (url != null) {
      webView.getSettings().setJavaScriptEnabled(true);
      webView.setWebViewClient(new WebViewClient());
      webView.loadUrl(url);
    }
  }
}
