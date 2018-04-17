package com.example.maxim_ozarovskiy.medievalknight.interfaces.activity;

import android.content.SharedPreferences;

import com.example.maxim_ozarovskiy.medievalknight.model.CharacterInfo;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

public interface SplashContract {

    interface View{
        void callbackMyCharacter(Knight knight);
        void createNewCharacter();
    }

    interface Presenter{
        void callMyCharacter();
    }

}
