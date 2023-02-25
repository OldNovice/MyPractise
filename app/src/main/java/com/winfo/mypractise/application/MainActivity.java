package com.winfo.mypractise.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity3;
import com.winfo.mypractise.recycler.RecyclerActivity;

public class MainActivity extends BaseActivity3 {

    private DrawerLayout drawerLayout;
    private FloatingActionButton flyBnt;
    @Override
    protected int intiLayout() {
        return R.layout.activity_main;
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.main_drawer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_24);
        }

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            return true;
        });
        flyBnt=findViewById(R.id.fly_bnt);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClickEvent() {
        flyBnt.setOnClickListener(this::onClicks);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClicks(View v) {
        if (v.getId() == R.id.fly_bnt) {
            startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @SuppressLint({"NonConstantResourceId", "RtlHardcoded"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.delete:
                Toast.makeText(this, "123456", Toast.LENGTH_SHORT).show();
                break;
            case R.id.backup:
                break;
        }
        return true;
    }
}