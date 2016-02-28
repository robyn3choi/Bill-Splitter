package com.nwhacks.billsplitter.logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ian Fong on 2016-02-27.
 */
public class SplitItem implements Serializable{
    private String name;
    private double price;


    private ArrayList<Person> participants = new ArrayList<>();
    private double costPerPerson;

    public SplitItem(String name, double price) {
        this.name = name;
        this.price = price;
        costPerPerson = price;
    }
    public void addParticipant(Person person) {
        participants.add(person);
        costPerPerson = price / participants.size();
    }

    public void removeParticipant(Person person) {
        participants.remove(person);
        if(participants.size() == 0) {
            costPerPerson = price;
        } else {
            costPerPerson = price / participants.size();
        }
    }

    public ArrayList<Person> getParticipants() {
        return participants;
    }

    public double getTotalCost() {
        return price;
    }

    public double getCostPerPerson() {
        return costPerPerson;
    }

    //for tax
    public double recalculateCostPerPerson() {
        costPerPerson = price/participants.size();
        return costPerPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
