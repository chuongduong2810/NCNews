package com.example.newsproject4.ui.tabsLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabsAdapter extends FragmentStatePagerAdapter {

    String[] TabsName=new String[] {"Hot","Music","Social","Sport"};
    public TabsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        return TabsName[position];
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new hot();
            case 1:
                return new music();
            case 2:
                return new social();
            case 3:
                return new sport();

        }
        return null;
    }

    @Override
    public int getCount() {
        return TabsName.length;
    }
}
