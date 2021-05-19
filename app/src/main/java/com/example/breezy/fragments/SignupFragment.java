package com.example.breezy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.breezy.LoginActivity;
import com.example.breezy.MainActivity;
import com.example.breezy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupFragment extends Fragment {

    @BindView(R.id.reg_name)
    EditText reg_name;
    @BindView(R.id.reg_email)
    EditText reg_email;
    @BindView(R.id.reg_age)
    EditText reg_age;
    @BindView(R.id.reg_contact)
    EditText reg_contact;
    @BindView(R.id.reg_password)
    EditText reg_password;
    @BindView(R.id.reg_conf_pass)
    EditText reg_conf_pass;
    @BindView(R.id.signup_btn)
    Button signup_btn;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        ButterKnife.bind(this, root);
        mAuth=FirebaseAuth.getInstance();
        signup_btn.setOnClickListener(view -> {
            String email = reg_email.getText().toString();
            String password = reg_password.getText().toString();
            String confpass = reg_conf_pass.getText().toString();

            if (email.isEmpty() || password.isEmpty() || confpass.isEmpty()) {
                Snackbar.make(view, "Please Enter all the Fields", Snackbar.LENGTH_LONG).show();
            } else if (!password.equals(confpass)) {
                Snackbar.make(view, "Passwords don't Match", Snackbar.LENGTH_LONG).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        } else {
                            Snackbar.make(view, "Sign Up Failed", Snackbar.LENGTH_LONG).show();
                            Log.e("error",task.getException().getMessage());

                        }
                    }
                });
            }
        });
        return root;
    }
}
