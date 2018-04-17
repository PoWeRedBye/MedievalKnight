package com.example.maxim_ozarovskiy.medievalknight.presenters.activity;

import android.content.Context;

import com.example.maxim_ozarovskiy.medievalknight.data.DataBaseAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.MainActivityContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.CharacterInfo;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    MainActivityContract.View view;
    private Context context;
    private DataBaseAdapter dbAdapter;
    private CharacterInfo characterInfo;
    private Knight knight;
    private List<ArmorItems> armorItems;

    public MainActivityPresenter(Context context, MainActivityContract.View view) {
        this.context = context;
        this.view = view;
    }

    private void getMyCharacter() {
        dbAdapter = new DataBaseAdapter(context);
        dbAdapter.open();
        characterInfo = dbAdapter.getMyCharacterFromDB();
        armorItems = dbAdapter.getEquippedArmor("true");
        knight = new Knight();
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
            def = armorItems.get(i-1).getDefenceBonus() + knightDef;
            knightDef = def;
            if (armorItems.get(i - 1).getAttackBonus() > 0) {
                dmg = armorItems.get(i-1).getAttackBonus() + knightDmg;
            }

        }
        knight.setAttackPower(dmg);
        knight.setDefence(def);

        view.callbackMyCharacter(knight);

    }

    @Override
    public void updateMyCharacterData() {
        getMyCharacter();
    }
}
