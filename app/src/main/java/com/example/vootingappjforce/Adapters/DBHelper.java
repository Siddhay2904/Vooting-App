package com.example.vootingappjforce.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.vootingappjforce.Data.Candidate;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private  static final String DATABASE_NAME ="AuthDB";
    private  static final int DATABASE_VERSION = 1;
    private  static final String TABLE_AUTH = "AuthTable";
    private static final String  TABLE_VOTES = "VoteTable";
    private  static final String KEY_ID = "id";
    private  static final String KEY_USERNAME = "username";
    private  static final String KEY_PASSWORD = "pass";
    private  static final String KEY_EMAIL = "email";
    private  static final String KEY_PHONE = "phone";
    private static final String KEY_CANDIDATE = "Candidates";
    private static final String KEY_VOTES = "Votes";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_AUTH + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PHONE + " TEXT )");

        db.execSQL("CREATE TABLE " + TABLE_VOTES + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CANDIDATE + " TEXT," + KEY_VOTES + " INTEGER )");
        //SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTES);
    }

    public void addCandidate(String candidate, Integer votes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CANDIDATE, candidate);
        values.put(KEY_VOTES, votes);

        db.insert(TABLE_VOTES, null, values);
    }

    public Boolean addUser(String username, String pass, String email, String phone) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, pass);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);

        long result = db.insert(TABLE_AUTH, null, values);

        if(result==-1) return false;
        else
            return true;
    }

    public  Boolean checkUser(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_AUTH + " WHERE " + KEY_USERNAME + " = ?",new String[]{user});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public  Boolean checkUserPass(String user, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_AUTH + " WHERE " + KEY_USERNAME + " = ? AND " + KEY_PASSWORD + " = ?", new String[] {user,pass});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public ArrayList<CandidateModel> ChkCandidate() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_VOTES, null);

        ArrayList<CandidateModel> arrayCandidate = new ArrayList<>();

        while (cursor.moveToNext()){

            CandidateModel model = new CandidateModel();
            model.id = cursor.getInt(0);
            model.candidate = cursor.getString(1);
            model.votes = cursor.getString(2);

            arrayCandidate.add(model);
        }
        return arrayCandidate;
    }

    public void updateVotes(CandidateModel candidateModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VOTES, candidateModel.votes);

        db.update(TABLE_VOTES, values, KEY_ID + " = " + + candidateModel.id, null);
    }


}
