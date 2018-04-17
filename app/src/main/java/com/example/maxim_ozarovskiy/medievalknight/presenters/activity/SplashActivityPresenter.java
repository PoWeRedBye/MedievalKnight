package com.example.maxim_ozarovskiy.medievalknight.presenters.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.maxim_ozarovskiy.medievalknight.BuildConfig;
import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.data.DataBaseAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.SplashContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.CharacterInfo;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

import java.util.ArrayList;
import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.APP_PREFERENCES_ARMORY_IS_UPLOADED;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.APP_PREFERENCES_CHARACTER_ID;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.ARMOR;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.BOOTS;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.FALSE;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.GLOVES;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.HELM;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.SHIELD;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.SWORD;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.TRUE;

public class SplashActivityPresenter implements SplashContract.Presenter {

    private Context context;
    SplashContract.View view;
    private SharedPreferences mySettings;
    private DataBaseAdapter dbAdapter;
    private List<ArmorItems> armorItems;

    public SplashActivityPresenter(Context context, SplashContract.View view, SharedPreferences mySettings) {
        this.context = context;
        this.view = view;
        this.mySettings = mySettings;
    }

    private void uploadArmoryItems(List<ArmorItems> armorItemsList) {
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();

        dbAdapter.uploadArmor(armorItemsList);

        dbAdapter.close();

        SharedPreferences.Editor editor = mySettings.edit();
        editor.putString(APP_PREFERENCES_ARMORY_IS_UPLOADED, "YES");
        editor.apply();

        view.createNewCharacter();
    }

    private void getMyCharacter() {
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();
        CharacterInfo characterInfo = dbAdapter.getMyCharacterFromDB();
        armorItems = new ArrayList<>();
        armorItems = dbAdapter.getEquippedArmor(TRUE);
        Knight knight = new Knight();
        knight.setName(characterInfo.getCharacterName());
        knight.setId(characterInfo.getId());
        knight.setHp(characterInfo.getCharacterHP());
        knight.setBaseAttackPower(characterInfo.getCharacterBaseAttack());
        knight.setArmorItems(armorItems);
        int def = 0;
        int dmg = 0;
        int knightDmg = characterInfo.getCharacterBaseAttack();
        int knightDef = 0;
        for (int i = armorItems.size(); i > 0; i--) {
            if (armorItems.get(i-1).getDefenceBonus()>0) {
                def = armorItems.get(i - 1).getDefenceBonus() + knightDef;
            } else if (armorItems.get(i-1).getAttackBonus() > 0){
            dmg = armorItems.get(i-1).getAttackBonus() + knightDmg;
            }
        }
        knight.setAttackPower(dmg);
        knight.setDefence(def);

        view.callbackMyCharacter(knight);
        dbAdapter.close();
    }

    private void createArmoryItemsList() {
        ArmorItems standartArmor = new ArmorItems();
        standartArmor.setItemName("Leather Armor");
        standartArmor.setCategory(ARMOR);
        standartArmor.setAttackBonus(0);
        standartArmor.setDefenceBonus(3);
        standartArmor.setEquipped(FALSE);
        Uri standartArmorImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.standart_armor);
        standartArmor.setIcon(String.valueOf(standartArmorImage));

