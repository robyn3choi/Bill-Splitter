package com.nwhacks.billsplitter.logic;

import java.util.ArrayList;
/**
 * Created by Robyn on 2016-02-27.
 */
public class Person {
    String name;
    double totalPrice;
    ArrayList<SplitItem> listOfItem;

    public Person(String name) {
        this.name = name;
        totalPrice = 0.00;
    }

    public void addItem(SplitItem item) {
        listOfItem.add(item);
    }

    public void removeItem(SplitItem item) {
        listOfItem.remove(item);
    }

    public String getName() {
        return this.name;
    }

    public Double getTotalPrice() {
        for(SplitItem item: listOfItem) {
            totalPrice += item.getCostPerPerson();
        }
        return this.totalPrice;
    }

    public ArrayList<SplitItem> getListOfItem() {
        return this.listOfItem;
    }
}
