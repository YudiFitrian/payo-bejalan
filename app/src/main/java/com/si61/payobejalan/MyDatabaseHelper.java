package com.si61.payobejalan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context ctx;
    private static final String DATABASE_NAME = "db_payo_bejalan";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_destinasi";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAMA = "nama";
    private static final String FIELD_ALAMAT = "alamat";
    private static final String FIELD_JAM = "jam";

    //factori adalah libary external tapi tidak di gunakan dan di ganti null
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //query buat table
        /*
        CREATE TABLE tbl_destinasi (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nama VARCHAR(50),
            alamat TEXT,
            jam VARCHAR(30)
        );
         */
        //QUERY DI ATAS DI UBAH KEDALAM BENTURK STRING SEBAGAI BERIKUT

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAMA + " VARCHAR(50), " +
                FIELD_ALAMAT + " TEXT, " +
                FIELD_JAM + " VARCHAR(30)" +
                ");"
                ;
        db.execSQL(query);
        //harus ada spasi setelah kolom ( FIELD_ID + "(spasi) INTEGER PRIMARY KEY AUTOINCREMENT,)

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long tambahDATA(String nama, String alamat, String jam) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_ALAMAT, alamat);
        cv.put(FIELD_JAM, jam);

        long eksekusi = db.insert(TABLE_NAME, null, cv);
        return eksekusi;
    }

    public Cursor bacaDataDestinasi(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor varCursor = null;
        if(db!=null){
            varCursor = db.rawQuery(query, null);
        }

        return varCursor;
    }
}
