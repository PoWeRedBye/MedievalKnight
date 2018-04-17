package com.example.maxim_ozarovskiy.medievalknight.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Parcelable {

    private int id;
    private String name;
    private int hp;
    private int attackPower;
    private int baseAttackPower;
    private int defence;
    private List<ArmorItems> armorItems;

    public Knight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseAttackPower() {
        return baseAttackPower;
    }

    public void setBaseAttackPower(int baseAttackPower) {
        this.baseAttackPower = baseAttackPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public List<ArmorItems> getArmorItems() {
        return armorItems;
    }

    public void setArmorItems(List<ArmorItems> armorItems) {
        this.armorItems = armorItems;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.hp);
        dest.writeInt(this.attackPower);
        dest.writeInt(this.baseAttackPower);
        dest.writeInt(this.defence);
        dest.writeList(this.armorItems);
    }

    protected Knight(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.hp = in.readInt();
        this.attackPower = in.readInt();
        this.baseAttackPower = in.readInt();
        this.defence = in.readInt();
        this.armorItems = new ArrayList<ArmorItems>();
        in.readList(this.armorItems, ArmorItems.class.getClassLoader());
    }

    public static final Parcelable.Creator<Knight> CREATOR = new Parcelable.Creator<Knight>() {
        @Override
        public Knight createFromParcel(Parcel source) {
            return new Knight(source);
        }

        @Override
        public Knight[] newArray(int size) {
            return new Knight[size];
        }
    };
}
