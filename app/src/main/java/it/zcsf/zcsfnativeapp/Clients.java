package it.zcsf.zcsfnativeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by new1 on 8/17/2017.
 */
public class Clients {

    public static final String TABLE_NAME="CLIENTS";

    public static final String ID="_ID";
    public static final String PASS="_PASS";
    public static final String NAME="_NAME";
    public static final String Email="_EMAIL";
    public static final String TID="_TID";


    private DataHelper myHelper;
    private final Context myContext;
    private SQLiteDatabase myDB;

    public Clients(Context C) {
        myContext = C;
    }

    public Clients open() throws SQLException {
        myHelper=new DataHelper(myContext);
        myDB=myHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        myHelper.close();
    }
    public long addClient(String name, String pass,String email,String tid) {
        ContentValues CV=new ContentValues();
        CV.put("_NAME",name);
        CV.put("_PASS", pass);
        CV.put("_EMAIL", email);
        CV.put("_TID", tid);
        return myDB.insert(TABLE_NAME,null,CV);
    }
    public Boolean login(String id,String pass){
        Cursor C=myDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + TID + " = '" +
                id + "' " + " AND " + PASS + " = '" + pass + "' ", null);
        return C.getCount() > 0;
    }
    public String getData(String id){
        Cursor C=myDB.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+TID+" = '"+id+"' ",null);

        int iName=C.getColumnIndex(NAME);
        int iEmail=C.getColumnIndex(Email);
        int iTid=C.getColumnIndex(TID);
        C.moveToFirst();
        return C.getString(iName)+" \n"+C.getString(iEmail)+" \n"+C.getString(iTid);

    }



    public static class DataHelper extends SQLiteOpenHelper {

        public DataHelper(Context context) {
            super(context, TABLE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+TABLE_NAME+ "(_ID INTEGER PRIMARY KEY AUTOINCREMENT, _NAME TEXT, _PASS TEXT, _EMAIL TEXT, _TID TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);

        }
    }
}
