package com.example.maxim_ozarovskiy.medievalknight.interfaces.activity;

import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;

import java.util.List;

public interface CharacterInventoryContract {

    interface View{
        void callbackInventoryItems(List<ArmorItems> armorItemsList);
    }

    interface Presenter{
        void callInventoryItems();
    }

}
