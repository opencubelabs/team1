package app.sacnorth.ml.starme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

/**
 * Created by Sachin on 3/18/2015.
 */
public class Sqhandler {
    File dbfile;
    SQLiteDatabase  db;
    Sqhandler(){
        try {
            dbfile = new File("/sdcard/star.db");
            db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
            db.setVersion(1);
            db.setLocale(Locale.getDefault());
            db.setLockingEnabled(true);
        }catch(Exception e){

        }
    }

    String[] getAllStars(){
        Cursor cursor = db.rawQuery("SELECT bookTitle FROM COMPANY ", null);
        String retdata[]= null;
        int i=0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                   retdata[i]=cursor.getString(cursor.getColumnIndex("NAME"));
                    i++;
                } while (cursor.moveToNext());
                return retdata;
            }

        }
        return new String[0];
    }

    String[] getStarInfo(int STARNO){


        Cursor cursor = db.rawQuery("SELECT bookTitle FROM COMPANY where NAME ="+STARNO, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String retdata[]=null;
                    String d1=cursor.getString(cursor.getColumnIndex("NAME"));
                    String d2=cursor.getString(cursor.getColumnIndex("SET1"));
                    String d3=cursor.getString(cursor.getColumnIndex("SET2"));
                    String d4=cursor.getString(cursor.getColumnIndex("SET3"));
                    retdata[0]=d1;
                    retdata[1]=d2;
                    retdata[2]=d3;
                    retdata[3]=d4;
                    return retdata;
                } while (cursor.moveToNext());

            }

        }

        return new String[0];
    }

}