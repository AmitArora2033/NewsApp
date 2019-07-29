package com.newsapp.mylibrary;

public class BaseViewState<T> {
  protected T data;
  protected String error;
  protected State currentState;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public State getCurrentState() {
    return currentState;
  }

  public void setCurrentState(State currentState) {
    this.currentState = currentState;
  }

  public enum State {
    LOADING(0), SUCCESS(1), FAILED(-1);
    public int value;

    State(int val) {
      value = val;
    }
  }
}
