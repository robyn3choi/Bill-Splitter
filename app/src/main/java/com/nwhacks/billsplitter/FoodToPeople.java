package com.nwhacks.billsplitter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.nwhacks.billsplitter.logic.Bill;
import com.nwhacks.billsplitter.logic.Person;
import com.nwhacks.billsplitter.logic.SplitItem;

import java.util.ArrayList;

/**
 * Created by Johnny on 28/02/2016.
 */
public class FoodToPeople extends Activity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    Bill data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        initialize();
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this, data);
        expListView.setAdapter(listAdapter);
    }

    public void initialize() {

        data = new Bill(12, 12);
        data.getPeople().add(new Person("Johnny"));
        data.getPeople().add(new Person("Tony"));
        data.getPeople().add(new Person("Robyn"));
        data.getPeople().add(new Person("Ian"));
        data.getItems().add(new SplitItem("fuckin", 20.00, 2));
        SplitItem item =  new SplitItem("Fuckin", 22.00, 1);
        SplitItem item2 =  new SplitItem("duckin", 22.00, 1);

        data.getItems().add(item);
        Person p = new Person("jon");
        Person q = new Person("josn");
        data.getPeople().add(p);
        data.getPeople().add(q);
        Log.d("f", "addPersonToItem: ");
        data.addPersonToItem(q, item);
        data.addPersonToItem(p, item);
    }



}
