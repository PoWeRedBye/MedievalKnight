package com.example.maxim_ozarovskiy.medievalknight.presenters.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.data.DataBaseAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.CreateNewCharacterContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.CharacterInfo;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

import java.util.ArrayList;
import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.APP_PREFERENCES_CHARACTER_ID;
import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.TRUE;

public class CreateNewCharacterPresenter implements CreateNewCharacterContract.Presenter{

    private Context context;
    CreateNewCharacterContract.View view;
    private SharedPreferences mySettings;
    private DataBaseAdapter dbAdapter;
    private List<ArmorItems> armorItemsList;
    private List<ArmorItems> equippedItems;
    private ArmorItems newbieArmor;
    private ArmorItems newbieHelm;
    private ArmorItems newbieGloves;
    private ArmorItems newbieBoots;
    private ArmorItems newbieShield;
    private ArmorItems newbieSword;
    private CharacterInfo characterInfo;
    private Knight knight;

    public CreateNewCharacterPresenter(Context context, CreateNewCharacterContract.View view, SharedPreferences mySettings){
        this.context = context;
        this.view = view;
        this.mySettings = mySettings;
    }

    private void saveMyKnightToSharedPreffs(int id){
        SharedPreferences.Editor editor = mySettings.edit();
        editor.putInt(APP_PREFERENCES_CHARACTER_ID, id);
        editor.apply();
        view.callbackNewCharacter();
    }

