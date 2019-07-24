package com.newsapp.mylibrary;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BaseViewModel extends ViewModel {

  private static final String TAG = BaseViewModel.class.getSimpleName();
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  protected final void addCompositeDisposable(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  @Override protected void onCleared() {
    compositeDisposable.clear();
    super.onCleared();
  }

  public <T> ObservableTransformer<T, T> applyObservableSchedulers() {
    return upstream -> upstream.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).doOnError(throwable -> {
          Log.e(TAG, throwable.getMessage());
        });
  }

  public <T> SingleTransformer<T, T> appSingleTransformer() {
    return upstream -> upstream.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).doOnError(throwable -> {
          Log.e(TAG, throwable.getMessage());
        });
  }
}
