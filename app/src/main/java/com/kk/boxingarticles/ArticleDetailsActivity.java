package com.kk.boxingarticles;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kk.boxingarticles.databinding.ArticleDetailsActivityBinding;
import com.kk.boxingarticles.model.Article;

/**
 * Created by kkand on 22.02.2017.
 */

public class ArticleDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.article_details_activity);
        ArticleDetailsActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.article_details_activity);

        Intent i = getIntent();
        Article article = i.getParcelableExtra("article");
        Log.i("Artykul " , article.toString());
        binding.setArticle(article);

    }



}
