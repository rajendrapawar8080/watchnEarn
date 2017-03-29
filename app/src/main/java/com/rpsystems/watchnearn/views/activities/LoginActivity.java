package com.rpsystems.watchnearn.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import android.widget.EditText;

import com.rpsystems.watchnearn.R;

import butterknife.BindView;

/**
 * Created by neosoft on 27/3/17.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.edt_email_id)EditText mUserMail;
    @BindView(R.id.edt_password_id)EditText mUserPass;
    @BindView(R.id.btn_login_id)Button mLoginButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
