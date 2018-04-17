package com.example.maxim_ozarovskiy.medievalknight.interfaces.activity;

import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

public interface MainActivityContract {

    interface View{
        void callbackMyCharacter(Knight knight);
    }

    interface Presenter{
        void updateMyCharacterData();
    }
}
