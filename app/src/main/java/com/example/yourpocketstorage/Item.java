package com.example.yourpocketstorage;

public class Item {
    public String id;
    public String name;

    public int amount;
    public float price;

    public String DateAdded;

    public Item(String id, String name, int amount, float price,String date){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.DateAdded = date;
    }

    public String getItemID(){
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
}
