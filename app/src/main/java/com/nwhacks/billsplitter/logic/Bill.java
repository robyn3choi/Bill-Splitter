package com.nwhacks.billsplitter.logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ian Fong on 2016-02-27.
 */
public class Bill implements Serializable{
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

    public Bill() {
        tax = 0;
        withTip = false;
    }

    public void addPersonToItem(Person person, SplitItem item) {
        person.addItem(item);
        item.addParticipant(person);
    }

    public void removePersonFromItem(Person person, SplitItem item) {
        person.removeItem(item);
        item.removeParticipant(person);
    }

    public static ArrayList<SplitItem> getItems() {
        return items;
    }

    public static ArrayList<Person> getPeople() {
        return people;
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

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTip(double tip) {
        this.tip = tip;
        withTip = true;
    }

    public Person getGuestFromName(String guestName) {
        for (int x = 0; x < people.size(); x++) {
            if (people.get(x).getName() == guestName)
                return people.get(x);
        }
        return null;
    }
}
