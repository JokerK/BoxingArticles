package com.kk.boxingarticles.viewModel;

import android.content.Context;

import com.kk.boxingarticles.model.Article;

/**
 * Created by Karol on 2017-02-04.
 */

public class ArticleViewModel {
    private Context mContext;
    private Article mArticle;

    public ArticleViewModel(Context context, Article article) {
        this.mContext = context;
        this.mArticle = article;
    }
    public String getTitle() {
        return mArticle.getTitle();
    }

    public String getContent() {
        return mArticle.getContent();
    }
    public String getDate() {
        return mArticle.getDate();
    }
    public String getAuthor() {
        return mArticle.getAuthor();
    }
    public String getContentUrl() {
        return mArticle.getContentUrl();
    }
    public String getImage() {
        return mArticle.getContentUrl();
    }
        

}
