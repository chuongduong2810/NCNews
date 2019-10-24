package com.example.newsproject4.ui.tabsLayout;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsproject4.News.Adapter;
import com.example.newsproject4.News.Article;
import com.example.newsproject4.News.News;
import com.example.newsproject4.News.Utils;
import com.example.newsproject4.R;
import com.example.newsproject4.api.ApiClient;
import com.example.newsproject4.api.ApiInterfaces;
import com.example.newsproject4.navigation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class music extends Fragment {


    String API_KEY = "0d7bf67ed34c46d2a60320ad0e75ac1a";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Article> articles = new ArrayList<>();
    String TAB= navigation.class.getSimpleName();
    View v;
    Adapter adapter;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v=inflater.inflate(R.layout.fragment_music,container,false);
        drawerLayout= v.findViewById(R.id.music_drawerLayout);

        toolbar=v.findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = v.findViewById(R.id.music_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView .setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        LoadJson();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void LoadJson() {
        ApiInterfaces apiInterfaces = ApiClient.getApiClient().create(ApiInterfaces.class);
        String country = Utils.getCountry();

        Call<News> call;
        call=apiInterfaces.getNews(country,API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful()&&response.body().getArticle()!=null){
                    if(!articles.isEmpty()){
                        articles.clear();
                    }
                    articles=response.body().getArticle();
                    adapter = new Adapter(articles, getActivity());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(),"No Result!",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}

