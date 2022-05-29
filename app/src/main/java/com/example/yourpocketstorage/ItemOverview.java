package com.example.yourpocketstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemOverview extends AppCompatActivity {

    ImageView back;

    TextView id;
    TextView name;
    TextView amount;
    TextView price;
    TextView date;
    TextView total_price;
    AppCompatButton delete_btn;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_overview);

        dbHelper =  new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });

        /*---------------------------- FindViewById Item ---------------------------- */

        {
            id = findViewById(R.id.overview_textview_id_none);
            name = findViewById(R.id.overview_textview_name_none);
            amount = findViewById(R.id.overview_textview_amount_none);
            price = findViewById(R.id.overview_textview_price_none);
            date = findViewById(R.id.overview_textview_date_none);
            total_price = findViewById(R.id.overview_textview_total_price_none);
        }

        Item item = (Item) getIntent().getSerializableExtra("item");

        id.setText(Integer.toString(item.id));
        name.setText(item.name);
        amount.setText(Integer.toString(item.amount));
        price.setText(Float.toString(item.price) + "$");
        date.setText(item.DateAdded);
        total_price.setText(Float.toString(item.price*item.amount) + "$");

        delete_btn = findViewById(R.id.delete_btn);

        delete_btn.setOnClickListener(view -> {
            database.delete(DBHelper.TABLE_ITEM,DBHelper.KEY_ID + "= " + item.id,null);
            finish();
        });
    }


}