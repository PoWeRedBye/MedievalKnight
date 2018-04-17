package com.example.maxim_ozarovskiy.medievalknight.interfaces.fragment;

import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;

import java.util.List;

public interface BootsFragmentContract {
    interface View{
        void callbackItems(List<ArmorItems> armorItemsList);
    }

    interface Presenter{
        void callItemsFromArmory(String boots);
        void equipNewItem(ArmorItems armorItems);
    }
}
