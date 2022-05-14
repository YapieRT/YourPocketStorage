package com.example.yourpocketstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class AddActivity extends AppCompatActivity {
    private DrawerLayout SideBar;

    private ActionBarDrawerToggle SideBar_Toogle;

    private NavigationView SideBar_NavView;

    private Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        SideBar = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolBar = (Toolbar) findViewById(R.id.nav_action_bar);
        SideBar_NavView = (NavigationView) findViewById(R.id.nav_view);

        /*---------------------------- ToolBar ---------------------------- */
        setSupportActionBar(mToolBar);

        SideBar_Toogle = new ActionBarDrawerToggle(this, SideBar,R.string.sidebar_open,R.string.sidebar_close);

        SideBar.addDrawerListener(SideBar_Toogle);

        SideBar_Toogle.syncState();

        /*---------------------------- Navigation ---------------------------- */

        SideBar_NavView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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
        return false;
    }

    public void OpenBrowseActivity(){
        Intent intent = new Intent(this, BrowseActivity.class);
        startActivity(intent);
    }
    public void OpenAddActivity(){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
    public void OpenExportActivity(){
        Intent intent = new Intent(this, ExportActivity.class);
        startActivity(intent);
    }
}