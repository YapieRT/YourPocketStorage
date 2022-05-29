package com.example.yourpocketstorage;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.*;

@SuppressLint("SimpleDateFormat")
public class AddActivity extends AppCompatActivity {

    /*---------------------------- Variables ---------------------------- */

    private DrawerLayout SideBar;

    private ActionBarDrawerToggle SideBar_Toogle;

    private NavigationView SideBar_NavView;

    private Toolbar mToolBar;

    private Button AddButton;

    private Button OverviewLastButton;

    private EditText item_name;

    private EditText item_amount;

    private EditText item_price;


    SimpleDateFormat sdf;

    {
        sdf = new SimpleDateFormat("dd.MM.yyyy");
    }

    public List<Item> Item_Storage = new ArrayList<>();

    /*---------------------------- Database ---------------------------- */

    DBHelper dbHelper;


    /*---------------------------- EditTextFilters ---------------------------- */

    private final String item_amount_blockCharacterSet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM;:'\"\\,[]{}<>/?~#^|$%&*!()";

    private final InputFilter item_amount_and_price_filter = (source, start, end, dest, dstart, dend) -> {

        if (source != null && item_amount_blockCharacterSet.contains(("" + source))) {
            return "";
        }
        return null;
    };

    private final String item_name_blockCharacterSet = ";:'\"\\,{}<.>/?~#^|$%&*!";

    private final InputFilter item_name_filter = (source, start, end, dest, dstart, dend) -> {

        if (source != null && item_name_blockCharacterSet.contains(("" + source))) {
            return "";
        }
        return null;
    };

    /*---------------------------- OnCreate ---------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /*---------------------------- Database ---------------------------- */

        dbHelper =  new DBHelper(this);

        /*---------------------------- Menu findViewById ---------------------------- */

        {
            SideBar = findViewById(R.id.drawerLayout);
            mToolBar = findViewById(R.id.nav_action_bar);
            SideBar_NavView = findViewById(R.id.nav_view);
        }

        /*---------------------------- Item findViewById ---------------------------- */

        {
        item_name = findViewById(R.id.item_name);
        item_amount = findViewById(R.id.item_amount);
        item_price = findViewById(R.id.item_price);
        AddButton = findViewById(R.id.add_btn);
        }

        /*---------------------------- ToolBar ---------------------------- */

        {setSupportActionBar(mToolBar);

        SideBar_Toogle = new ActionBarDrawerToggle(this, SideBar,R.string.sidebar_open,R.string.sidebar_close);

        SideBar.addDrawerListener(SideBar_Toogle);

        SideBar_Toogle.syncState();}

        /*---------------------------- Navigation ---------------------------- */

        {SideBar_NavView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);}

        /*---------------------------- Focus ---------------------------- */

        {
        item_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        item_amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        item_price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("focus", "focus loosed");
                    // Do whatever you want here
                } else {
                    Log.d("focus", "focused");
                }
            }
        });}

        /*---------------------------- Add Button ---------------------------- */

        {
            item_amount.setFilters(new InputFilter[]{item_amount_and_price_filter});
            item_price.setFilters(new InputFilter[]{item_amount_and_price_filter});
            item_name.setFilters(new InputFilter[]{item_name_filter});
        }

        AddButton.setOnClickListener(view -> {
            try {
                String name = item_name.getText().toString();

                int amount = Integer.parseInt(item_amount.getText().toString());
                float price = Float.parseFloat(item_price.getText().toString());

                SQLiteDatabase database = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_NAME,name);
                contentValues.put(DBHelper.KEY_AMOUNT,amount);
                contentValues.put(DBHelper.KEY_PRICE,price);
                contentValues.put(DBHelper.KEY_DATE,sdf.format(new Date()));

                database.insert(DBHelper.TABLE_ITEM,null,contentValues);

            }
            catch (NumberFormatException ex){
                Toast.makeText(AddActivity.this, "Wrong data!",Toast.LENGTH_SHORT).show();
            }

        });

        /*---------------------------- Overview Last Button ---------------------------- */

        OverviewLastButton = findViewById(R.id.overview_last_btn);

        OverviewLastButton.setOnClickListener(view -> {

            Intent last_intent = new Intent(this, ItemOverview.class);

            SQLiteDatabase database = dbHelper.getWritableDatabase();

            Cursor cursor = database.query(DBHelper.TABLE_ITEM,null,null,null,null,null,null);

            cursor.moveToLast();

            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int AmountIndex = cursor.getColumnIndex(DBHelper.KEY_AMOUNT);
            int PriceIndex = cursor.getColumnIndex(DBHelper.KEY_PRICE);
            int DateIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);

            try {
                Item last_item = new Item(cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getInt(AmountIndex), cursor.getFloat(PriceIndex), cursor.getString(DateIndex));

                last_intent.putExtra("item", last_item);

                OpenItemOverviewLastActivity(last_intent);
            }
            catch (CursorIndexOutOfBoundsException ex) {
                Toast.makeText(AddActivity.this, "There is no records!",Toast.LENGTH_SHORT).show();
            }

        });

    }

    /*---------------------------- Navigation ---------------------------- */

    public boolean onOptionsItemSelected(MenuItem item){
        if(SideBar_Toogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

        if (menuItem.getItemId() == R.id.nav_browse) {
            OpenBrowseActivity();
        }
        if (menuItem.getItemId() == R.id.nav_add_item){
            OpenAddActivity();
        }
        if (menuItem.getItemId() == R.id.nav_export){
            OpenExportActivity();
        }
        if(menuItem.getItemId() == R.id.nav_clear_database){
            AlertDialogClearDatabase();
        }
        return false;
    }

    /*---------------------------- Menu Operations ---------------------------- */

    public void OpenBrowseActivity(){
        startActivity(new Intent(this, BrowseActivity.class));
    }
    public void OpenAddActivity(){
        startActivity(new Intent(this, AddActivity.class));
    }
    public void OpenExportActivity(){
        startActivity(new Intent(this, ExportActivity.class));
    }
    public void OpenItemOverviewLastActivity(Intent intent){
        startActivity(intent);
    }

    public void AlertDialogClearDatabase(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Clear Database");
        builder.setMessage("Are you sure you want to clear your database?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase database = dbHelper.getWritableDatabase();
                        database.delete(DBHelper.TABLE_ITEM, null,null);
                        Toast.makeText(AddActivity.this, "Database cleared.",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /*---------------------------- Touch Event ---------------------------- */
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