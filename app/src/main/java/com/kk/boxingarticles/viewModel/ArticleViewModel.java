package com.kk.boxingarticles.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.kk.boxingarticles.ArticleDetailsActivity;
import com.kk.boxingarticles.MainActivity;
import com.kk.boxingarticles.model.Article;

/**
 * Created by Karol on 2017-02-04.
 */

public class ArticleViewModel {
    private Context mContext;
    private Article mArticle;

    public ArticleViewModel(Article article,Context context) {
        this.mArticle = article;
        this.mContext = context;
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
        return mArticle.getImage();
    }

    public View.OnClickListener onReadMoreClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Otwieram artykul"+getTitle(), Toast.LENGTH_SHORT).show();
                Intent articleIntent = new Intent(mContext, ArticleDetailsActivity.class);
                articleIntent.putExtra("article", mArticle);
                mContext.startActivity(articleIntent);
            }
        };
    }


}
