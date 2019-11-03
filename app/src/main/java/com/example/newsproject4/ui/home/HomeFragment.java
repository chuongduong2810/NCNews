package com.example.newsproject4.ui.home;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.newsproject4.News.Adapter;
import com.example.newsproject4.News.Article;
import com.example.newsproject4.News.News;
import com.example.newsproject4.News.Utils;
import com.example.newsproject4.R;
import com.example.newsproject4.api.ApiClient;
import com.example.newsproject4.api.ApiInterfaces;
import com.example.newsproject4.navigation;
import com.example.newsproject4.ui.tabsLayout.TabsAdapter;
import com.example.newsproject4.ui.tabsLayout.hot;
import com.example.newsproject4.ui.tabsLayout.music;
import com.example.newsproject4.ui.tabsLayout.social;
import com.example.newsproject4.ui.tabsLayout.sport;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout=(TabLayout)v.findViewById(R.id.tablayout_id);
        viewPager=(ViewPager)v.findViewById(R.id.viewpager_id);

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
          //  @Override
          //  public void onTabSelected(TabLayout.Tab tab) {

          //  }

           // @Override
          //  public void onTabUnselected(TabLayout.Tab tab) {

          //  }

           // @Override
//
           // }
       // });

    }

    private void setUpViewPager(ViewPager viewPager) {
        TabsAdapter adapter=new TabsAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabLayout.removeAllTabs();
    }

}
