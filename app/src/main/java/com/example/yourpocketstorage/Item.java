package com.example.yourpocketstorage;

import java.io.Serializable;

public class Item implements Serializable {
    public int id;
    public String name;

    public int amount;
    public float price;

    public String DateAdded;

    public Item(int id, String name, int amount, float price,String date){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.DateAdded = date;
    }

    public int getItemID(){
        return id;
    }

    public String getItemName(){
        return name;
    }

    public int getItemAmount(){
        return amount;
    }

    public float getItemPrice(){
        return price;
    }

    public String getDateAdded(){
        return DateAdded;
    }

    public String ItemStroke(){
        return "id =" + this.id + " name =" + this.name + " amount =" + this.amount + " price =" + this.price + " date =" + this.DateAdded;
    }
}
