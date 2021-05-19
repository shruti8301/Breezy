package com.example.breezy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.breezy.fragments.ChatFragment;
import com.example.breezy.fragments.HomeFragment;
import com.example.breezy.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigation) BottomNavigationView bottomNavigation;
    @BindView(R.id.profileFloatingBtn) FloatingActionButton profileFloatingBtn;
    @BindView(R.id.nav_host_frame) FrameLayout nav_host_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        profileFloatingBtn.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_frame, new ProfileFragment()).commit();
            setItemCheckable(false);
        });

        bottomNavigation.setSelectedItemId(R.id.nav_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_frame, new HomeFragment()).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_chat:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_frame, new ChatFragment()).commit();
                    setItemCheckable(true);
                    return true;

                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_frame, new HomeFragment()).commit();
                    setItemCheckable(true);
                    return true;
            }
            return false;
        });
    }

    private void setItemCheckable(boolean b) {
        bottomNavigation.getMenu().getItem(0).setCheckable(b);
        bottomNavigation.getMenu().getItem(2).setCheckable(b);
    }
}