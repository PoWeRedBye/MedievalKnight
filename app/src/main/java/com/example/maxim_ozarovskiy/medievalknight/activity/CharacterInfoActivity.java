package com.example.maxim_ozarovskiy.medievalknight.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.maxim_ozarovskiy.medievalknight.R;
import com.example.maxim_ozarovskiy.medievalknight.adapters.CharacterInfoAdapter;
import com.example.maxim_ozarovskiy.medievalknight.interfaces.activity.CharacterInfoContract;
import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;
import com.example.maxim_ozarovskiy.medievalknight.presenters.activity.CharacterInfoActivityPresenter;

import java.util.List;

import static com.example.maxim_ozarovskiy.medievalknight.activity.SplashActivity.KNIGHT;

public class CharacterInfoActivity extends AppCompatActivity implements CharacterInfoContract.View {

    private TextView characterName;
    private TextView characterHP;
    private TextView characterDmg;
    private TextView characterDef;
    private TextView equippedArmorName;
    private TextView equippedHelmName;
    private TextView equippedGlovesName;
    private TextView equippedBootsName;
    private TextView equippedSwordName;
    private TextView equippedShieldName;

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private CharacterInfoAdapter adapter;

    private CharacterInfoActivityPresenter presenter;
    private Knight knight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_info_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();
        presenter = new CharacterInfoActivityPresenter(this,this);
        getMyCharacter();
    }

    private void getMyCharacter(){
        knight = getIntent().getParcelableExtra(KNIGHT);
        presenter.callEquippedList(knight);
    }

    private void setNewRecyclerAdapter(List<ArmorItems> newList){
        adapter = new CharacterInfoAdapter(this, newList);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setMyData(Knight knight,
                           List<ArmorItems> armorItemsList,
                           String armorName,
                           String helmName,
                           String glovesName,
                           String bootsName,
                           String swordName,
                           String shieldName){
        characterName.setText(knight.getName());
        characterHP.setText(String.valueOf(knight.getHp()));
        characterDmg.setText(String.valueOf(knight.getAttackPower()));
        characterDef.setText(String.valueOf(knight.getDefence()));
        equippedArmorName.setText(armorName);
        equippedHelmName.setText(helmName);
        equippedGlovesName.setText(glovesName);
        equippedBootsName.setText(bootsName);
        equippedSwordName.setText(swordName);
        equippedShieldName.setText(shieldName);
        setNewRecyclerAdapter(armorItemsList);
    }

    private void initUI(){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        recyclerView = findViewById(R.id.character_info_recycler_view);
        characterName = findViewById(R.id.character_name_info_tv);
        characterHP = findViewById(R.id.character_hp_info_tv);
        characterDmg = findViewById(R.id.character_info_damage_tv);
        characterDef = findViewById(R.id.character_info_defence_tv);
        equippedArmorName = findViewById(R.id.equipped_armor_name_tv);
        equippedHelmName = findViewById(R.id.equipped_helm_name_tv);
        equippedGlovesName = findViewById(R.id.equipped_gloves_name_tv);
        equippedBootsName = findViewById(R.id.equipped_boots_name_tv);
        equippedSwordName = findViewById(R.id.equipped_sword_name_tv);
        equippedShieldName = findViewById(R.id.equipped_shield_name_tv);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void callbackEquippedList(Knight knight,
                                     List<ArmorItems> armorItemsList,
                                     String armorName,
                                     String helmName,
                                     String glovesName,
                                     String bootsName,
                                     String swordName,
                                     String shieldName) {
        setMyData(knight,armorItemsList,armorName,helmName,glovesName,bootsName,swordName,shieldName);
    }
}
