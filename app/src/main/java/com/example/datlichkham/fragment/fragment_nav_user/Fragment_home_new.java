package com.example.datlichkham.fragment.fragment_nav_user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.datlichkham.FileActivity;
import com.example.datlichkham.ListDoctorActivity;
import com.example.datlichkham.ListOrderActivity;
import com.example.datlichkham.ListServiceActivity;
import com.example.datlichkham.R;
import com.example.datlichkham.SearchActivity;
import com.example.datlichkham.adapter.AdapterListDoctor;
import com.example.datlichkham.adapter.AdapterListService;
import com.example.datlichkham.adapter.BannerAdapter;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.Banner;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class Fragment_home_new extends Fragment {
    private RecyclerView rcv_list_services,rcv_list_doctors;
    private CircleIndicator circleIndicator;
    private ViewPager viewPager;
    private BannerAdapter bannerAdapter;
    private List<Banner> list;
    private LinearLayout llOrderService,llOrderDoctor,llFile;
    private TextView tvSearch;

    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = viewPager.getCurrentItem();
            if (currentPosition == list.size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(currentPosition + 1);
            }
        }
    };

    private TextView tvListService,tvlistDoctor,tvListOrder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_new,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_services = view.findViewById(R.id.rcv_list_services);
        rcv_list_doctors = view.findViewById(R.id.rcv_list_doctors);
        tvListOrder = view.findViewById(R.id.tvListOrder);
        llOrderDoctor = view.findViewById(R.id.llOrderDoctor);
        llOrderService = view.findViewById(R.id.llOrderService);
        llFile = view.findViewById(R.id.llFile);
        tvSearch = view.findViewById(R.id.tvSearch);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getContext(), SearchActivity.class);
                    startActivity(intent);
            }
        });

        llFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FileActivity.class);
                startActivity(intent);
            }
        });

        //banner
        viewPager = view.findViewById(R.id.view_pager);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        list = getListPhoto();
        bannerAdapter = new BannerAdapter(getContext(), list);
        viewPager.setAdapter(bannerAdapter);

        llOrderService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ListServiceActivity.class);
                startActivity(intent);
            }
        });

        llOrderDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ListDoctorActivity.class);
                startActivity(intent);
            }
        });

        circleIndicator.setViewPager(viewPager);
        handler.postDelayed(runnable, 3000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //banner

        ServicesDAO service = new ServicesDAO(getContext());
        ArrayList<ServicesDTO> ds = service.selectAll();
        AdapterListService adapter1 = new AdapterListService(ds,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rcv_list_services.setLayoutManager(manager);
        rcv_list_services.setAdapter(adapter1);

        DoctorDAO doctor = new DoctorDAO(getContext());
        ArrayList<DoctorDTO> listDtoDoctor = doctor.selectAll();
        AdapterListDoctor adapter2 = new AdapterListDoctor(listDtoDoctor,getContext());
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rcv_list_doctors.setLayoutManager(manager1);
        rcv_list_doctors.setAdapter(adapter2);

        tvListOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListOrderActivity.class);
                startActivity(intent);
            }
        });


    }

    private List<Banner> getListPhoto() {
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(R.drawable.banner1));
        list.add(new Banner(R.drawable.banner2));
        list.add(new Banner(R.drawable.banner3));
        list.add(new Banner(R.drawable.banner4));
        list.add(new Banner(R.drawable.banner5));
        list.add(new Banner(R.drawable.banner6));
        list.add(new Banner(R.drawable.banner7));
        list.add(new Banner(R.drawable.banner8));
        list.add(new Banner(R.drawable.banner9));
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}
