package com.example.breezy;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.breezy.fragments.login_fragment;
import com.example.breezy.fragments.signup_fragment;

class LoginAdapter extends FragmentPagerAdapter {
    Context context;
    int totaltabs;



    public LoginAdapter(FragmentManager fm, Context context, int totaltabs) {
        super(fm);
        this.context = context;
        this.totaltabs=totaltabs;
    }

    @Override
    public int getCount() {
        return totaltabs;
    }

    public Fragment getItem(int position)
    {
        switch(position){
            case 0:
                login_fragment login_fragment=new login_fragment();
                return login_fragment;
            case 1:
                signup_fragment signup_fragment=new signup_fragment();
                return signup_fragment;
            default:
                return null;
        }
    }


}
