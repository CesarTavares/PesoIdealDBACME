package com.aracagamesstudio.pesoidealdbacme;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.aracagamesstudio.pesoidealdbacme.controller.ImcDAO;
import com.aracagamesstudio.pesoidealdbacme.controller.ListViewAdapter;
import com.aracagamesstudio.pesoidealdbacme.model.ListViewData;
import com.aracagamesstudio.pesoidealdbacme.model.Person;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtHeight, edtWeight;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch swiGender;
    Button btnAddImc, btnShow;
    ImcDAO dao;
    ListView lsvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edtName);
        edtHeight = (EditText) findViewById(R.id.edtHeight);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        btnAddImc = (Button) findViewById(R.id.btnAddImc);
        btnShow = (Button) findViewById(R.id.btnShow);
        swiGender = (Switch) findViewById(R.id.swiGender);
        lsvResults = (ListView) findViewById(R.id.lsvResults);

        dao = new ImcDAO(this);
        dao.Open();
        GenerateImcList(dao);
        dao.Close();

        btnAddImc.setOnClickListener(this::AddImc);
        btnShow.setOnClickListener(this::ShowAllRecords);
    }

    private void ShowAllRecords(View view)
    {
        Intent recordsPage = new Intent(this, RecordsActivity.class);
        this.startActivity(recordsPage);
    }

    private void ClearFields()
    {
        edtName.setText("");
        edtWeight.setText("");
        edtHeight.setText("");
        swiGender.setEnabled(false);
    }

    private void AddImc(View view)
    {
        if(!VerifyFields())
        {
            Toast msg = Toast.makeText(this, "Todos os campos devem estar preenchidos!", Toast.LENGTH_LONG);
            msg.show();
            return;
        }

        String gender = VerifyGender(swiGender);

        dao.Open();
        Person person = new Person(edtName.getText().toString(), gender, Double.parseDouble(edtWeight.getText().toString()), Double.parseDouble(edtHeight.getText().toString()));
        if(dao.InsertInto(person) > 0)
        {
            GenerateImcList(dao);
        }
        dao.Close();

        ClearFields();
    }

    private boolean VerifyFields()
    {
        EditText[] fields = new EditText[]{edtName, edtHeight, edtWeight};
        boolean isOk = true;

        for(EditText edt : fields)
        {
            if(!edt.getText().toString().matches("\\S{1,}"))
            {
                isOk = false;
            }
        }

        return isOk;
    }

    private String VerifyGender(@SuppressLint("UseSwitchCompatOrMaterialCode") Switch gender)
    {
        return gender.isEnabled()?"M":"F";
    }

    private void GenerateImcList(ImcDAO dao)
    {
        ListViewAdapter adapter = new ListViewAdapter(this, ListViewData.CreateList(dao.ListImcList(), 3));
        lsvResults.setAdapter((ListAdapter) adapter);
        lsvResults.setCacheColorHint(Color.TRANSPARENT);
    }
}