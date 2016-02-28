package com.nwhacks.billsplitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.nwhacks.billsplitter.logic.Bill;
import com.nwhacks.billsplitter.logic.Person;
import com.nwhacks.billsplitter.logic.SplitItem;

/**
 * Created by Johnny on 28/02/2016.
 */
public class FoodToPeople extends Activity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    Bill data;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        intent = getIntent();
        data = (Bill)intent.getSerializableExtra("itemSend");
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this, data);
        expListView.setAdapter(listAdapter);
    }




}
