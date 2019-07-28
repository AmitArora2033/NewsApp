package com.newsapp.newsapp.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PageScrollListener extends RecyclerView.OnScrollListener {

  @Override public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

    if (linearLayoutManager != null) {

      int visibleItem = linearLayoutManager.getChildCount();
      int totalItems = linearLayoutManager.getItemCount();
      int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

      if (visibleItem + firstVisibleItemPosition >= totalItems
          && totalItems < getTotalItems()
          && !isLoading()) {
        loadMoreItems();
      }
    }

    super.onScrolled(recyclerView, dx, dy);
  }

  protected abstract void loadMoreItems();

  protected abstract long getTotalItems();

  protected abstract boolean isLoading();
}
