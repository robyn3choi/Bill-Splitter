package com.nwhacks.billsplitter.logic;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by Robyn on 2016-02-27.
 */
public class Person implements Serializable {
    String name;
    double totalPrice;
    private ArrayList<SplitItem> listOfItem = new ArrayList<>();

    public Person(String name) {
        this.name = name;
        totalPrice = 0.00;
        listOfItem = new ArrayList<SplitItem>();
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
