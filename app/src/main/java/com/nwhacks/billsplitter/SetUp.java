package com.nwhacks.billsplitter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class SetUp extends AppCompatActivity {

    // Array of strings..
    private ArrayList<Person> personList = new ArrayList<Person>();
    private EditText text;
    private PersonAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_up_listview);
        text = (EditText) findViewById(R.id.nameText);
        adapter = new PersonAdapter(this, R.layout.set_up_textview, personList);
        final ListView listView = (ListView) findViewById(R.id.list_person);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                {
                    AlertDialog removeMessage = new AlertDialog.Builder(SetUp.this).create();
                    removeMessage.setTitle("Delete");
                    removeMessage.setMessage("Are you sure you want to delete this person?");
                    removeMessage.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int choice) {
                                    dialog.dismiss();
                                }
                            });
                    removeMessage.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int choice) {
                                    personList.remove(position);
                                    adapter.notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            });
                    removeMessage.show();
                    return false;
                }
            }
        });
    }
    //adds a person to a list
    public void addPerson(View v){
        if(text.getText().toString().trim().length() != 0) {
            if(personList.size() < 12) {
                String personName = text.getText().toString();
                personList.add(new Person(personName));
                adapter.notifyDataSetChanged();
                text.setText("");
            }
            else{
                AlertDialog tooManyMessage = new AlertDialog.Builder(SetUp.this).create();
                tooManyMessage.setTitle("Too many people!");
                tooManyMessage.setMessage("You can't have more than 12 people!");
                tooManyMessage.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int choice) {
                                dialog.dismiss();
                            }
                        });
                tooManyMessage.show();
            }
        }
    }
}
