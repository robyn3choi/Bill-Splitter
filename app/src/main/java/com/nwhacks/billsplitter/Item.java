package com.nwhacks.billsplitter;

import java.util.ArrayList;

/**
 * Created by Robyn on 2016-02-27.
 */
public class Item {
    Double price;
    ArrayList<Person> listOfPerson;
    String name;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public void addPerson(Person person) {
        listOfPerson.add(person);
    }

    public void removePerson(Person person) {
        listOfPerson.remove(person);
    }

}
