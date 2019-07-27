package com.newsapp.mylibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements BaseView {

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDependencies();
    initViewModels();
    initObservers();
    initDataCalls();
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    int layoutId = getContentLayout();
    return inflater.inflate(layoutId, container, false);
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    ButterKnife.bind(this, view);
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    onReady(savedInstanceState);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  protected abstract void initDataCalls();

  protected abstract void initObservers();

  protected abstract void initViewModels();

  public abstract @LayoutRes int getContentLayout();

  protected abstract void initDependencies();

  protected abstract void onReady(Bundle savedInstanceState);

  @Override public void showToast(int resId) {
    Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
  }

  @Override public void showContent() {

  }

  @Override public void showError(String errorMessage) {

  }

  @Override public void showToast(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }

  @Override public void showProgress() {

  }
}
