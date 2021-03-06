package com.kk.boxingarticles.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.kk.boxingarticles.R;


import com.kk.boxingarticles.databinding.ItemArticleBinding;
import com.kk.boxingarticles.model.Article;

import java.util.ArrayList;

/**
 * Created by Karol on 2017-01-25.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.BindingViewHolder> {

    private ArrayList<Article> mArticles;


    public ArticleAdapter(ArrayList<Article> mArticles) {
        this.mArticles = mArticles;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View statusContainer = inflater.inflate(R.layout.item_article, parent, false);
        return new BindingViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Article status = mArticles.get(position);
        holder.bind(status);

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class BindingViewHolder extends RecyclerView.ViewHolder {
            private ItemArticleBinding binding;

        public BindingViewHolder(View rowView) {
            super(rowView);
            this.binding = DataBindingUtil.bind(rowView);
        }
        public void bind(Article article) {
            binding.setArticle(article);
        }
    }


}


