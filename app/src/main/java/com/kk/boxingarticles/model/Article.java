package com.kk.boxingarticles.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.style.ParagraphStyle;

/**
 * Created by Karol on 2017-01-25.
 */

public class Article implements Parcelable {
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

    public Article(Parcel in) {
        mTitle = in.readString();
        mAuthor = in.readString();
        mContent = in.readString();
        mContentUrl = in.readString();
        mDate = in.readString();
        mImageUrl = in.readString();

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
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mAuthor);
        dest.writeString(mContent);
        dest.writeString(mContentUrl);
        dest.writeString(mDate);
        dest.writeString(mImageUrl);
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}