package com.kk.boxingarticles.viewModel;

import android.content.Context;

import com.kk.boxingarticles.model.Article;

/**
 * Created by Karol on 2017-02-04.
 */

public class ArticleViewModel {
    private Context context;
    private Article article;

    public ArticleViewModel(Context context, Article article) {
        this.context = context;
        this.article = article;
    }
    public String getArticleTitle() {
        return article.getTitle();
    }
    public String getCountVotes() {
        //just stubbed value
        return ""+10;
    }


}
