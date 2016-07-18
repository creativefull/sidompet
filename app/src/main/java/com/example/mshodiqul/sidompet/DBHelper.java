package com.example.mshodiqul.sidompet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mshodiqul on 7/16/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "sidompet";

    public  DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table transaksi
        Log.w("Version", "Current Version is " + db.getVersion());
        String queryBuatTable = "CREATE TABLE IF NOT EXISTS transaksi (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "amount VARCHAR," +
                "keterangan TEXT," +
                "type VARCHAR," +
                "date DATETIME)";
        String queryBuatTableDompet = "CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR," +
                "amount INTEGER)";
        db.execSQL(queryBuatTable);
        db.execSQL(queryBuatTableDompet);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Update DB", "DB IS UPDATE TO " + db.getVersion());
        db.execSQL("DROP TABLE IF EXISTS transaksi");
        onCreate(db);
    }
}
