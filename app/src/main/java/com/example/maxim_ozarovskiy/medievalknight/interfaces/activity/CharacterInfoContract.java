package com.example.maxim_ozarovskiy.medievalknight.interfaces.activity;

import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

import java.util.List;

public interface CharacterInfoContract {

    interface View{
        void callbackEquippedList(Knight knight,
                                  List<ArmorItems> armorItemsList,
                                  String armorName,
                                  String helmName,
                                  String glovesName,
                                  String bootsName,
                                  String swordName,
                                  String shieldName);
    }

    interface Presenter{
        void callEquippedList(Knight knight);
    }

}
