package com.example.maxim_ozarovskiy.medievalknight.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.ArenaActivityContract;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;
import com.example.maxim_ozarovskiy.medievalknight.presenters.activity.ArenaActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.KNIGHT;

public class ArenaActivity extends AppCompatActivity implements ArenaActivityContract.View {

    TextView arenaLogs;
    Button leave;
    ProgressBar progressBar;
    private String arenaLogsString;
    private String arena;
    private Knight myKnight;
    private Knight otherKnight;
    private String battleLog;
    private String battleLog2;
    private String battleLog3;

    private ArenaActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();
        presenter = new ArenaActivityPresenter(this, this);
        getMyCharacter();
        progressBar.animate();

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    private void startMainActivity(){
        finish();
    }

    private void getMyCharacter() {
        Knight knight = getIntent().getParcelableExtra(KNIGHT);
        presenter.prepareToBattle(knight);
    }

    private void setDataLog(String log) {
        progressBar.clearAnimation();
        progressBar.setVisibility(View.INVISIBLE);
        arenaLogs.setText(log);
    }

    private void initUI() {
        arenaLogs = findViewById(R.id.arena_battle_logs_tv);
        leave = findViewById(R.id.exit_from_arena_btn);
        progressBar = findViewById(R.id.battle_activity_progress_bar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void callbackDuelInformation(Knight my, final Knight other) {
        myKnight = my;
        otherKnight = other;
        arenaLogsString = "Battle has begin: "+"\n ";

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                int HP = 0;
                int dmg2;
                int dmg1;
                int hp2;
                int hp1;
                boolean isAlive = true;
                while (isAlive) {

                    if (myKnight.getHp() < HP || otherKnight.getHp() < HP) {
                        if (myKnight.getHp() < 0) {
                            battleLog3 = arenaLogsString + "\n" + myKnight.getName() + " is *!*!*!*->DEAD<-*!*!*!*" + ";";
                            arenaLogsString = battleLog3;
                            isAlive = false;
                            setDataLog(arenaLogsString);
                        } else if (otherKnight.getHp() < 0) {
                            battleLog3 = arenaLogsString + "\n" + otherKnight.getName() + " is *!*!*!*->DEAD<-*!*!*!*" + ";";
                            arenaLogsString = battleLog3;
                            isAlive = false;
                            setDataLog(arenaLogsString);
                        }


                    } else if (myKnight.getHp() > 0 || otherKnight.getHp() > 0) {
                        dmg1 = myKnight.getAttackPower() - otherKnight.getDefence();
                        if(dmg1 < 0){
                            battleLog = arenaLogsString + "\n" + myKnight.getName() + " miss;";
                            arenaLogsString = battleLog;
                        } else {
                            hp1 = otherKnight.getHp() - dmg1;
                            otherKnight.setHp(hp1);
                            battleLog = arenaLogsString + "\n" + myKnight.getName() + " take damage to oponent: " + dmg1 + ";";
                            arenaLogsString = battleLog;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dmg2 = otherKnight.getAttackPower() - myKnight.getDefence();
                        if(dmg2 < 0){
                            battleLog = arenaLogsString + "\n" + otherKnight.getName() + " miss;";
                            arenaLogsString = battleLog;
                        } else {
                            hp2 = myKnight.getHp() - dmg2;
                            myKnight.setHp(hp2);
                            battleLog2 = arenaLogsString + "\n" + otherKnight.getName() + " take damage to oponent: " + dmg2 + ";";
                            arenaLogsString = battleLog2;
                        }
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
