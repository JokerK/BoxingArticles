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


    final private static String SOURCE_WEB = "http://www.ringpolska.pl";
    private ArrayList<Article> articleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = (RecyclerView) findViewById(R.id.articles);

        //w celach optymalizacji
        recyclerView.setHasFixedSize(true);

        //ustawiamy LayoutManagera
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        //ustawiamy animatora, ktory odpowiada za animacje dodania/usuniecia elementow listy
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //myBoxArticles = new ArticleManager(this, recyclerView);

        //myBoxArticles.execute("dawaj te artykuly");


        //tworzymy adapter oraz laczymy go z RecyclerView
        //recyclerView.setAdapter(new ArticleAdapter(myBoxArticles.getArticleList(), recyclerView));


          observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.<String>getObserver());

        /** Testowy kod */
//        Article one = new Article("dupa","dupa");
//        Article two = new Article("noga","noga");
//        ArrayList<Article> artykuly = new ArrayList<>();
//        artykuly.add(one);
//        artykuly.add(two);
//        recyclerView.setAdapter(new ArticleAdapter(artykuly));

    }

    Observable<ArrayList<Article>> observable = Observable.fromCallable(new Callable<ArrayList<Article>>() {
        @Override
        public ArrayList<Article> call() throws Exception {
            ArrayList<Article> data = getHeadlines(SOURCE_WEB);
            return data;
        }
    });

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
                recyclerView.setAdapter(new ArticleAdapter(value));
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


    public ArrayList<Article> getHeadlines(String source) throws IOException {
        Document doc = (Document) Jsoup.connect(source).get();
        Elements newsHeadlines = doc.select("h2");
        Elements newsAuthors = doc.select("span.art-postauthoricon");
        Elements newsDates = doc.select("time");
        Elements newsContentUrls = doc.select("h2 a[href]");
        Elements newsImagesUrls = doc.select("div[class=art-article]");

        int temp = newsHeadlines.size();
        int counter = 0;
        while (counter != temp) {
            String newsHeadline = String.valueOf(newsHeadlines.eq(counter).text());
            String newsAuthor = String.valueOf(newsAuthors.eq(counter).text());
            newsAuthor=changeAuthor(newsAuthor);
            String newsDate = String.valueOf(newsDates.eq(counter).text());
            String newsContentUrl = String.valueOf(newsContentUrls.eq(counter).attr("href"));
            Element newsImage = newsImagesUrls.get(counter).select("img , iframe").first();
            String newsImageUrl = String.valueOf(newsImage.attr("src"));
            if (!(newsImageUrl.contains(SOURCE_WEB))) {
                newsImageUrl = SOURCE_WEB + newsImageUrl;
                if(newsImageUrl.contains("www.youtube.com"))
                newsImageUrl=getYouTubeThumbnail(newsImageUrl);
            }
            Log.i("PictureURL", newsImageUrl);
            articleList.add(new Article(newsHeadline, newsAuthor, newsDate, newsContentUrl, newsImageUrl));
            counter++;
        }
        return  articleList;
    }

    public String getYouTubeThumbnail(String url) {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);
        if(matcher.find())
            return "http://img.youtube.com/vi/"+matcher.group()+"/hqdefault.jpg";
        return url;

    }

    public String changeAuthor(String author) {
        if(author.toLowerCase().equals("redakcja"))
            return "ringpolska.pl";
        return author;
    }

}