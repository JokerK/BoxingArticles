package com.kk.boxingarticles.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Karol on 2017-02-08.
 */

public class BindingUtils {
    private BindingUtils() {

    }
    @BindingAdapter("imageUrl")
    public static void  setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Picasso.with(context).load(url).into(imageView);

    }
}

