package com.example.mp_wortspiel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlData extends SQLiteOpenHelper {

    private static final String db_name = "my_db";
    private static final String table_name = "Users";
    private static final String column_name = "Name";
    private static final String column_sno = "SNo";
    private static final String column_password = "Password";
    private static final String column_Email_id = "Email_id";
    private static final int version = 1;
    private static final String table_create = "create table Users (SNo integer primary key not null, "
            + "Name text not null, Email_id text not null, Password text not null);";
    SQLiteDatabase db;

    public sqlData(Context context)

    {
        super(context,db_name,null,version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(table_create);
        this.db = db;
    }
    public String search(String user) {
        db = this.getReadableDatabase();
        String query = "select Name, Password from Users";
        Cursor cursor = db.rawQuery(query,null);
        String cuser,cpassword = "";
        if (cursor.moveToFirst()) {
            do {
                cuser = cursor.getString(0);

                if ((cuser.equals(user))) {
                    cpassword = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return cpassword;

    }
    public boolean userexist(String user) {
        db = this.getReadableDatabase();
        String query = "select Name, Password from Users";
        Cursor cursor = db.rawQuery(query,null);

        String cuser,cpassword = "";
        if (cursor.moveToFirst()) {
            do {
                cuser = cursor.getString(0);

                if ((cuser.equals(user))) {
                    return true;
                }
            } while (cursor.moveToNext());
        } if (!cursor.moveToFirst()) {
            return false;
        }
        return false;
    }
    public void addUser(UserDetails user) {
        db = this.getWritableDatabase();
        String query = "Select * from Users";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        ContentValues values = new ContentValues();
        values.put(column_sno , count);
        values.put(column_name, user.getUser());
        values.put(column_Email_id, user.getEmailid());
        values.put(column_password, user.getPassword());
        db.insert(table_name,null, values);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + table_name;
        db.execSQL(query);
        this.onCreate(db);

    }
}
