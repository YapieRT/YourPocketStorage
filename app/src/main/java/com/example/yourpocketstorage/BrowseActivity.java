package com.example.yourpocketstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class BrowseActivity extends AppCompatActivity {

    private DrawerLayout SideBar;

    private ActionBarDrawerToggle SideBar_Toogle;

    private NavigationView SideBar_NavView;

    private Toolbar mToolBar;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_activity);

        SideBar = findViewById(R.id.drawerLayout);
        mToolBar = findViewById(R.id.nav_action_bar);
        SideBar_NavView = findViewById(R.id.nav_view);

        /*---------------------------- ToolBar ---------------------------- */
        setSupportActionBar(mToolBar);

        SideBar_Toogle = new ActionBarDrawerToggle(this, SideBar,R.string.sidebar_open,R.string.sidebar_close);

        SideBar.addDrawerListener(SideBar_Toogle);

        SideBar_Toogle.syncState();

        /*---------------------------- Navigation ---------------------------- */

        SideBar_NavView.bringToFront();

        SideBar_NavView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /*---------------------------- Database ---------------------------- */

        dbHelper =  new DBHelper(this);
    }

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

    public void OpenBrowseActivity(){
        startActivity(new Intent(this, BrowseActivity.class));
    }
    public void OpenAddActivity(){
        startActivity(new Intent(this, AddActivity.class));
    }
    public void OpenExportActivity(){
        startActivity(new Intent(this, ExportActivity.class));
    }
    public void OpenItemOverviewActivity(){
        startActivity(new Intent(this, ItemOverview.class));
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
                        Toast.makeText(BrowseActivity.this, "Database cleared.", Toast.LENGTH_SHORT).show();
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
}