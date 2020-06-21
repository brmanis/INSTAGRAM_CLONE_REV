package com.example.instagram_clone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class tabAdapter extends FragmentPagerAdapter {
    public tabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {
        switch (tabPosition){
            case 0:
                ProfileTab profileTab = new ProfileTab();
                return profileTab;
            case 1:
                return new Users_Tab();
            case 2:
                 return new SharePicture_Tab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "PROFILE";
            case 1:
                return "USERS";
            case 2:
                return "SHARE PICTURES";
            default:
                return null;
        }
    }
}
