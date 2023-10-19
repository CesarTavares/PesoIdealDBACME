package com.aracagamesstudio.pesoidealdbacme.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aracagamesstudio.pesoidealdbacme.controller.helper.DBHelper;
import com.aracagamesstudio.pesoidealdbacme.model.Person;

import java.util.List;
import java.util.ArrayList;


public class ImcDAO {
    SQLiteDatabase db;
    DBHelper helper;

    public ImcDAO(Context context)
    {
        helper = new DBHelper(context);
    }

    public void Open()
    {
        db = helper.getReadableDatabase();
    }

    public void Close()
    {
        db.close();
    }

    public Long InsertInto(Person person)
    {
        ContentValues data = new ContentValues();
        data.put("Nome", person.GetName());
        data.put("Sexo", person.GetGender());
        data.put("Peso", person.GetWeight());
        data.put("Altura", person.GetHeight());

        return (Long) db.insert(DBHelper.TBL_NAME, null, data);
    }

    public List<Person> ListImcList()
    {
        List<Person> imc = new ArrayList<Person>();
        String[] fields = new String[]{"Nome", "Sexo", "Peso", "Altura"};

        String stmt = "SELECT `Nome`, `Sexo`, `Peso`, `Altura` FROM " + DBHelper.TBL_NAME;
        Cursor result = db.rawQuery(stmt, null);
        result.moveToNext();

        while(!result.isAfterLast())
        {
            Person person = new Person(
                    result.getString(0),
                    result.getString(1),
                    result.getDouble(2),
                    result.getDouble(3)
            );

            imc.add(person);
            result.moveToNext();
        }

        result.close();
        return imc;
    }
}
