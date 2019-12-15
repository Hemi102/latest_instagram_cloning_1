package com.example.latest_instagram_cloning_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabFragment extends FragmentPagerAdapter {
    public TabFragment(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new profileTab();
            case 1:
                return new userTab();
            case 2:
                return new sharePictureTab();
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
        switch (position)
        {
            case 0:
                return "Profile";
            case 1:
                return "User";
            case 2:
                return "Shared Pictures";

            default:
                return null;

        }
    }
}
