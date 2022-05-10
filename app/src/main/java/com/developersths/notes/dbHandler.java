package com.developersths.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dbHandler extends SQLiteOpenHelper {
    public dbHandler(Context context) {
        super(context, params.DB_NAME, null, params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE TABLENAME(ID PRIMARY KEY, TITLE TEXT, CONTENT TEXT, DATE TEXT, TIME TEXT);
        String create = "CREATE TABLE " + params.TABLE_NAME + "(" + params.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + params.KEY_TITLE + " TEXT, " + params.KEY_CONTENT + " TEXT, " + params.KEY_DATE + " TEXT," + params.KEY_TIME + " TEXT)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNote(Notes note){
        ContentValues values = new ContentValues();
        values.put(params.KEY_CONTENT, note.getContent());
        values.put(params.KEY_DATE, note.getDate());
        values.put(params.KEY_TIME, note.getTime());
        values.put(params.KEY_TITLE, note.getTitle());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(params.TABLE_NAME, null, values);
    }

    public List<Notes> getNotes(){
        List<Notes> Notes_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "SELECT * FROM " + params.TABLE_NAME;
        Cursor cursor = db.rawQuery(selection, null);

        if (cursor.moveToFirst()){
            do{
                Notes note = new Notes();
                note.set_id(cursor.getString(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));
                Notes_list.add(note);
            }while(cursor.moveToNext());
        }
        return Notes_list;
    }

    public void dlt_note(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(params.TABLE_NAME, params.KEY_ID+"=?", new String[]{id} );
        db.close();
    }

    public void update_note(String id, String title, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(params.KEY_TITLE, title);
        values.put(params.KEY_CONTENT, content);
        db.update(params.TABLE_NAME, values, params.KEY_ID+"=?", new String[]{id});
    }

}
