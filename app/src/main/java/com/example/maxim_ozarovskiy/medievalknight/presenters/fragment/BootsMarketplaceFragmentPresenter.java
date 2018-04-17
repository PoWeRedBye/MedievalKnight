package com.example.maxim_ozarovskiy.medievalknight.presenters.fragment;

import android.content.Context;

import com.example.maxim_ozarovskiy.medievalknight.data.DataBaseAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.fragment.ArmorFragmentContract;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.fragment.BootsFragmentContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;

import java.util.ArrayList;
import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.BOOTS;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.FALSE;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.GLOVES;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.TRUE;

public class BootsMarketplaceFragmentPresenter implements BootsFragmentContract.Presenter{

    BootsFragmentContract.View view;
    private Context context;
    private DataBaseAdapter dbAdapter;
    private List<ArmorItems> armorItemsList;
    private List<ArmorItems> equippedItems;

    public BootsMarketplaceFragmentPresenter(Context context, BootsFragmentContract.View view){
        this.context = context;
        this.view = view;
    }

    private void getItemsFromArmory(String boots){
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();
        armorItemsList = new ArrayList<>();
        armorItemsList = dbAdapter.getArmorItemsByCategoryFromDB(boots);
        dbAdapter.close();
        for (int i = armorItemsList.size(); i>0 ; i--){
            if (armorItemsList.get(i-1).isEquipped().equals(TRUE)){
                armorItemsList.remove(i-1);
            }
        }
        view.callbackItems(armorItemsList);
    }

    private void equipNewItems(ArmorItems armorItems){
        equippedItems = new ArrayList<>();
        armorItems.setEquipped(TRUE);
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();
        equippedItems = dbAdapter.getEquippedArmor(TRUE);
        for (int i = equippedItems.size(); i>0 ; i--){
            if (equippedItems.get(i-1).getCategory().equals(armorItems.getCategory())){
                equippedItems.get(i-1).setEquipped(FALSE);
                dbAdapter.updateArmorItem(equippedItems.get(i-1).getId(), equippedItems.get(i-1));
            }
        }
        dbAdapter.updateArmorItem(armorItems.getId(),armorItems);
        dbAdapter.close();
        getItemsFromArmory(BOOTS);
    }

    @Override
    public void callItemsFromArmory(String boots) {
        getItemsFromArmory(boots);
    }

    @Override
    public void equipNewItem(ArmorItems armorItems) {
        equipNewItems(armorItems);
    }
}