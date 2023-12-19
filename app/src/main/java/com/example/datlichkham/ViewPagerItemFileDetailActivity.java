package com.example.datlichkham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.datlichkham.adapter.AdapterViewPagerItemFileDetail;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ViewPagerItemFileDetailActivity extends AppCompatActivity {
    private TabLayout tabVpStatisticalDoctor;
    private ViewPager2 vpFileDetail;
    private Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_item_file_detail);
        tabVpStatisticalDoctor = findViewById(R.id.tab_vp_statisticalDoctor);
        toolBar = findViewById(R.id.toolBar);
        toolBar.setNavigationOnClickListener(view->{
            finish();
        });

        vpFileDetail = findViewById(R.id.vp_fileDetail);
        AdapterViewPagerItemFileDetail adapterViewPagerItemFileDetail = new AdapterViewPagerItemFileDetail(this);
        vpFileDetail.setAdapter(adapterViewPagerItemFileDetail);
        TabLayoutMediator mediator = new TabLayoutMediator(tabVpStatisticalDoctor, vpFileDetail, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("Thông tin người bệnh");
                }else if(position==1){
                    tab.setText("Lịch đã đặt");
                }else if(position==2){
                    tab.setText("Lịch sử khám");
                }
            }
        });
        mediator.attach();

    }
}