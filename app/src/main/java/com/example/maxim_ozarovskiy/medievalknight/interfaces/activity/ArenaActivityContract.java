package com.example.maxim_ozarovskiy.medievalknight.interfaces.activity;

import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

import java.util.List;

public interface ArenaActivityContract {

    interface View {
        void callbackDuelInformation(Knight myKnight, Knight otherKnight);
    }

    interface Presenter {
        void prepareToBattle(Knight knight);
    }

}
