package com.example.maxim_ozarovskiy.medievalknight.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.SplashContract;
import com.example.maxim_ozarovskiy.medievalknight.model.CharacterInfo;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;
import com.example.maxim_ozarovskiy.medievalknight.presenters.activity.SplashActivityPresenter;


public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    public static final String APP_PREFERENCES = "my_character";
    public static final String APP_PREFERENCES_CHARACTER_ID = "character_name";
    public static final String APP_PREFERENCES_ARMORY_IS_UPLOADED = "armory_is_uploaded";
    public static final String KNIGHT = "knight";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String ARMOR = "ARMOR";
    public static final String GLOVES = "GLOVES";
    public static final String BOOTS = "BOOTS";
    public static final String HELM = "HELM";
    public static final String SWORD ="SWORD";
    public static final String SHIELD ="SHIELD";

    SplashContract.Presenter presenter;
    private SharedPreferences mySettings;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        presenter = new SplashActivityPresenter(this,this,mySettings);
        presenter.callMyCharacter();
    }

    private void init(){
        mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    private void createNewCharacterActivity(){
        Intent intent = new Intent(this,CreateNewCharacterActivity.class);
        startActivity(intent);
        finish();
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void callbackMyCharacter(Knight knight) {
        startMainActivity();
    }

    @Override
    public void createNewCharacter() {
        createNewCharacterActivity();
    }
}
