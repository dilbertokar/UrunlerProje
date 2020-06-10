package com.example.urunlerproje;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapterr extends FragmentPagerAdapter {

    Fragment1 image1Fragment;
    Fragment2 image2Fragment;
    Fragment3 image3Fragment;

    public ViewPagerAdapterr(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return Fragment1.newInstance(0);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return Fragment2.newInstance(1);
            case 2: // Fragment # 1 - This will show SecondFragment
                return Fragment3.newInstance(2);
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
