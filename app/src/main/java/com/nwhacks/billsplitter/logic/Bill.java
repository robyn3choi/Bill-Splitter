package com.nwhacks.billsplitter.logic;

import com.nwhacks.billsplitter.Person;

import java.util.ArrayList;

/**
 * Created by Ian Fong on 2016-02-27.
 */
public class Bill {
    private static ArrayList<SplitItem> items = new ArrayList<>();
    private static ArrayList<Person> people = new ArrayList<>();
    private double tax;
    private Boolean withTip;
    private double tip;

    public Bill(double tax, double tip) {
        this.tax = tax;
        this.tip = tip;
        withTip = true;
    }

    public Bill(double tax) {
        this.tax = tax;
        withTip = false;
    }

    public void addSplitItem(String name, double price, int quantity) {
        SplitItem item = new SplitItem(name, price, quantity);
        Bill.items.add(item);
    }

    public void addPerson(String name) {
        Person person = new Person(name);
        people.add(person);
    }

    public double getTotalCost() {
        double sum = 0;
        for(SplitItem item : items) {
            sum += item.getTotalCost();
        }
        if(withTip) {
            sum = sum * tip;
        }
        sum = sum * tax;
        return sum;
    }
}
