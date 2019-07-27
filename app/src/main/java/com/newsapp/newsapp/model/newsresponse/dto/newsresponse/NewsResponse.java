package com.newsapp.newsapp.model.newsresponse.dto.newsresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsResponse {

  @SerializedName("code") @Expose private String code;
  @SerializedName("message") @Expose private String errorMessage;

  @SerializedName("status")
  @Expose
  private String status;
  @SerializedName("totalResults")
  @Expose
  private int totalResults;
  @SerializedName("articles")
  @Expose
  private List<Article> articles = null;

  public String getCode() {
    return code;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public String getStatus() {
    return status;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public List<Article> getArticles() {
    return articles;
  }
}
