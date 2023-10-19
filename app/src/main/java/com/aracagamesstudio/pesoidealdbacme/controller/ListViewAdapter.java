package com.aracagamesstudio.pesoidealdbacme.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aracagamesstudio.pesoidealdbacme.R;
import com.aracagamesstudio.pesoidealdbacme.model.ListViewData;
import com.aracagamesstudio.pesoidealdbacme.model.Person;

import java.util.ArrayList;

public class ListViewAdapter  extends BaseAdapter {
    private final LayoutInflater fragment;
    private final ArrayList<Person> items;

    public ListViewAdapter(Context context, ArrayList<Person> _items)
    {
        items = _items;
        fragment = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int index) {
        return items.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder", "InflateParams", "DefaultLocale"})
    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        Person person = items.get(index);

        view  =  fragment.inflate(R.layout.lsv_item_fragment, null);
        ((TextView) view.findViewById(R.id.txtName)).setText(person.GetName());
        ((TextView) view.findViewById(R.id.txtCurrentWeight)).setText("Peso Atual: " + String.format("%.1f", person.GetWeight()));
        ((TextView) view.findViewById(R.id.txtIdealWeight)).setText("Peso Ideal: " + String.format("%.1f", ListViewData.CalculateImc((Double)person.GetHeight(), person.GetGender())));

        return view;
    }

}
