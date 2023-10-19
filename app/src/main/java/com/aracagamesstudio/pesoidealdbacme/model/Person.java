package com.aracagamesstudio.pesoidealdbacme.model;

public class Person {

    int idPessoa;
    String name, gender;
    Double weight, height;

    public Person(String _name, String _gender, Double _weight, Double _height)
    {
        name = _name;
        gender = _gender;
        weight = _weight;
        height = _height;
    }

    public void SetName(String _name)
    {
        name = _name;
    }

    public String GetName()
    {
        return name;
    }

    public void SetGender(String _gender)
    {
        gender = _gender;
    }

    public String GetGender()
    {
        return gender;
    }

    public void SetWeight(Double _weight)
    {
        weight = _weight;
    }

    public Double GetWeight()
    {
        return weight;
    }

    public void SetHeight(Double _height)
    {
        height = _height;
    }

    public Double GetHeight()
    {
        return height;
    }
}
