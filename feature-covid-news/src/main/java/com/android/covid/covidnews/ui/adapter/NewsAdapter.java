package com.android.covid.covidnews.ui.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.covid.covidnews.R;
import com.android.covid.covidnews.model.Article;
import com.covid.util.CovidUtil;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends PagedListAdapter<Article, NewsAdapter.ArticleItemViewHolder> {

    private Context context;

    public NewsAdapter(Context context) {
        super(Article.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ArticleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.covid_news_item, parent, false);
        return new ArticleItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleItemViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    class ArticleItemViewHolder extends RecyclerView.ViewHolder {

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

            newsItemTitle.setText(spannableString);
            newsItemDatePosted.setText(article.getPublishedAt());
            newsItemDesc.setText(article.getDescription());
            if (!CovidUtil.isNullOrEmpty(article.getUrlToImage()))
                Picasso.get().load(article.getUrlToImage()).into(newsItemImage);
        }
    }
}
