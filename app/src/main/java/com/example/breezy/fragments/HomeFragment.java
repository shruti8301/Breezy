package com.example.breezy.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.breezy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    @BindView(R.id.username) TextView username;
    @BindView(R.id.sleep_no) TextView sleep_no;
    @BindView(R.id.sleep_decrease) Button sleep_decrease;
    @BindView(R.id.sleep_increase) Button sleep_increase;

    int sleep_hours;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, root);

        SharedPreferences userPrefs = getContext().getSharedPreferences("UserPrefs", MODE_PRIVATE);
        username.setText(userPrefs.getString("Name", "Name"));

        sleep_hours = 0;
        sleep_decrease.setEnabled(false);

        sleep_increase.setOnClickListener(view -> {
            sleep_decrease.setEnabled(true);
            sleep_no.setText(String.valueOf(++sleep_hours));
        });

        sleep_decrease.setOnClickListener(view -> {
            sleep_no.setText(String.valueOf(--sleep_hours));
            if (sleep_hours == 0)
                sleep_decrease.setEnabled(false);
        });

        return root;
    }
}