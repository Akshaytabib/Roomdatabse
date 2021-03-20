package com.example.test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageViewAdater extends FragmentPagerAdapter {

    public PageViewAdater(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllUserView allUserView = new AllUserView();
                return allUserView;

            case 1:
                ActiveFragment activeFragment = new ActiveFragment();
                return activeFragment;

            case 2:
                CompleteFragment completeFragment = new CompleteFragment();
                return completeFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}