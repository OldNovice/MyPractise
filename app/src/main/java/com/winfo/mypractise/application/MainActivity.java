package com.winfo.mypractise.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity;
import com.winfo.mypractise.recycler.RecyclerActivity;

public class MainActivity extends BaseActivity {

    private Button btu1,btu3;
    private FloatingActionButton  btu2;
    private DrawerLayout drawerLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btu1 = findViewById(R.id.but1);
        btu2 = findViewById(R.id.but2);
        btu3 = findViewById(R.id.but3);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.main_drawer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_24);
        }
    }

    @Override
    protected void initData() {
        btu1.setOnClickListener(this);
        btu3.setOnClickListener(this);
    }

    @Override
    protected void Business() {
        btu2.setOnClickListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "ShowToast"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but1:
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
                break;
            case R.id.but2:
                Snackbar.make(v, "", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", view -> showToast("来了老弟"))
                        .show();
                showToast("12132");
                break;
            case R.id.but3:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                showToast("backup");
                break;
            case R.id.delete:
                showToast("delete");
                break;
            case R.id.settings:
                showToast("settings");
                break;
            case R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}