package com.mlabs.bbm.firstandroidapp_morningclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;

public class DataBaseAdapter extends SQLiteOpenHelper{

    private static final int dbVer = 1;

    private static final String dbNam = "registeredAccounts";

    public DataBaseAdapter(Context context) {
        super(context, dbNam, null, dbVer);
    }

    //creates table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAccTable = "CREATE TABLE accounts (id INTEGER PRIMARY KEY, username TEXT UNIQUE, email TEXT UNIQUE, password TEXT, firstname TEXT, lastname TEXT, datecreated TEXT);";
        db.execSQL(createAccTable);
    }

    //updates table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF TABLE EXISTS accounts");
        onCreate(db);
    }

    public void addAcc(DataBaseHelper acc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("id", getAccCount());
        values.put("username", acc.getuNam());
        values.put("email", acc.geteMail());
        values.put("password", acc.getPass());
        values.put("firstname", acc.getfNam());
        values.put("lastname", acc.getlNam());


        db.insert("accounts", null, values);
        db.close();
    }

    public boolean checkEmail(String mail) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM accounts WHERE email = '" + mail + "'";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() <= 0) {
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public boolean checkUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM accounts WHERE username = '" + user + "'";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() <= 0) {
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public boolean loginCheck(String credential, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username, email, password FROM accounts", null);
        String u, e, p;
        if(cursor.moveToFirst()){
            do{
                e = cursor.getString(cursor.getColumnIndex("email"));
                u = cursor.getString(cursor.getColumnIndex("username"));
                p = cursor.getString(cursor.getColumnIndex("password"));
                if (u.equals(credential) || e.equals(credential)) {
                    if (p.equals(pass))
                        return true;
                }
            }while(cursor.moveToNext());
        } return false;
    }

    public int getAccCount() {
        String countQuery = "SELECT * FROM accounts";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM accounts",null);
        return res;
    }

}
