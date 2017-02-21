package com.kk.boxingarticles.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;

import com.kk.boxingarticles.adapter.ArticleAdapter;
import com.kk.boxingarticles.model.Article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Karol on 2017-02-04.
 */

public class ArticleViewModel extends BaseObservable{
    private Context mContext;
    private Article mArticle;


    final private static String SOURCE_WEB = "http://www.ringpolska.pl";
    private static ArrayList<Article> articleList = new ArrayList<>();

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




}
