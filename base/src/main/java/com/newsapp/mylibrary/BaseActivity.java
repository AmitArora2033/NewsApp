package com.newsapp.mylibrary;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDependencies();
  }

  @Override public void setContentView(@LayoutRes int resId) {
    super.setContentView(resId);
    ButterKnife.bind(this);
    initViewModel();
    onReady();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public abstract void initDependencies();

  public abstract void onReady();

  protected abstract void initViewModel();
}
