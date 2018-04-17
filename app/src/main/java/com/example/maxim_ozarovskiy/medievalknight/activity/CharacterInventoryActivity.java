package com.example.maxim_ozarovskiy.medievalknight.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.adapters.ArmorItemsAdapter;
import com.example.maxim_ozarovskiy.medievalknight.adapters.InventoryItemsAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.CharacterInventoryContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.presenters.activity.InventoryActivityPresenter;

import java.util.List;

public class CharacterInventoryActivity extends AppCompatActivity implements CharacterInventoryContract.View{

    private InventoryActivityPresenter presenter;
    private InventoryItemsAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();
        presenter = new InventoryActivityPresenter(this,this);
        presenter.callInventoryItems();

    }

    private void setNewRecyclerAdapter(List<ArmorItems> newList){
        adapter = new InventoryItemsAdapter(this, newList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initUI(){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        recyclerView = findViewById(R.id.inventory_recycler);
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

    @Override
    public void callbackInventoryItems(List<ArmorItems> armorItemsList) {
        setNewRecyclerAdapter(armorItemsList);
    }
}
