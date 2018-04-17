package com.example.maxim_ozarovskiy.medievalknight.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ArmorItems implements Parcelable {

    private int id;
    private String itemName;
    private int attackBonus;
    private int defenceBonus;
    private String category;
    private String icon;
    private String equipped;

    public ArmorItems() {
    }

    public String isEquipped() {
        return equipped;
    }

    public void setEquipped(String equipped) {
        this.equipped = equipped;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public int getDefenceBonus() {
        return defenceBonus;
    }

    public void setDefenceBonus(int defenceBonus) {
        this.defenceBonus = defenceBonus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.itemName);
        dest.writeInt(this.attackBonus);
        dest.writeInt(this.defenceBonus);
        dest.writeString(this.category);
        dest.writeString(this.icon);
        dest.writeString(this.equipped);
    }

    protected ArmorItems(Parcel in) {
        this.id = in.readInt();
        this.itemName = in.readString();
        this.attackBonus = in.readInt();
        this.defenceBonus = in.readInt();
        this.category = in.readString();
        this.icon = in.readString();
        this.equipped = in.readString();
    }

    public static final Parcelable.Creator<ArmorItems> CREATOR = new Parcelable.Creator<ArmorItems>() {
        @Override
        public ArmorItems createFromParcel(Parcel source) {
            return new ArmorItems(source);
        }

        @Override
        public ArmorItems[] newArray(int size) {
            return new ArmorItems[size];
        }
    };
}
