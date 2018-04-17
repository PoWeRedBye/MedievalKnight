package com.example.maxim_ozarovskiy.medievalknight.presenters.fragment;

import android.content.Context;

import com.example.maxim_ozarovskiy.medievalknight.data.DataBaseAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.fragment.HelmFragmentContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;

import java.util.ArrayList;
import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.FALSE;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.HELM;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.SHIELD;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.SWORD;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.TRUE;

public class HelmetMarketplaceFragmentPresenter implements HelmFragmentContract.Presenter{

    HelmFragmentContract.View view;
    private Context context;
    private DataBaseAdapter dbAdapter;
    private List<ArmorItems> armorItemsList;
    private List<ArmorItems> equippedItems;

    public HelmetMarketplaceFragmentPresenter(Context context, HelmFragmentContract.View view){
        this.context = context;
        this.view = view;
    }

    private void getItemsFromArmory(String helm){
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();
        armorItemsList = new ArrayList<>();
        armorItemsList = dbAdapter.getArmorItemsByCategoryFromDB(helm);
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
        getItemsFromArmory(HELM);
    }


    @Override
    public void callItemsFromArmory(String helm) {
        getItemsFromArmory(helm);
    }

    @Override
    public void equipNewItem(ArmorItems armorItems) {
        equipNewItems(armorItems);
    }
}
