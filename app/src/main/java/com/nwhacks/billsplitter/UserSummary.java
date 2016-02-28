package com.nwhacks.billsplitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.nwhacks.billsplitter.logic.ItemAdapter;
import com.nwhacks.billsplitter.logic.Person;

public class UserSummary extends AppCompatActivity {

    Intent intent;
    Person person;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_summary);
        intent = getIntent();
        person = (Person)intent.getSerializableExtra("userSend");
        TextView userTitle = (TextView)findViewById(R.id.userTitle);
        userTitle.setText(person.getName());
        adapter = new ItemAdapter(this, R.layout.set_up_textview, person.getListOfItem());
        ListView itemDisplay = (ListView)findViewById(R.id.itemDisplay);
        itemDisplay.setAdapter(adapter);
        TextView totalPrice = (TextView)findViewById(R.id.totalPrice);
        totalPrice.setText(" Total Price:   " + String.format("$%.2f",  person.getTotalPrice()));

        //String text = String.format("$%.2f", this.headerItems.getItems().get(groupPosition).getCostPerPerson());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_summary, menu);
        return true;
    }

    public void back(View view){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
