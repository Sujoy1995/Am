package com.demon.slayer.qr2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUJOY GHOSH on 08-12-2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "foodManager";

    // Contacts table name
    private static final String TABLE_FOODS = "foods";
    private static final String TABLE_TABLE = "tables";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COST = "cost";
    private static final String KEY_TABLE = "tableid";
    private static final String KEY_QTYP ="qtyp" ;


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FOODS + "("
                + KEY_ID + "AUTOINCREMENT" + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_COST + " TEXT," + KEY_QTYP + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

            String CREATE_TABLE = "CREATE TABLE " + TABLE_TABLE + "(" + KEY_TABLE + " TEXT" + ")";
            db.execSQL(CREATE_TABLE);


    }

    @Override


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TABLE);
        // Create tables again
        onCreate(db);
    }

    void addTable(table t) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TABLE, t.getTableid());
        db.insert(TABLE_TABLE, null, values);
        db.close();
    }

    void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getFood()); // Contact Name
        values.put(KEY_COST, food.getCost()); // Contact Phone
        values.put(KEY_QTYP,food.getQtyprice());
        // Inserting Row
        db.insert(TABLE_FOODS, null, values);
        db.close(); // Closing database connection
    }

    public table getTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        table t = new table();
        t.setTableid((cursor.getString(0)));


        return t;
    }

    public List<Food> getAllFoods() {
        List<Food> foodList = new ArrayList<Food>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FOODS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setFood(cursor.getString(1));
                food.setCost(cursor.getString(2));
                food.setQtyprice(Integer.parseInt(cursor.getString(3)));
                // Adding food to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return contact list
        return foodList;
    }
public int countRows()
{
    String countQuery = "SELECT * FROM " + TABLE_FOODS;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);
    int cnt = cursor.getCount();
    cursor.close();
    return cnt;

}
    public int addCost() {
        String selectQuery = "SELECT SUM("+KEY_QTYP+") FROM " + TABLE_FOODS;
        int total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        /*if (cursor.moveToFirst()) {
            do {
                Food food = new Food();


                food.setCost(cursor.getString(0));
                total = total + Integer.parseInt(food.getCost());
            } while (cursor.moveToNext());
        }
*/
        cursor.moveToFirst();
        total=cursor.getInt(0);
        return total;
    }
    /*public int setQuantity()
    {
        int q;
        String selectQuery = "SELECT "+ KEY_QUAN +" FROM " + TABLE_QUAN;
        int total=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Quantity quan = new Quantity();


                quan.setQauantity(cursor.getString(0));
                q=Integer.parseInt(quan.getQauantity());
            } while (cursor.moveToNext());
        }

        return
    }
    */

}