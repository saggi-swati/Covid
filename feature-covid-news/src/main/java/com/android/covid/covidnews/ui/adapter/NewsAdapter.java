package com.android.covid.covidnews.ui.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid.covidnews.R;
import com.android.covid.covidnews.model.Article;
import com.android.covid.network.NetworkState;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends PagedListAdapter<Article, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private NetworkState networkState;

    public NewsAdapter(Context context) {
        super(Article.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            View v = layoutInflater.inflate(R.layout.item_network_state, parent, false);
            return new NetworkStateItemViewHolder(v);

        } else {
            View v = layoutInflater.inflate(R.layout.covid_news_item, parent, false);
            return new ArticleItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticleItemViewHolder) {
            ((ArticleItemViewHolder) holder).bindTo(getItem(position));
        } else {
            ((NetworkStateItemViewHolder) holder).bindView(networkState);
        }
    }


    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    public class ArticleItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView newsItemImage;
        private TextView newsItemDesc;
        private TextView newsItemDatePosted;
        private TextView newsItemTitle;

        ArticleItemViewHolder(View view) {
            super(view);
            newsItemTitle = view.findViewById(R.id.news_item_title);
            newsItemDatePosted = view.findViewById(R.id.news_item_date_posted);
            newsItemDesc = view.findViewById(R.id.news_item_desc);
            newsItemImage = view.findViewById(R.id.news_item_image);
        }

        void bindTo(Article article) {

            String author = article.getAuthor() == null || article.getAuthor().isEmpty() ? "" : article.getAuthor();
            String titleString = String.format(context.getString(R.string.item_title), author, article.getTitle());
            SpannableString spannableString = new SpannableString(titleString);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context.getApplicationContext(), R.color.secondary_text)),
                    titleString.lastIndexOf(author) + author.length() + 1, titleString.lastIndexOf(article.getTitle()) - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            newsItemTitle.setText(spannableString);
            newsItemDatePosted.setText(article.getPublishedAt());
            newsItemDesc.setText(article.getDescription());
            Picasso.get().load(article.getUrlToImage()).into(newsItemImage);
        }
    }

    public static class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private TextView errorMsg;

        NetworkStateItemViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progress_bar);
            errorMsg = view.findViewById(R.id.error_msg);
        }

        void bindView(NetworkState networkState) {
            if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                errorMsg.setVisibility(View.VISIBLE);
                errorMsg.setText(networkState.getMsg());
            } else {
                errorMsg.setVisibility(View.GONE);
            }
        }
    }
}
