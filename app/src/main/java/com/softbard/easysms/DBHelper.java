package com.softbard.easysms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myDB";
    public static final String TABLE_NAME = "username";
    public static final String ID = "ID";
    public static final String COL_1 = "name";
    private static final String TAG = "databaseHelper";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insartData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1,name);
        long result = db.insert(TABLE_NAME,null,values);
        if (result== -1){
            return false;
        }else
            return true;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from username WHERE ROWID = 1 Limit 1",null);
        return cursor;
    }
    public boolean upDateData(String id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(COL_1,name);
        db.update(TABLE_NAME,values,"ID = ?",new String[]{id});
        return true;

    }
    public void inputData(ModelClass modelClass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",modelClass.getName());
        long mytable = db.insert(TABLE_NAME,null,values);
        Log.e(TAG, "insertData:"+mytable );

    }
    public void fetchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from username WHERE ROWID = 1 Limit 1",null);
        while (cursor.moveToFirst()){
            Log.e(TAG, "fetchData: "+cursor.getString(1));
        }
        cursor.close();
    }
}
