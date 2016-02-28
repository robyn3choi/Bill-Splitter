package com.nwhacks.billsplitter;

/**
 * Created by Robyn on 2016-02-27.
 */
public class Person {

    private String name;
    private Double totalPrice;

    public Person(String name) {
        this.name = name;
        totalPrice = 0.00;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
