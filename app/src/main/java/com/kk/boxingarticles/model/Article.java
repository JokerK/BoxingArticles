package com.kk.boxingarticles.model;

import android.databinding.BaseObservable;

/**
 * Created by Karol on 2017-01-25.
 */

public class Article extends BaseObservable {
    private String mTitle;
    private String mAuthor;
    private String mContent;
    private String mDate;
    private String mContentUrl;
    private String mImageUrl;



    public Article(String mTitle, String mAuthor, String mDate,String mContentUrl,String mImageUrl) {

        // ustawiamy  tytuł i treść artykułu
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mDate = mDate;
        this.mContent = mContentUrl;
        this.mImageUrl = mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }
    public String getDate() {
        return mDate;
    }
    public String getAuthor() {
        return mAuthor;
    }
    public String getContentUrl() {
        return mContentUrl;
    }
    public String getImage() {
        return mImageUrl;
    }


public String toString() {
    return this.mTitle +": " + mContent+"\n";
}}