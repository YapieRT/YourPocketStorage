package com.example.yourpocketstorage;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout SideBar;

    private ActionBarDrawerToggle SideBar_Toogle;

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.nav_action_bar);

        setSupportActionBar(mToolBar);

        SideBar = (DrawerLayout) findViewById(R.id.drawerLayout);
        SideBar_Toogle = new ActionBarDrawerToggle(this, SideBar,R.string.sidebar_open,R.string.sidebar_close);

        SideBar.addDrawerListener(SideBar_Toogle);

        SideBar_Toogle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(SideBar_Toogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}