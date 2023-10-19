package com.aracagamesstudio.pesoidealdbacme;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ListAdapter;

import com.aracagamesstudio.pesoidealdbacme.model.ListViewData;
import com.aracagamesstudio.pesoidealdbacme.controller.ListViewAdapter;
import com.aracagamesstudio.pesoidealdbacme.controller.ImcDAO;

public class RecordsActivity  extends AppCompatActivity {
    Button btnBack;
    ListView lsvResults;
    ImcDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        btnBack = (Button) findViewById(R.id.btnBack);
        lsvResults = (ListView) findViewById(R.id.lsvResults);

        dao = new ImcDAO(this);

        Init();
        btnBack.setOnClickListener(this::FinishActivity);
    }

    private void Init()
    {
        btnBack.setText("< Clínica Balança Ideal ACME");

        dao.Open();
        GenerateRecords(dao);
        dao.Close();
    }

    private void FinishActivity(View view)
    {
        this.finish();
    }

    private void GenerateRecords(ImcDAO dao)
    {
        ListViewAdapter adapter = new ListViewAdapter(this, ListViewData.CreateList(dao.ListImcList(), false));
        lsvResults.setAdapter((ListAdapter) adapter);
        lsvResults.setCacheColorHint(Color.TRANSPARENT);
    }
}
