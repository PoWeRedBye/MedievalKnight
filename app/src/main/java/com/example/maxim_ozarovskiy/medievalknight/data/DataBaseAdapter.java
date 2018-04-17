package com.example.maxim_ozarovskiy.medievalknight.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.maxim_ozarovskiy.medievalknight.model.ArmorItems;
import com.example.maxim_ozarovskiy.medievalknight.model.CharacterInfo;
import com.example.maxim_ozarovskiy.medievalknight.model.Knight;

import java.util.ArrayList;
import java.util.List;

public class DataBaseAdapter {

    private static final String TAG = "DataBaseAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;


    public class MyFieldDB {

        private static final String DATABASE_NAME = "Armory";

        private static final String ARMORY_TABLE = "armory_table";
        public static final String ARMOR_ITEM_ID = "id";
        public static final String ITEM_NAME = "armor_name";
        public static final String ITEM_ATTACK_POWER_BONUS = "attack_bonus";
        public static final String ITEM_CATEGORY = "armor_category";
        public static final String EQUIPPED_OR_NOT = "equipped_or_not";
        public static final String ITEM_DEFENCE_BONUS = "defence_bonus";
        public static final String ITEM_ICON = "item_icon";

        private static final String CHARACTER_INFO_TABLE = "character_info_table";
        public static final String CHARACTER_ID = "id";
        public static final String CHARACTER_ATTACK_DAMAGE = "attack";
        public static final String CHARACTER_DEFENCE = "defence";
        public static final String CHARACTER_BASE_ATTACK = "base_attack";
        public static final String CHARACTER_HP = "hp";
        public static final String CHARACTER_NAME = "character_name";
        public static final String EQUIPPED_ARMOR_ID = "equipped_armor";
        public static final String EQUIPPED_HELM_ID = "equipped_helm";
        public static final String EQUIPPED_BOOTS_ID = "equipped_boots";
        public static final String EQUIPPED_GLOVES_ID = "equipped_goves";
        public static final String EQUIPPED_SHIELD_ID = "equipped_shield";
        public static final String EQUIPPED_SWORD_ID = "equipped_sword";

        private static final int DATABASE_VERSION = 1;

        private static final String CREATE_ARMORY_TABLE =
                "CREATE TABLE IF NOT EXISTS " + ARMORY_TABLE + " (" +
                        ARMOR_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ITEM_NAME + "," +
                        ITEM_ATTACK_POWER_BONUS + "," +
                        ITEM_DEFENCE_BONUS + "," +
                        ITEM_ICON + "," +
                        ITEM_CATEGORY + "," +
                        EQUIPPED_OR_NOT + "," +
                        " UNIQUE (" + ARMOR_ITEM_ID + "));";

        private static final String CREATE_CHARACTER_TABLE =
                "CREATE TABLE IF NOT EXISTS " + CHARACTER_INFO_TABLE + " (" +
                        CHARACTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CHARACTER_NAME + "," +
                        CHARACTER_ATTACK_DAMAGE + "," +
                        CHARACTER_BASE_ATTACK + "," +
                        CHARACTER_DEFENCE + "," +
                        CHARACTER_HP + "," +
                        EQUIPPED_ARMOR_ID + "," +
                        EQUIPPED_HELM_ID + "," +
                        EQUIPPED_BOOTS_ID + "," +
                        EQUIPPED_GLOVES_ID + "," +
                        EQUIPPED_SHIELD_ID + "," +
                        EQUIPPED_SWORD_ID + ")";
    }

