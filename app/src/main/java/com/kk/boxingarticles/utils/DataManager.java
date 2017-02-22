package com.kk.boxingarticles.utils;

import android.util.Log;

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

/**
 * Created by kkand on 21.02.2017.
 */

public class DataManager {

    final private static String SOURCE_WEB = "http://www.ringpolska.pl";
    private ArrayList<Article> articleList = new ArrayList<>();

    public  Observable<ArrayList<Article>> getArticlesFromWebsite() {
        Observable<ArrayList<Article>> observable = Observable.fromCallable(new Callable<ArrayList<Article>>() {
            @Override
            public ArrayList<Article> call() throws Exception {
                ArrayList<Article> data = getHeadlines(SOURCE_WEB);
                return data;
            }
        });
        return observable;
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
            String newsImageUrl = "ringpolska.pl";
            if(newsImage != null)                                                                     //todo ogarnac tego ifa
                newsImageUrl = String.valueOf(newsImage.attr("src"));
            if (!(newsImageUrl.contains(SOURCE_WEB))) {
                newsImageUrl = SOURCE_WEB + newsImageUrl;
                if(newsImageUrl.contains("www.youtube.com"))
                    newsImageUrl=getYouTubeThumbnail(newsImageUrl);
            }
            Log.i("ContentUrl", newsContentUrl);
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
