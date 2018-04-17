package com.example.maxim_ozarovskiy.medievalknight.presenters.activity;

import android.content.Context;
import android.os.AsyncTask;

import com.example.maxim_ozarovskiy.medievalknight.activity.ArenaActivity;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.ArenaActivityContract;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

import java.util.ArrayList;
import java.util.List;

public class ArenaActivityPresenter implements ArenaActivityContract.Presenter{

    private Context context;
    ArenaActivityContract.View view;
    private Knight myKnight;
    private Knight otherKnight;


    public ArenaActivityPresenter(Context context, ArenaActivityContract.View view){
        this.context = context;
        this.view = view;
    }

    private void createOpponent(){
        otherKnight = new Knight();
        otherKnight.setName("Some random Knight");
        otherKnight.setAttackPower(30 + (int) (Math.random()*15));
        otherKnight.setDefence(22 + (int)(Math.random()*16));
        otherKnight.setHp(100);
        sendLogs(myKnight,otherKnight);
    }


    public void sendLogs(Knight my, Knight other){
        view.callbackDuelInformation(my,other);
    }

    @Override
    public void prepareToBattle(Knight knight) {
        myKnight = new Knight();
        myKnight = knight;
        createOpponent();
    }

}
