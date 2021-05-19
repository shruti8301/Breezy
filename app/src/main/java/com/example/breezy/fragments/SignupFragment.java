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

public class SignupFragment extends Fragment {

    @BindView(R.id.reg_name) EditText reg_name;
    @BindView(R.id.reg_email) EditText reg_email;
    @BindView(R.id.reg_age) EditText reg_age;
    @BindView(R.id.reg_contact) EditText reg_contact;
    @BindView(R.id.reg_password) EditText reg_password;
    @BindView(R.id.reg_conf_pass) EditText reg_conf_pass;
    @BindView(R.id.signup_btn) Button signup_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup,container,false);

        ButterKnife.bind(this, root);

        return root;
    }
}
