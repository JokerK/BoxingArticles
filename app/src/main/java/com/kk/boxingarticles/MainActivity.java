package com.kk.boxingarticles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kk.boxingarticles.adapter.ArticleAdapter;
//import com.kk.androidcardview.manager.ArticleManager;
import com.kk.boxingarticles.model.Article;
import com.kk.boxingarticles.utils.DataManager;

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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.articles);

        //w celach optymalizacji
        recyclerView.setHasFixedSize(true);

        //ustawiamy LayoutManagera
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //ustawiamy animatora, ktory odpowiada za animacje dodania/usuniecia elementow listy
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //myBoxArticles = new ArticleManager(this, recyclerView);

        //myBoxArticles.execute("dawaj te artykuly");


        //tworzymy adapter oraz laczymy go z RecyclerView
        //recyclerView.setAdapter(new ArticleAdapter(myBoxArticles.getArticleList(), recyclerView));


        new DataManager().getArticlesFromWebsite().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.<String>getObserver());

    }

    protected  Observer<ArrayList<Article>> getObserver() {
        return new Observer<ArrayList<Article>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("Observer get: ", "mamy sub");
              //  Toast.makeText(context, "Wait...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(ArrayList<Article> value) {
                Log.i("Observer get: ",value.toString());
                recyclerView.setAdapter(new ArticleAdapter(value, getApplicationContext()));
            }


            @Override
            public void onError(Throwable e) {
                Log.i("Observer get ", "Error");
            }

            @Override
            public void onComplete() {
                Log.i("Observer  ", "complete");
            }
        };
    }


}