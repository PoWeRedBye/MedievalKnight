package com.example.maxim_ozarovskiy.medievalknight.interfaces.activity;


public interface CreateNewCharacterContract {

    interface View{
        void callbackNewCharacter();
        void callbackError(String error);
    }

    interface Presenter{
        void createNewCharacter(String characterName);
    }
}
