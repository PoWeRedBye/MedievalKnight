package com.example.maxim_ozarovskiy.medievalknight.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.CreateNewCharacterContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;
import com.example.maxim_ozarovskiy.medievalknight.presenters.activity.CreateNewCharacterPresenter;

import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.APP_PREFERENCES;

public class CreateNewCharacterActivity extends AppCompatActivity implements CreateNewCharacterContract.View {

    EditText characterName;
    Button submit;
    CreateNewCharacterContract.Presenter presenter;
    SharedPreferences mySettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_character_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();
        presenter = new CreateNewCharacterPresenter(this, this, mySettings);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createNewCharacter(characterName.getText().toString());
            }
        });

    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initUI(){
        mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        characterName = findViewById(R.id.create_character_activity_character_name_et);
        submit = findViewById(R.id.create_character_activity_submit_btn);
    }

    @Override
    public void callbackNewCharacter() {
        startMainActivity();
    }

    @Override
    public void callbackError(String error) {
        Toast.makeText(this,R.string.character_name_is_empty,Toast.LENGTH_LONG).show();
    }
}