        ArmorItems standartHelm = new ArmorItems();
        standartHelm.setItemName("Leather Helmet");
        standartHelm.setCategory(HELM);
        standartHelm.setDefenceBonus(3);
        standartHelm.setAttackBonus(0);
        standartHelm.setEquipped(FALSE);
        Uri standartHelmImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.standart_helmet);
        standartHelm.setIcon(String.valueOf(standartHelmImage));

        ArmorItems standartBoots = new ArmorItems();
        standartBoots.setItemName("Leather Boots");
        standartBoots.setCategory(BOOTS);
        standartBoots.setDefenceBonus(3);
        standartBoots.setAttackBonus(0);
        standartBoots.setEquipped(FALSE);
        Uri standartBootsImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.standart_boots);
        standartBoots.setIcon(String.valueOf(standartBootsImage));

        ArmorItems standartGloves = new ArmorItems();
        standartGloves.setItemName("Leather Gloves");
        standartGloves.setCategory(GLOVES);
        standartGloves.setDefenceBonus(3);
        standartGloves.setAttackBonus(0);
        standartGloves.setEquipped(FALSE);
        Uri standartGlovesImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.standart_gloves);
        standartGloves.setIcon(String.valueOf(standartGlovesImage));

        ArmorItems standartShield = new ArmorItems();
        standartShield.setItemName("Wooden Shield");
        standartShield.setCategory(SHIELD);
        standartShield.setDefenceBonus(5);
        standartShield.setEquipped(FALSE);
        Uri standartShieldImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.standart_shield);
        standartShield.setIcon(String.valueOf(standartShieldImage));

        ArmorItems standartSword = new ArmorItems();
        standartSword.setItemName("Iron Sword");
        standartSword.setCategory(SWORD);
        standartSword.setAttackBonus(20);
        standartSword.setDefenceBonus(0);
        standartSword.setEquipped(FALSE);
        Uri standartSwordImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.standart_sword);
        standartSword.setIcon(String.valueOf(standartSwordImage));

        ArmorItems angelArmor = new ArmorItems();
        angelArmor.setItemName("Angel Armor");
        angelArmor.setCategory(ARMOR);
        angelArmor.setDefenceBonus(5);
        angelArmor.setAttackBonus(0);
        angelArmor.setEquipped(FALSE);
        Uri angelArmorImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.armor2);
        angelArmor.setIcon(String.valueOf(angelArmorImage));

        ArmorItems angelHelm = new ArmorItems();
        angelHelm.setItemName("Angel Helmet");
        angelHelm.setCategory(HELM);
        angelHelm.setDefenceBonus(5);
        angelHelm.setAttackBonus(0);
        angelHelm.setEquipped(FALSE);
        Uri angelHelmImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.helmet2);
        angelHelm.setIcon(String.valueOf(angelHelmImage));

        ArmorItems angelGloves = new ArmorItems();
        angelGloves.setItemName("Angel Gloves");
        angelGloves.setCategory(GLOVES);
        angelGloves.setDefenceBonus(5);
        angelGloves.setAttackBonus(0);
        angelGloves.setEquipped(FALSE);
        Uri angelGlovesImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.gloves2);
        angelGloves.setIcon(String.valueOf(angelGlovesImage));

        ArmorItems angelBoots = new ArmorItems();
        angelBoots.setItemName("Angel Boots");
        angelBoots.setCategory(BOOTS);
        angelBoots.setDefenceBonus(5);
        angelBoots.setAttackBonus(0);
        angelBoots.setEquipped(FALSE);
        Uri angelBootsImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.boots2);
        angelBoots.setIcon(String.valueOf(angelBootsImage));

        ArmorItems angelShield = new ArmorItems();
        angelShield.setItemName("Angel Shield");
        angelShield.setCategory(SHIELD);
        angelShield.setDefenceBonus(10);
        angelShield.setAttackBonus(0);
        angelShield.setEquipped(FALSE);
        Uri angelShieldImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.shield2);
        angelShield.setIcon(String.valueOf(angelShieldImage));

        ArmorItems angelSword = new ArmorItems();
        angelSword.setItemName("Angel Sword");
        angelSword.setCategory(SWORD);
        angelSword.setAttackBonus(30);
        angelSword.setDefenceBonus(0);
        angelSword.setEquipped(FALSE);
        Uri angelSwordImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.sword2);
        angelSword.setIcon(String.valueOf(angelSwordImage));

        ArmorItems infernalArmor = new ArmorItems();
        infernalArmor.setItemName("Infernal Armor");
        infernalArmor.setCategory(ARMOR);
        infernalArmor.setDefenceBonus(7);
        infernalArmor.setAttackBonus(0);
        infernalArmor.setEquipped(FALSE);
        Uri infernalArmorImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.armor);
        infernalArmor.setIcon(String.valueOf(infernalArmorImage));

        ArmorItems infernalHelm = new ArmorItems();
        infernalHelm.setItemName("Infernal Helmet");
        infernalHelm.setCategory(HELM);
        infernalHelm.setDefenceBonus(7);
        infernalHelm.setAttackBonus(0);
        infernalHelm.setEquipped(FALSE);
        Uri infernalHelmImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.helmet);
        infernalHelm.setIcon(String.valueOf(infernalHelmImage));

        ArmorItems infernalGloves = new ArmorItems();
        infernalGloves.setItemName("Infernal Gloves");
        infernalGloves.setCategory(GLOVES);
        infernalGloves.setDefenceBonus(7);
        infernalGloves.setAttackBonus(0);
        infernalGloves.setEquipped(FALSE);
        Uri infernalGlovesImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.gloves);
        infernalGloves.setIcon(String.valueOf(infernalGlovesImage));

        ArmorItems infernalBoots = new ArmorItems();
        infernalBoots.setItemName("Infernal Boots");
        infernalBoots.setCategory(BOOTS);
        infernalBoots.setDefenceBonus(7);
        infernalBoots.setAttackBonus(0);
        infernalBoots.setEquipped(FALSE);
        Uri infernalBootsImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.boots);
        infernalBoots.setIcon(String.valueOf(infernalBootsImage));

        ArmorItems infernalShield = new ArmorItems();
        infernalShield.setItemName("Infernal Shield");
        infernalShield.setCategory(SHIELD);
        infernalShield.setDefenceBonus(10);
        infernalShield.setAttackBonus(0);
        infernalShield.setEquipped(FALSE);
        Uri infernalShieldImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.shield);
        infernalShield.setIcon(String.valueOf(infernalShieldImage));

        ArmorItems infernalSword = new ArmorItems();
        infernalSword.setItemName("Infernal Sword");
        infernalSword.setCategory(SWORD);
        infernalSword.setAttackBonus(40);
        infernalSword.setDefenceBonus(-15);
        infernalSword.setEquipped(FALSE);
        Uri infernalSwordImage = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.sword);
        infernalSword.setIcon(String.valueOf(infernalSwordImage));


        List<ArmorItems> armorItems = new ArrayList<>();
        armorItems.add(standartArmor);
        armorItems.add(standartHelm);
        armorItems.add(standartGloves);
        armorItems.add(standartBoots);
        armorItems.add(standartShield);
        armorItems.add(standartSword);

        armorItems.add(angelArmor);
        armorItems.add(angelHelm);
        armorItems.add(angelGloves);
        armorItems.add(angelBoots);
        armorItems.add(angelShield);
        armorItems.add(angelSword);

        armorItems.add(infernalArmor);
        armorItems.add(infernalHelm);
        armorItems.add(infernalGloves);
        armorItems.add(infernalBoots);
        armorItems.add(infernalShield);
        armorItems.add(infernalSword);

        uploadArmoryItems(armorItems);

    }

    private void getCharacterData() {
        if (mySettings.contains(APP_PREFERENCES_CHARACTER_ID)) {
            getMyCharacter();
        } else if (mySettings.contains(APP_PREFERENCES_ARMORY_IS_UPLOADED)){
            view.createNewCharacter();
        } else {
            createArmoryItemsList();
        }

    }

    @Override
    public void callMyCharacter() {
        getCharacterData();
    }
}
