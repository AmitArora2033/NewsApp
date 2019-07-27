package com.newsapp.newsapp.module.home;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import com.newsapp.mylibrary.BaseActivity;
import com.newsapp.newsapp.App;
import com.newsapp.newsapp.R;
import com.newsapp.newsapp.module.headlines.NewsHeadlineFragment;
import com.newsapp.newsapp.module.home.adapter.NewsAdapter;

public class HomeActivity extends BaseActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  private HomeViewModel viewModel;
  private NewsAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override protected void initViewModel() {
  }

  @Override public void initDependencies() {
    App.get(this).getComponent().inject(this);
  }

  @Override public void onReady() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);

    getSupportFragmentManager().beginTransaction()
        .add(R.id.frame_layout, NewsHeadlineFragment.newInstance())
        .commit();
  }

  public void addFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .addToBackStack(fragment.getClass().getSimpleName())
        .add(R.id.frame_layout, fragment)
        .commit();
  }

  public void replaceFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .addToBackStack(fragment.getClass().getSimpleName())
        .replace(R.id.frame_layout, fragment)
        .commit();
  }
}
