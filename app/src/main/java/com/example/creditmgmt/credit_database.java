package com.example.creditmgmt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class credit_database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="db1";
    public static final String TABLE_NAME = "credit";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String POINT = "point";
    public credit_database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , EMAIL TEXT , POINT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addcredits(String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(checkuser(name)==false){
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(POINT,100);
        long val = db.insert(TABLE_NAME,null,contentValues);}
        db.close();
        return getcredits(name);
    }

    public boolean checkuser(String name){
        String col[] = {ID};
        SQLiteDatabase db = getReadableDatabase();
        String seletion = NAME + " = ?";
        String[] selargs = {name};
        Cursor cursor = db.query(TABLE_NAME,col,seletion,selargs,null,null, null);
        int count = cursor.getCount();
        cursor.close();
        if (count>0)
            return true;
        return false;
    }







    public int getcredits(String names){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE name ='" + names +"'", null);
        String po = "";
        if (cursor.moveToFirst()) {
            do {
               po = cursor.getString(3);
            } while (cursor.moveToNext());
        }
        return Integer.parseInt(po);
    }

    public void update(String from, String to){
        int one = getcredits(from);
        int two = getcredits(to);
        one = one-1;
        two=two+1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, from);
        contentValues.put(EMAIL, from+"@gmail.com");
        contentValues.put(POINT,one);
        SQLiteDatabase db = this.getWritableDatabase();
        int id = getId(from);
        db.update(TABLE_NAME,contentValues, "ID = ?", new String[] { String.valueOf(id) });
        contentValues = new ContentValues();
        contentValues.put(NAME, to);
        contentValues.put(EMAIL, to+"@gmail.com");
        contentValues.put(POINT,two);
        id = getId(to);
        db.update(TABLE_NAME,contentValues, "ID = ?", new String[] { String.valueOf(id) });
    }

    public int getId(String names){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE name ='" + names +"'", null);
        String po="";
        if (cursor.moveToFirst()) {
            do {
                po = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return Integer.parseInt(po);
    }
}
