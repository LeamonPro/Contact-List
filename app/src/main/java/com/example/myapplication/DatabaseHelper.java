package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper( Context context) {
        super(context,"contactListe.sqlite3",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT,userName TEXT,phoneNumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
    public void addUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("userName",user.getUsername());
        cv.put("phoneNumber",user.getPhonenumber());
        db.insert("user",null,cv);
        db.close();
    }
    public void deleteUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from user where id="+String.valueOf(user.getId()));
        db.close();
    }
    public ArrayList<User> fetchAll(){
        String userName,phoneNumber;
        ArrayList<User> usersList=new ArrayList<User>();
        ArrayList<User> temp;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM user",null);
        while(c.moveToNext()){
            userName=c.getString(1);
            phoneNumber=c.getString(2);
            usersList.add(new User(c.getInt(0),userName,phoneNumber));

        }
        c.close();
        db.close();
        return usersList;
    }
    public void modifyUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("userName",user.getUsername());
        cv.put("phoneNumber",user.getPhonenumber());
        db.update("user",cv,"id=?",new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public ArrayList<User> getAll(String keyword){
        ArrayList<User> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("user",new String[]{"id","userName","phoneNumber"},"userName like?", new String[]{keyword + "%"},null,null,null);
        while(c.moveToNext()) {
            contacts.add(new User(c.getInt(0),c.getString(1), c.getString(2)));
        }
        c.close();
        db.close();
        return contacts;
    }

}

