package com.example.datlichkham.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.datlichkham.R;
import com.example.datlichkham.fragment.Fragment_ManagerCategory;
import com.example.datlichkham.fragment.Fragment_ManagerDoctor;
import com.example.datlichkham.fragment.Fragment_ManagerFile;
import com.example.datlichkham.fragment.Fragment_ManagerRoom;
import com.example.datlichkham.fragment.Fragment_ManagerService;
import com.example.datlichkham.fragment.Fragment_ViewPagerTimeWork;
import com.example.datlichkham.fragment.Fragment_list_order_to_day;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private FrameLayout fragmentContent;
    private NavigationView navigationAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolBar = findViewById(R.id.toolBar);
        fragmentContent = findViewById(R.id.fragment_content);
        navigationAdmin = findViewById(R.id.nv_view);
        toolBar.setNavigationOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        navigationAdmin.setNavigationItemSelectedListener(this);
        replaceFragmet(new Fragment_list_order_to_day());
        navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(true);
        toolBar.setTitle("Danh sách Đặt lịch khám hôm nay");

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isOpen()) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.m_managerDoctor:
                replaceFragmet(new Fragment_ManagerDoctor());
                toolBar.setTitle("Quản lí bác sĩ");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                break;
            case R.id.m_managerTimeWork:
                toolBar.setTitle("Quản lí ca làm việc");
                replaceFragmet(new Fragment_ViewPagerTimeWork());
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                break;
            case R.id.m_managerCategory:
                replaceFragmet(new Fragment_ManagerCategory());
                toolBar.setTitle("Quản lí loại dịch vụ khám");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                break;
            case R.id.m_managerService:
                replaceFragmet(new Fragment_ManagerService());
                toolBar.setTitle("Quản lí chuyên khoa");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                break;
            case R.id.m_managerRoom:
                replaceFragmet(new Fragment_ManagerRoom());
                toolBar.setTitle("Quản lí phòng khám");
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(false);
                break;

            case R.id.list_order_today:
                toolBar.setTitle("Danh sách đặt lịch khám hôm nay");
                replaceFragmet(new Fragment_list_order_to_day());
                navigationAdmin.getMenu().findItem(R.id.list_order_today).setChecked(true);
                navigationAdmin.getMenu().findItem(R.id.m_managerDoctor).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerCategory).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerTimeWork).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerService).setChecked(false);
                navigationAdmin.getMenu().findItem(R.id.m_managerRoom).setChecked(false);
                break;

            case R.id.m_logOut:
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragmet(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commit();
    }
}