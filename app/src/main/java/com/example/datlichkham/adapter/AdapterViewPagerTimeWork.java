package com.example.datlichkham.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.datlichkham.fragment.Fragment_TimeWorkDetail;
import com.example.datlichkham.fragment.Fragmet_TimeWork;

public class AdapterViewPagerTimeWork extends FragmentStateAdapter {


    public AdapterViewPagerTimeWork(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragmet_TimeWork();
            case 1:
                return  new Fragment_TimeWorkDetail();
            default:
                return new Fragmet_TimeWork();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
