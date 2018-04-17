package com.example.maxim_ozarovskiy.medievalknight.presenters.activity;

import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.CharacterInfoContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.ARMOR;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.BOOTS;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.GLOVES;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.HELM;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.SHIELD;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.SWORD;

public class CharacterInfoActivityPresenter implements CharacterInfoContract.Presenter {

    CharacterInfoContract.View view;
    private Context context;
    private List<ArmorItems> armorItemsList;
    private Knight knight;
    private String armorName;
    private String helmName;
    private String glovesName;
    private String bootsName;
    private String swordName;
    private String shieldName;

    public CharacterInfoActivityPresenter(Context context, CharacterInfoContract.View view) {
        this.context = context;
        this.view = view;
    }

    private void getItemsList(Knight knight) {
        armorItemsList = new ArrayList<>();
        armorItemsList.addAll(knight.getArmorItems());
        for (int i = knight.getArmorItems().size(); i > 0; i--) {
            if (knight.getArmorItems().get(i - 1).getCategory().equals(ARMOR)) {
                armorName = knight.getArmorItems().get(i - 1).getItemName();
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals(HELM)) {
                helmName = knight.getArmorItems().get(i - 1).getItemName();
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals(GLOVES)) {
                glovesName = knight.getArmorItems().get(i - 1).getItemName();
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals(BOOTS)) {
                bootsName = knight.getArmorItems().get(i - 1).getItemName();
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals(SWORD)) {
                swordName = knight.getArmorItems().get(i - 1).getItemName();
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals(SHIELD)) {
                shieldName = knight.getArmorItems().get(i - 1).getItemName();
            }
        }
        view.callbackEquippedList(knight,
                armorItemsList,
                armorName,
                helmName,
                glovesName,
                bootsName,
                swordName,
                shieldName);
    }

    @Override
    public void callEquippedList(Knight knight) {
        getItemsList(knight);
    }
}
