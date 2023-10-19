package com.aracagamesstudio.pesoidealdbacme.controller.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

public class DBHelper  extends SQLiteOpenHelper {
    private static final String DB_NAME = "Imc ACME";
    private static final int DB_VERSION = 1;
    public static final String TBL_NAME = "person";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String stmt = "CREATE TABLE " + TBL_NAME +
                "(" +
                "idPessoa integer not null primary key autoincrement," +
                "Nome text not null," +
                "Sexo text not null," +
                "Peso double not null," +
                "Altura double not null" +
                ");";

        db.execSQL(stmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Atualização de DB", "Versão Atual: " + oldVersion + "\nNova Versão: " + newVersion);

        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

}
