package com.aracagamesstudio.pesoidealdbacme.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListViewData {

    //region Create List
    public static ArrayList<Person> CreateList(List<Person> imcs, int limit)
    {
        ArrayList<Person> _imcs = new ArrayList<Person>();
        for(Person imc : imcs)
        {
            if(_imcs.size() <= limit) {
                AddPerson(imc.GetName(), imc.GetGender(), imc.GetWeight(), imc.GetHeight(), _imcs);
            }
        }
        return _imcs;
    }

    public static ArrayList<Person> CreateList(List<Person> imcs, boolean isLimited)
    {
        ArrayList<Person> _imcs = new ArrayList<Person>();
        for(Person imc : imcs)
        {
            if(!isLimited) {
                AddPerson(imc.GetName(), imc.GetGender(), imc.GetWeight(), imc.GetHeight(), _imcs);
            }
        }
        return _imcs;
    }

    //endregion
    private static void AddPerson(String name, String gender, Double weight, Double height, ArrayList<Person> list)
    {
        Person person = new Person(name, gender, weight, height);
        list.add(person);
    }

    public static Double CalculateImc(Double height, String gender)
    {
        return Objects.equals(gender, "M")?((72.7 * height) - 58):((62.1 * height) - 44.7);
    }
}
