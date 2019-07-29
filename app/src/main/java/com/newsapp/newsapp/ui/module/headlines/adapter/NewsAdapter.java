package com.newsapp.newsapp.ui.module.headlines.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.newsapp.newsapp.R;
import com.newsapp.newsapp.model.newsresponse.domain.News;
import com.newsapp.newsapp.util.DateUtil;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

  private final NewsItemSelectedListener listener;
  private ArrayList<News.Article> newsList = new ArrayList<>();

  public NewsAdapter(NewsItemSelectedListener listener) {
    this.listener = listener;
  }

  public void addData(ArrayList<News.Article> news) {
    int pos = newsList.size() - 1;
    this.newsList.addAll(news);
    if (newsList.size() > 0) {
      notifyItemRangeInserted(pos, news.size());
    } else {
      notifyDataSetChanged();
    }
  }

  public List<News.Article> getNewsArticleData() {
    return newsList;
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false),
        listener);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.tvHeadline.setText(newsList.get(position).title());
    holder.tvSubhead.setText(newsList.get(position).sourceName());
    holder.tvDate.setText(DateUtil.formaReadableDate(newsList.get(position).publishedAt()));
    Glide.with(holder.imgHeadline.getContext())
        .load(newsList.get(position).imageURL())
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(holder.imgHeadline);
  }

  @Override public int getItemCount() {
    return newsList.size();
  }

  public interface NewsItemSelectedListener {

    void onNewsItemSelected(int selectedItemPosition);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_news) ImageView imgHeadline;
    @BindView(R.id.tv_headline) TextView tvHeadline;
    @BindView(R.id.tv_subhead) TextView tvSubhead;
    @BindView(R.id.tv_date) TextView tvDate;

    public ViewHolder(@NonNull View itemView, NewsItemSelectedListener listener) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      itemView.setOnClickListener(v -> {
        listener.onNewsItemSelected(getAdapterPosition());
      });
    }
  }
}
