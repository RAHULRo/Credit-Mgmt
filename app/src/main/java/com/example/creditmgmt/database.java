package com.example.creditmgmt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db";
    public static final String TABLE_NAME = "users";
    public static final String ID = "id";
    public static final String NAME = "name";
    public database(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public long add(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put("name", name);
        long val = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return  val;
    }

    public ArrayList<String> getall(){
        ArrayList<String> al = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        if (cursor.moveToFirst()){
            do{
                al.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
    }

    public void delete(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }
}
