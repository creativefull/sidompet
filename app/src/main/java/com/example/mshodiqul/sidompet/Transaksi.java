package com.example.mshodiqul.sidompet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by mshodiqul on 7/16/16.
 */
public class Transaksi {
    public String keteragan;
    public Integer amount;
    private DBHelper dbHelper;
    public String type;
    Context contextApp;
    public  Transaksi() {

    }
    public Transaksi (String keteragan, Integer amount) {
        this.keteragan = keteragan;
        this.amount = amount;
    }
    public Transaksi(Context context) {
        dbHelper = new DBHelper(context);
        contextApp = context;
    }
    public int insertTable(Transaksi data) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());

        values.put("amount", data.amount);
        values.put("keterangan", data.keteragan);
        values.put("type", data.type);
        values.put("date", strDate);

        long transaksi_id = db.insert("transaksi",null,values);
        db.close();
        return (int) transaksi_id;
    }
    public ArrayList<HashMap<String, String>> getList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String querySelect = "SELECT * From transaksi order by id desc";
        ArrayList<HashMap<String, String>> trxList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(querySelect, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> trx = new HashMap<String, String>();
                trx.put("id", cursor.getString(cursor.getColumnIndex("id")));
                trx.put("keterangan", cursor.getString(cursor.getColumnIndex("keterangan")));
                trx.put("amount", cursor.getString(cursor.getColumnIndex("amount")));
                trx.put("type", cursor.getString(cursor.getColumnIndex("type")));
                trx.put("date", cursor.getString(cursor.getColumnIndex("date")));
                trxList.add(trx);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return trxList;
    }
}
