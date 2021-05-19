package com.example.breezy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.breezy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment {

    @BindView(R.id.login_email) EditText login_email;
    @BindView(R.id.login_pass) EditText login_pass;
    @BindView(R.id.login_btn) Button login_btn;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, root);

        login_email.setTranslationX(800);
        login_pass.setTranslationX(800);
        login_btn.setTranslationX(800);

        login_email.setAlpha(v);
        login_pass.setAlpha(v);
        login_btn.setAlpha(v);

        login_email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        login_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login_btn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        return root;
    }
}
