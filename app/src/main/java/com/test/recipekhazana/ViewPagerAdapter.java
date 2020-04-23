package com.test.recipekhazana;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {
    String[] titles;
    String[] images;
    Context context;

    public ViewPagerAdapter(String[] titles, String[] images, Context context) {
        this.titles = titles;
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_viewpager,container,false);
        ImageView imageView = v.findViewById(R.id.imageVP);
        TextView textView = v.findViewById(R.id.titleVP);
        Picasso.get().load(images[position]).into(imageView);
        textView.setText(titles[position]);
        container.addView(v);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
