package com.example.maxim_ozarovskiy.medievalknight.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.fragments.BootsMarketplaceItemsFragment;
import com.example.maxim_ozarovskiy.medievalknight.fragments.ChestMarketplaceItemsFragment;
import com.example.maxim_ozarovskiy.medievalknight.fragments.GlovesMarketplaceItemsFragment;
import com.example.maxim_ozarovskiy.medievalknight.fragments.HelmetMarketplaceItemsFragment;
import com.example.maxim_ozarovskiy.medievalknight.fragments.WeaponAndShieldFragment;

public class MarketplaceActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketpalce_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();

        bottomNavigation.setSelectedItemId(R.id.weapon_and_shied_fragment);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.weapon_and_shied_fragment:
                        fragment = new WeaponAndShieldFragment();
                        setTitle(R.string.weapon_and_shield);
                        break;
                    case R.id.chest_armor_fragment:
                        fragment = new ChestMarketplaceItemsFragment();
                        setTitle(R.string.chest_armory);
                        break;
                    case R.id.gloves_fragment:
                        fragment = new GlovesMarketplaceItemsFragment();
                        setTitle(R.string.gloves_armory);
                        break;
                    case R.id.boots_fragment:
                        fragment = new BootsMarketplaceItemsFragment();
                        setTitle(R.string.boots_armory);
                        break;
                    case R.id.helmet_fragment:
                        fragment = new HelmetMarketplaceItemsFragment();
                        setTitle(R.string.helmet_armory);
                }
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

    }

    private void initUI() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        bottomNavigation = findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        fragment = new WeaponAndShieldFragment();
        transaction.replace(R.id.main_container,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
