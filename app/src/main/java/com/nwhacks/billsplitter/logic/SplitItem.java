package com.nwhacks.billsplitter.logic;

import java.util.ArrayList;

/**
 * Created by Ian Fong on 2016-02-27.
 */
public class SplitItem {
    private String name;
    private int quantity;
    private double price;


    private ArrayList<Person> participants = new ArrayList<>();
    private double costPerPerson;

    public SplitItem(String name, double price, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        costPerPerson = price;
    }
    public void addParticipant(Person person) {
        participants.add(person);
        costPerPerson = (price * quantity) / participants.size();
    }

    public void removeParticipant(Person person) {
        participants.remove(person);
        if(participants.size() == 0) {
            costPerPerson = price * quantity;
        } else {
            costPerPerson = (price * quantity) / participants.size();
        }
    }

    public ArrayList<Person> getParticipants() {
        return participants;
    }

    public double getTotalCost() {
        return price * quantity;
    }

    public double getCostPerPerson() {
        return costPerPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SplitItem splitItem = (SplitItem) o;

        return name.equals(splitItem.name);

    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
