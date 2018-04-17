package com.example.maxim_ozarovskiy.medievalknight.presenters.fragment;

import android.content.Context;

import com.example.maxim_ozarovskiy.medievalknight.data.DataBaseAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.fragment.WeaponAndShieldFragmentContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;

import java.util.ArrayList;
import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.FALSE;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.SHIELD;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.SWORD;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.TRUE;

public class WeaponAndShieldMarketplaceFragmentPresenter implements WeaponAndShieldFragmentContract.Presenter{

    WeaponAndShieldFragmentContract.View view;
    private Context context;
    private DataBaseAdapter dbAdapter;
    private List<ArmorItems> weaponList;
    private List<ArmorItems> shieldList;
    private List<ArmorItems> armorItemsList;
    private List<ArmorItems> equippedItems;

    public WeaponAndShieldMarketplaceFragmentPresenter(Context context, WeaponAndShieldFragmentContract.View view){
        this.context = context;
        this.view = view;
    }

    private void getItemsFromArmory(String weapon, String shield){
        weaponList = new ArrayList<>();
        shieldList = new ArrayList<>();
        armorItemsList = new ArrayList<>();
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();
        weaponList = dbAdapter.getArmorItemsByCategoryFromDB(weapon);
        shieldList = dbAdapter.getArmorItemsByCategoryFromDB(shield);
        armorItemsList.addAll(weaponList);
        armorItemsList.addAll(shieldList);
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
        getItemsFromArmory(SWORD,SHIELD);
    }

    @Override
    public void callItemsFromArmory(String weapon, String shield) {
        getItemsFromArmory(weapon,shield);
    }

    @Override
    public void equipNewItem(ArmorItems armorItems) {
        equipNewItems(armorItems);
    }
}
