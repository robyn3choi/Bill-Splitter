package com.nwhacks.billsplitter;

import java.util.ArrayList;

/**
 * Created by Robyn on 2016-02-27.
 */
public class Person {

    String name;
    Double totalPrice;
    ArrayList<Item> listOfItem;

    public Person(String name) {
        this.name = name;
        totalPrice = 0.00;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addItem(Item item) {
        listOfItem.add(item);
    }

    public void removeItem(Item item) {
        listOfItem.remove(item);
    }

    public String getName() {
        return this.name;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public ArrayList<Item> getListOfItem() {
        return this.listOfItem;
    }
}
