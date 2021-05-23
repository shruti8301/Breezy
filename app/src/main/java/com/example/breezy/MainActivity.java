package com.example.breezy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.breezy.fragments.ChatFragment;
import com.example.breezy.fragments.QuizFragment;
import com.example.breezy.fragments.HomeFragment;
import com.example.breezy.fragments.ProfileFragment;
import com.example.breezy.fragments.TaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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

                case R.id.nav_feedback:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_frame, new QuizFragment()).commit();
                    setItemCheckable(true);
                    return true;

                case R.id.nav_tasks:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_frame, new TaskFragment()).commit();
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

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    SharedPreferences userPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor Ed = userPrefs.edit();
                    Ed.putString("Name", String.valueOf(snapshot.child("Name").getValue()));
                    Ed.putString("Age", String.valueOf(snapshot.child("Age").getValue()));
                    Ed.putString("Contact", String.valueOf(snapshot.child("Contact").getValue()));
                    Ed.putString("Points", String.valueOf(snapshot.child("Points").getValue()));
                    Ed.commit();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something is wrong with database", Toast.LENGTH_LONG).show();
                Log.e("Error:Database", error.getMessage());
            }
        });
    }
}