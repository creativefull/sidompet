package com.example.mshodiqul.sidompet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mshodiqul on 7/18/16.
 */
public class User {
    public String name;
    public Integer amount;
    private DBHelper dbHelper;
    public User() {

    }
    public User(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }
    public User(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insertUser(User data) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", data.name);
        values.put("amount", data.amount);
        long user_id = db.insert("user", null, values);
        db.close();
        return (int) user_id;
    }

    public int updateBalance(Integer balance) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put("amount", balance);
        String selection = "id=?";
        String[] selectionArg = {String.valueOf(1)};
        long id = db.update("user", cValues, selection, selectionArg);
        return (int) id;
    }

    public JSONObject getUser() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where id=1",null);
        JSONObject usernya = new JSONObject();
        if (cursor.moveToFirst()) {
            try {
                usernya.put("name", cursor.getString(cursor.getColumnIndex("name")));
                usernya.put("amount", cursor.getString(cursor.getColumnIndex("amount")));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        db.close();
        return usernya;
    }
}