    private void getEquippedItems(){
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();

        armorItemsList = dbAdapter.getAllArmorItemsFromDB();
        equippedItems = new ArrayList<>();
        for (int i = armorItemsList.size(); i>0 ; i--){
            if (armorItemsList.get(i-1).getItemName().equals("Leather Armor")){
                newbieArmor = new ArmorItems();
                newbieArmor.setItemName(armorItemsList.get(i-1).getItemName());
                newbieArmor.setId(armorItemsList.get(i-1).getId());
                newbieArmor.setAttackBonus(armorItemsList.get(i-1).getAttackBonus());
                newbieArmor.setDefenceBonus(armorItemsList.get(i-1).getDefenceBonus());
                newbieArmor.setIcon(armorItemsList.get(i-1).getIcon());
                newbieArmor.setCategory(armorItemsList.get(i-1).getCategory());
                armorItemsList.get(i-1).setEquipped(TRUE);
                dbAdapter.updateArmorItem(armorItemsList.get(i-1).getId(), armorItemsList.get(i-1));
                equippedItems.add(newbieArmor);
            } else if (armorItemsList.get(i-1).getItemName().equals("Leather Helmet")){
                newbieHelm = new ArmorItems();
                newbieHelm.setItemName(armorItemsList.get(i-1).getItemName());
                newbieHelm.setId(armorItemsList.get(i-1).getId());
                newbieHelm.setAttackBonus(armorItemsList.get(i-1).getAttackBonus());
                newbieHelm.setDefenceBonus(armorItemsList.get(i-1).getDefenceBonus());
                newbieHelm.setIcon(armorItemsList.get(i-1).getIcon());
                newbieHelm.setCategory(armorItemsList.get(i-1).getCategory());
                armorItemsList.get(i-1).setEquipped(TRUE);
                dbAdapter.updateArmorItem(armorItemsList.get(i-1).getId(), armorItemsList.get(i-1));
                equippedItems.add(newbieHelm);
            } else if (armorItemsList.get(i-1).getItemName().equals("Leather Gloves")){
                newbieGloves = new ArmorItems();
                newbieGloves.setItemName(armorItemsList.get(i-1).getItemName());
                newbieGloves.setId(armorItemsList.get(i-1).getId());
                newbieGloves.setAttackBonus(armorItemsList.get(i-1).getAttackBonus());
                newbieGloves.setDefenceBonus(armorItemsList.get(i-1).getDefenceBonus());
                newbieGloves.setIcon(armorItemsList.get(i-1).getIcon());
                newbieGloves.setCategory(armorItemsList.get(i-1).getCategory());
                armorItemsList.get(i-1).setEquipped(TRUE);
                dbAdapter.updateArmorItem(armorItemsList.get(i-1).getId(), armorItemsList.get(i-1));
                equippedItems.add(newbieGloves);
            } else if (armorItemsList.get(i-1).getItemName().equals("Leather Boots")){
                newbieBoots = new ArmorItems();
                newbieBoots.setItemName(armorItemsList.get(i-1).getItemName());
                newbieBoots.setId(armorItemsList.get(i-1).getId());
                newbieBoots.setAttackBonus(armorItemsList.get(i-1).getAttackBonus());
                newbieBoots.setDefenceBonus(armorItemsList.get(i-1).getDefenceBonus());
                newbieBoots.setIcon(armorItemsList.get(i-1).getIcon());
                newbieBoots.setCategory(armorItemsList.get(i-1).getCategory());
                armorItemsList.get(i-1).setEquipped(TRUE);
                dbAdapter.updateArmorItem(armorItemsList.get(i-1).getId(), armorItemsList.get(i-1));
                equippedItems.add(newbieBoots);
            } else if (armorItemsList.get(i-1).getItemName().equals("Wooden Shield")) {
                newbieShield = new ArmorItems();
                newbieShield.setItemName(armorItemsList.get(i-1).getItemName());
                newbieShield.setId(armorItemsList.get(i-1).getId());
                newbieShield.setAttackBonus(armorItemsList.get(i-1).getAttackBonus());
                newbieShield.setDefenceBonus(armorItemsList.get(i-1).getDefenceBonus());
                newbieShield.setIcon(armorItemsList.get(i-1).getIcon());
                newbieShield.setCategory(armorItemsList.get(i-1).getCategory());
                armorItemsList.get(i-1).setEquipped(TRUE);
                dbAdapter.updateArmorItem(armorItemsList.get(i-1).getId(), armorItemsList.get(i-1));
                equippedItems.add(newbieShield);
            } else if (armorItemsList.get(i-1).getItemName().equals("Iron Sword")) {
                newbieSword = new ArmorItems();
                newbieSword.setItemName(armorItemsList.get(i-1).getItemName());
                newbieSword.setId(armorItemsList.get(i-1).getId());
                newbieSword.setAttackBonus(armorItemsList.get(i-1).getAttackBonus());
                newbieSword.setDefenceBonus(armorItemsList.get(i-1).getDefenceBonus());
                newbieSword.setIcon(armorItemsList.get(i-1).getIcon());
                newbieSword.setCategory(armorItemsList.get(i-1).getCategory());
                armorItemsList.get(i-1).setEquipped(TRUE);
                dbAdapter.updateArmorItem(armorItemsList.get(i-1).getId(), armorItemsList.get(i-1));
                equippedItems.add(newbieSword);
            }
        }
    }

    private void createMyCharacter(String characterName){
        getEquippedItems();
        int knightDefence = newbieArmor.getDefenceBonus() +
                newbieBoots.getDefenceBonus() +
                newbieGloves.getDefenceBonus() +
                newbieHelm.getDefenceBonus() +
                newbieShield.getDefenceBonus();
        int knightAttack = 15;
        knight = new Knight();
        knight.setName(characterName);
        knight.setBaseAttackPower(knightAttack);
        knight.setAttackPower(newbieSword.getAttackBonus() + knightAttack);
        knight.setDefence(knightDefence);
        knight.setHp(100);
        knight.setArmorItems(equippedItems);

        dbAdapter.createNewCharacter(knight);
        dbAdapter.getMyCharacterFromDB();

        characterInfo = dbAdapter.getMyCharacterFromDB();

        saveMyKnightToSharedPreffs(characterInfo.getId());

    }

    @Override
    public void createNewCharacter(String characterName) {
        if (TextUtils.isEmpty(characterName)){
            view.callbackError("Character name is empty");
        } else {
        createMyCharacter(characterName);
        }
    }
}
