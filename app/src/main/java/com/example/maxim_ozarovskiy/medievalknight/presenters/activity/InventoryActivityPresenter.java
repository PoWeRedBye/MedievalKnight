package com.example.maxim_ozarovskiy.medievalknight.presenters.activity;

import android.content.Context;

import com.example.maxim_ozarovskiy.medievalknight.data.DataBaseAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.CharacterInventoryContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;

import java.util.ArrayList;
import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.TRUE;

public class InventoryActivityPresenter implements CharacterInventoryContract.Presenter{

    CharacterInventoryContract.View view;
    private Context context;
    private DataBaseAdapter dbAdapter;
    private List<ArmorItems> armorItemsList;

    public InventoryActivityPresenter(Context context, CharacterInventoryContract.View view){
        this.context = context;
        this.view = view;
    }

    private void getInventoryItems(){
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();
        armorItemsList = new ArrayList<>();
        armorItemsList = dbAdapter.getEquippedArmor(TRUE);
        dbAdapter.close();
        view.callbackInventoryItems(armorItemsList);
    }

    @Override
    public void callInventoryItems() {
        getInventoryItems();
    }
}