    private final Context mCtx;


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, MyFieldDB.DATABASE_NAME, null, MyFieldDB.DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, MyFieldDB.CREATE_ARMORY_TABLE);
            db.execSQL(MyFieldDB.CREATE_ARMORY_TABLE);
            Log.w(TAG, MyFieldDB.CREATE_CHARACTER_TABLE);
            db.execSQL(MyFieldDB.CREATE_CHARACTER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + MyFieldDB.CHARACTER_INFO_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + MyFieldDB.ARMORY_TABLE);
            onCreate(db);
        }
    }

    public DataBaseAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public DataBaseAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public void uploadArmor(List<ArmorItems> armorItemsList){
        mDb.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            for (ArmorItems armorItems : armorItemsList){
                contentValues.put(MyFieldDB.ITEM_NAME, armorItems.getItemName());
                contentValues.put(MyFieldDB.ITEM_ATTACK_POWER_BONUS, armorItems.getAttackBonus());
                contentValues.put(MyFieldDB.ITEM_DEFENCE_BONUS, armorItems.getDefenceBonus());
                contentValues.put(MyFieldDB.ITEM_CATEGORY, armorItems.getCategory());
                contentValues.put(MyFieldDB.EQUIPPED_OR_NOT, armorItems.isEquipped());
                contentValues.put(MyFieldDB.ITEM_ICON, armorItems.getIcon());
                mDb.insert(MyFieldDB.ARMORY_TABLE, null, contentValues);
            }
            mDb.setTransactionSuccessful();
        } finally {
            mDb.endTransaction();
        }
    }

    public long createNewArmorItems(ArmorItems armorItems){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyFieldDB.ITEM_NAME, armorItems.getItemName());
        contentValues.put(MyFieldDB.ITEM_ATTACK_POWER_BONUS, armorItems.getAttackBonus());
        contentValues.put(MyFieldDB.ITEM_DEFENCE_BONUS, armorItems.getDefenceBonus());
        contentValues.put(MyFieldDB.ITEM_CATEGORY, armorItems.getCategory());
        contentValues.put(MyFieldDB.ITEM_ICON, armorItems.getIcon());

        return mDb.insert(MyFieldDB.ARMORY_TABLE, null, contentValues);
    }

    public long createNewCharacter(Knight knight) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(MyFieldDB.CHARACTER_NAME, knight.getName());
        initialValues.put(MyFieldDB.CHARACTER_ATTACK_DAMAGE, knight.getAttackPower());
        initialValues.put(MyFieldDB.CHARACTER_BASE_ATTACK, knight.getBaseAttackPower());
        initialValues.put(MyFieldDB.CHARACTER_DEFENCE, knight.getDefence());
        initialValues.put(MyFieldDB.CHARACTER_HP, knight.getHp());
        for (int i = knight.getArmorItems().size(); i > 0; i--) {
            if (knight.getArmorItems().get(i - 1).getCategory().equals("ARMOR")) {
                initialValues.put(MyFieldDB.EQUIPPED_ARMOR_ID, knight.getArmorItems().get(i - 1).getId());
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals("HELM")) {
                initialValues.put(MyFieldDB.EQUIPPED_HELM_ID, knight.getArmorItems().get(i - 1).getId());
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals("BOOTS")) {
                initialValues.put(MyFieldDB.EQUIPPED_BOOTS_ID, knight.getArmorItems().get(i - 1).getId());
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals("GLOVES")) {
                initialValues.put(MyFieldDB.EQUIPPED_GLOVES_ID, knight.getArmorItems().get(i - 1).getId());
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals("SWORD")) {
                initialValues.put(MyFieldDB.EQUIPPED_SWORD_ID, knight.getArmorItems().get(i - 1).getId());
            } else if (knight.getArmorItems().get(i - 1).getCategory().equals("SHIELD")) {
                initialValues.put(MyFieldDB.EQUIPPED_SHIELD_ID, knight.getArmorItems().get(i - 1).getId());
            }
        }
        return mDb.insert(MyFieldDB.CHARACTER_INFO_TABLE, null, initialValues);
    }

    private void getMyCharacter(){

    }

    public long updateCharacter(int id, Knight knight) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyFieldDB.CHARACTER_ATTACK_DAMAGE, knight.getAttackPower());
            contentValues.put(MyFieldDB.CHARACTER_DEFENCE, knight.getDefence());
            contentValues.put(MyFieldDB.CHARACTER_HP, knight.getHp());
            for (int i = knight.getArmorItems().size(); i >= 0; i--) {
                if (knight.getArmorItems().get(i + 1).getCategory().equals("ARMOR")) {
                    contentValues.put(MyFieldDB.EQUIPPED_ARMOR_ID, knight.getArmorItems().get(i + 1).getId());
                } else if (knight.getArmorItems().get(i + 1).getCategory().equals("HELM")) {
                    contentValues.put(MyFieldDB.EQUIPPED_HELM_ID, knight.getArmorItems().get(i + 1).getId());
                } else if (knight.getArmorItems().get(i + 1).getCategory().equals("BOOTS")) {
                    contentValues.put(MyFieldDB.EQUIPPED_BOOTS_ID, knight.getArmorItems().get(i + 1).getId());
                } else if (knight.getArmorItems().get(i + 1).getCategory().equals("GLOVES")) {
                    contentValues.put(MyFieldDB.EQUIPPED_GLOVES_ID, knight.getArmorItems().get(i + 1).getId());
                } else if (knight.getArmorItems().get(i + 1).getCategory().equals("SWORD")) {
                    contentValues.put(MyFieldDB.EQUIPPED_SWORD_ID, knight.getArmorItems().get(i + 1).getId());
                } else if (knight.getArmorItems().get(i + 1).getCategory().equals("SHIELD")) {
                    contentValues.put(MyFieldDB.EQUIPPED_SHIELD_ID, knight.getArmorItems().get(i + 1).getId());
                } else {
                    break;
                }
            }

            return mDb.update(MyFieldDB.CHARACTER_INFO_TABLE, contentValues, MyFieldDB.CHARACTER_ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long updateArmorItem(int id, ArmorItems armorItem) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyFieldDB.EQUIPPED_OR_NOT, armorItem.isEquipped());

            return mDb.update(MyFieldDB.ARMORY_TABLE, contentValues, MyFieldDB.ARMOR_ITEM_ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ArmorItems> getEquippedArmor(String equipped){
        List<ArmorItems> equippedArmor = new ArrayList<>();
        String query = "select * from " + MyFieldDB.ARMORY_TABLE + " where " + MyFieldDB.EQUIPPED_OR_NOT + " = '" + equipped + "'" + " order by " + MyFieldDB.ARMOR_ITEM_ID;
        mDb = mDbHelper.getWritableDatabase();
        Cursor cursor = mDb.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                ArmorItems armorItems = new ArmorItems();
                armorItems.setId(cursor.getInt(0));
                armorItems.setItemName(cursor.getString(1));
                armorItems.setAttackBonus(cursor.getInt(2));
                armorItems.setDefenceBonus(cursor.getInt(3));
                armorItems.setIcon(cursor.getString(4));
                armorItems.setCategory(cursor.getString(5));
                armorItems.setEquipped(cursor.getString(6));
                equippedArmor.add(armorItems);
            } while (cursor.moveToNext());
        }
        Log.d("equipped armor list", equippedArmor.toString());
        return equippedArmor;


    }

    /*public void delProductRec(long id) {
        mDb.delete(MyFieldDB.PRODUCT_TABLE, MyFieldDB.PRODUCTID + " = " + id, null);
    }*/


    public List<ArmorItems> getArmorItemsByCategoryFromDB(String categoryName) {
        List<ArmorItems> armorItemsList = new ArrayList<ArmorItems>();
        String query = "select * from " + MyFieldDB.ARMORY_TABLE + " where " + MyFieldDB.ITEM_CATEGORY + " = '" + categoryName + "'" + " order by " + MyFieldDB.ARMOR_ITEM_ID;
        mDb = mDbHelper.getWritableDatabase();
        Cursor cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ArmorItems armorItems = new ArmorItems();
                armorItems.setId(cursor.getInt(0));
                armorItems.setItemName(cursor.getString(1));
                armorItems.setAttackBonus(cursor.getInt(2));
                armorItems.setDefenceBonus(cursor.getInt(3));
                armorItems.setIcon(cursor.getString(4));
                armorItems.setCategory(cursor.getString(5));
                armorItems.setEquipped(cursor.getString(6));
                armorItemsList.add(armorItems);
            } while (cursor.moveToNext());
        }
        Log.d("armor list by category", armorItemsList.toString());
        return armorItemsList;
    }

    public CharacterInfo getMyCharacterFromDB() {
        CharacterInfo characterInfo = new CharacterInfo();
        String query = "select * from " + MyFieldDB.CHARACTER_INFO_TABLE;
        mDb = mDbHelper.getWritableDatabase();
        Cursor cursor = mDb.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                characterInfo.setId(cursor.getInt(0));
                characterInfo.setCharacterName(cursor.getString(1));
                characterInfo.setCharacterAttackDmg(cursor.getInt(2));
                characterInfo.setCharacterBaseAttack(cursor.getInt(3));
                characterInfo.setCharacterDefence(cursor.getInt(4));
                characterInfo.setCharacterHP(cursor.getInt(5));
                characterInfo.setEquipedArmorID(cursor.getInt(6));
                characterInfo.setEquipedHelmID(cursor.getInt(7));
                characterInfo.setEquipedBootsID(cursor.getInt(8));
                characterInfo.setEquipedGlovesID(cursor.getInt(9));
                characterInfo.setEquipedShieldID(cursor.getInt(10));
                characterInfo.setEquipedSwordID(cursor.getInt(11));
            } while (cursor.moveToNext());
        }
        Log.d("character data", characterInfo.toString());
        return characterInfo;
    }

    public List<ArmorItems> getAllArmorItemsFromDB() {
        List<ArmorItems> armorItemsList = new ArrayList<>();
        String query = "select * from " + MyFieldDB.ARMORY_TABLE;
        mDb = mDbHelper.getWritableDatabase();
        Cursor cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ArmorItems armorItems = new ArmorItems();
                armorItems.setId(cursor.getInt(0));
                armorItems.setItemName(cursor.getString(1));
                armorItems.setAttackBonus(cursor.getInt(2));
                armorItems.setDefenceBonus(cursor.getInt(3));
                armorItems.setIcon(cursor.getString(4));
                armorItems.setCategory(cursor.getString(5));
                armorItems.setEquipped(cursor.getString(6));
                armorItemsList.add(armorItems);
            } while (cursor.moveToNext());
        }
        Log.d("armor items data", armorItemsList.toString());
        return armorItemsList;
    }

}
