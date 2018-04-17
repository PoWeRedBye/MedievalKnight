package com.example.maxim_ozarovskiy.medievalknight.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CharacterInfo implements Parcelable {

    private int id;
    private String characterName;
    private int characterAttackDmg;
    private int characterBaseAttack;
    private int characterDefence;
    private int characterHP;
    private int equipedArmorID;
    private int equipedHelmID;
    private int equipedBootsID;
    private int equipedGlovesID;
    private int equipedShieldID;
    private int equipedSwordID;

    public CharacterInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCharacterAttackDmg() {
        return characterAttackDmg;
    }

    public void setCharacterAttackDmg(int characterAttackDmg) {
        this.characterAttackDmg = characterAttackDmg;
    }

    public int getCharacterBaseAttack() {
        return characterBaseAttack;
    }

    public void setCharacterBaseAttack(int characterBaseAttack) {
        this.characterBaseAttack = characterBaseAttack;
    }

    public int getCharacterDefence() {
        return characterDefence;
    }

    public void setCharacterDefence(int characterDefence) {
        this.characterDefence = characterDefence;
    }

    public int getCharacterHP() {
        return characterHP;
    }

    public void setCharacterHP(int characterHP) {
        this.characterHP = characterHP;
    }

    public int getEquipedArmorID() {
        return equipedArmorID;
    }

    public void setEquipedArmorID(int equipedArmorID) {
        this.equipedArmorID = equipedArmorID;
    }

    public int getEquipedHelmID() {
        return equipedHelmID;
    }

    public void setEquipedHelmID(int equipedHelmID) {
        this.equipedHelmID = equipedHelmID;
    }

    public int getEquipedBootsID() {
        return equipedBootsID;
    }

    public void setEquipedBootsID(int equipedBootsID) {
        this.equipedBootsID = equipedBootsID;
    }

    public int getEquipedGlovesID() {
        return equipedGlovesID;
    }

    public void setEquipedGlovesID(int equipedGlovesID) {
        this.equipedGlovesID = equipedGlovesID;
    }

    public int getEquipedShieldID() {
        return equipedShieldID;
    }

    public void setEquipedShieldID(int equipedShieldID) {
        this.equipedShieldID = equipedShieldID;
    }

    public int getEquipedSwordID() {
        return equipedSwordID;
    }

    public void setEquipedSwordID(int equipedSwordID) {
        this.equipedSwordID = equipedSwordID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.characterName);
        dest.writeInt(this.characterAttackDmg);
        dest.writeInt(this.characterBaseAttack);
        dest.writeInt(this.characterDefence);
        dest.writeInt(this.characterHP);
        dest.writeInt(this.equipedArmorID);
        dest.writeInt(this.equipedHelmID);
        dest.writeInt(this.equipedBootsID);
        dest.writeInt(this.equipedGlovesID);
        dest.writeInt(this.equipedShieldID);
        dest.writeInt(this.equipedSwordID);
    }

    protected CharacterInfo(Parcel in) {
        this.id = in.readInt();
        this.characterName = in.readString();
        this.characterAttackDmg = in.readInt();
        this.characterBaseAttack = in.readInt();
        this.characterDefence = in.readInt();
        this.characterHP = in.readInt();
        this.equipedArmorID = in.readInt();
        this.equipedHelmID = in.readInt();
        this.equipedBootsID = in.readInt();
        this.equipedGlovesID = in.readInt();
        this.equipedShieldID = in.readInt();
        this.equipedSwordID = in.readInt();
    }

    public static final Parcelable.Creator<CharacterInfo> CREATOR = new Parcelable.Creator<CharacterInfo>() {
        @Override
        public CharacterInfo createFromParcel(Parcel source) {
            return new CharacterInfo(source);
        }

        @Override
        public CharacterInfo[] newArray(int size) {
            return new CharacterInfo[size];
        }
    };
}
