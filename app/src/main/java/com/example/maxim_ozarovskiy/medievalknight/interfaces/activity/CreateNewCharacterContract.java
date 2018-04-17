package com.example.maxim_ozarovskiy.medievalknight.interfaces.activity;

import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

import java.util.List;

public interface CreateNewCharacterContract {

    interface View{
        void callbackNewCharacter(Knight knight, List<ArmorItems> armorItems);
        void callbackError(String error);
    }

    interface Presenter{
        void createNewCharacter(String characterName);
    }
}
