package com.nwhacks.billsplitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import com.nwhacks.billsplitter.logic.Person;


public class SetUp extends AppCompatActivity {

//    // Array of strings..
//    private ArrayList<Person> personList = new ArrayList<Person>();
//    private EditText text;
//    private PersonAdapter adapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.set_up_listview);
//        text = (EditText) findViewById(R.id.nameText);
//        adapter = new PersonAdapter(this, R.layout.set_up_textview, personList);
//        ListView listView = (ListView) findViewById(R.id.list_person);
//        listView.setAdapter(adapter);
//        listView.setClickable(true);
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                return false;
//            }
//        });
//    }
//    //adds a person to a list
//    public void addPerson(View v){
//        if(text.getText().toString().trim().length() != 0) {
//            String personName = text.getText().toString();
//            personList.add(new Person(personName));
//            adapter.notifyDataSetChanged();
//            text.setText("");
//        }
//    }
}
