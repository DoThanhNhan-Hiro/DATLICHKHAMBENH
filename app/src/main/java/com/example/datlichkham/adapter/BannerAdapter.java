package com.example.datlichkham.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.datlichkham.R;
import com.example.datlichkham.dto.Banner;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    Context context;
    List<Banner> list;

    public BannerAdapter(Context context, List<Banner> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, container, false);
        ImageView imageBanner = view.findViewById(R.id.img_banner);

        Banner banner = list.get(position);
        if (banner != null){
            Glide.with(context).load(banner.getResourceId()).into(imageBanner);
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
