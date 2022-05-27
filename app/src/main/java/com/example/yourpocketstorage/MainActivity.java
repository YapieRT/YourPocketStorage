package com.example.yourpocketstorage;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private EditText username;

    private EditText password;

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username_edit);

        password = findViewById(R.id.userpassword_edit);

        login = findViewById(R.id.login_btn);

        login.setOnClickListener(view -> {
            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                Toast.makeText(MainActivity.this, "Login Succesfull",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, BrowseActivity.class));
                finish();
            }
            else{
                Toast.makeText(MainActivity.this, "Login Failed",Toast.LENGTH_SHORT).show();
            }
        });

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("focus", "focus loosed");
                    // Do whatever you want here
                } else {
                    Log.d("focus", "focused");
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("focus", "focus loosed");
                    // Do whatever you want here
                } else {
                    Log.d("focus", "focused");
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}