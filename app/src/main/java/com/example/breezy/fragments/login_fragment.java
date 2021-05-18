package com.example.breezy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.breezy.R;

public class login_fragment extends Fragment {
    EditText username;
    EditText password;
    Button login;
    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.pass);
        login = root.findViewById(R.id.login);

        username.setTranslationX(800);
        password.setTranslationX(800);
        login.setTranslationX(800);

        username.setAlpha(v);
        password.setAlpha(v);
        login.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        return root;
    }
}
