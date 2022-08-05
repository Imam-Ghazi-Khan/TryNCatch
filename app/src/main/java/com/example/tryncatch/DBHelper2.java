package com.example.tryncatch;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper2 extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME = "websitesDatabase2.db";
    public static final String TABLE_NAME = "websitesTable2";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "web";
    public static final String COL_3 = "link";
    public DBHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, web TEXT, link TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertData(String webName, String webLink){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, webName);
        contentValues.put(COL_3, webLink);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return  true;
        }
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}