package com.example.maxim_ozarovskiy.medievalknight.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.MainActivityContract;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;
import com.example.maxim_ozarovskiy.medievalknight.presenters.activity.MainActivityPresenter;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.KNIGHT;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private Button infoBtn;
    private Button marketplaceBtn;
    private Button inventoryBtn;
    private Button arenaBtn;
    private Knight myKnight;

    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();
        presenter = new MainActivityPresenter(this,this);

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateMyCharacterData();
                startInfoActivity(myKnight);
            }
        });
        marketplaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateMyCharacterData();
                startMarketplaceActivity(myKnight);
            }
        });
        inventoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateMyCharacterData();
                startInventoryActivity(myKnight);
            }
        });
        arenaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateMyCharacterData();
                startArenaActivity(myKnight);
            }
        });

    }

    private void startInfoActivity(Knight knight){
        Intent intent = new Intent(this, CharacterInfoActivity.class);
        intent.putExtra(KNIGHT, knight);
        startActivity(intent);
    }

    private void startMarketplaceActivity(Knight knight){
        Intent intent = new Intent(this, MarketplaceActivity.class);
        intent.putExtra(KNIGHT, knight);
        startActivity(intent);
    }

    private void startInventoryActivity(Knight knight){
        Intent intent = new Intent(this, CharacterInventoryActivity.class);
        intent.putExtra(KNIGHT, knight);
        startActivity(intent);
    }

    private void startArenaActivity(Knight knight){
        Intent intent = new Intent(this, ArenaActivity.class);
        intent.putExtra(KNIGHT, knight);
        startActivity(intent);
    }

    private void initUI(){
        infoBtn = findViewById(R.id.character_info_btn);
        marketplaceBtn = findViewById(R.id.marketplace_btn);
        inventoryBtn = findViewById(R.id.inventory_btn);
        arenaBtn = findViewById(R.id.arena_btn);
    }

    @Override
    public void callbackMyCharacter(Knight knight) {
        myKnight = knight;
    }
}
