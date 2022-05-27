package com.example.yourpocketstorage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ItemOverview extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> {
            finish();
        });
    }
}