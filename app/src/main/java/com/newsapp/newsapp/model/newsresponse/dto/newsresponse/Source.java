package com.newsapp.newsapp.model.newsresponse.dto.newsresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name")
  @Expose
  private String name;

  public Source() {
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
