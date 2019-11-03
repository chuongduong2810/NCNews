package com.example.newsproject4;

import android.content.Intent;
import android.os.Bundle;

import com.example.newsproject4.News.Adapter;
import com.example.newsproject4.News.Article;
import com.example.newsproject4.News.News;
import com.example.newsproject4.News.Utils;
import com.example.newsproject4.Retrofit.APIUtils;
import com.example.newsproject4.Retrofit.DataClient;
import com.example.newsproject4.api.ApiClient;
import com.example.newsproject4.api.ApiInterfaces;
import com.example.newsproject4.ui.home.HomeFragment;
import com.example.newsproject4.ui.tabsLayout.TabsAdapter;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class navigation extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CircleImageView circleImageView;
    TabLayout tabLayout;
    ViewPager viewPager;
    TabsAdapter tabsAdapter;
    Button btn_sign_out;

    //test
    TextView tvName;
    TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Navigation Drawer
        circleImageView=findViewById(R.id.useravatar);
        drawerLayout = findViewById(R.id.main_drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile,R.id.nav_login)
                .setDrawerLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Display current user name and email
        View header=navigationView.getHeaderView(0);


        tvName=header.findViewById(R.id.tv_user);
        tvEmail=header.findViewById(R.id.tv_email);
        checkLogin();


        //Sign out
        btn_sign_out = (Button) findViewById(R.id.btn_sign_out);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log out
                AuthUI.getInstance()
                        .signOut(navigation.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //btn_sign_out.setEnabled(false);
                                //showSignInOptions();
                                Toast.makeText(navigation.this, "Signed out" , Toast.LENGTH_SHORT).show();
                                tvName.setText(R.string.text_username);
                                tvEmail.setText(R.string.text_email);
                                navigationView.getMenu().findItem(R.id.nav_profile).setVisible(false);
                                navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(navigation.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
//Check login to display elements
    private void checkLogin() {
        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            tvName.setText(name);
            tvEmail.setText(email);
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);

        }else{
            navigationView.getMenu().findItem(R.id.nav_profile).setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}