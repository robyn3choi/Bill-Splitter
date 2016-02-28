package com.nwhacks.billsplitter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> mealItems;
    ArrayList<Person> guests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for testing
//        Person robyn = new Person("robyn");
//        Person johnny = new Person("johnny");
//        Person tony = new Person("tony");
//        Person ian = new Person("ian");
//        Person stuart = new Person("stuart");
//        Person cam = new Person("cam");
//        Person chris = new Person("chris");
//        Person isaac = new Person("isaac");
//        Person sam = new Person("sam");
//        Person jlee = new Person("jlee");
//        Person sarah = new Person("sarah");
//        Person brian = new Person("brian");
//
//        guests = new ArrayList<Person>();
//        guests.add(robyn);
//        guests.add(johnny);
//        guests.add(tony);
//        guests.add(ian);
//        guests.add(stuart);
//        guests.add(cam);
//        guests.add(chris);
//        guests.add(isaac);
//        guests.add(sam);
//        guests.add(jlee);
//        guests.add(sarah);
//        guests.add(brian);




        CircleLayout circleLayout = (CircleLayout) findViewById(R.id.circleLayout);
        for(int x=0; x < guests.size(); x++) {
            ImageView image = new ImageView(MainActivity.this);
            image.setBackgroundResource(R.mipmap.circle_icon);
            image.setId(x);
            circleLayout.addView(image);

            TextView guestName = new TextView(MainActivity.this);
            guestName.setText(guests.get(x).getName());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_LEFT,image.getId());
            circleLayout.addView(guestName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


    public void addMealItem(Item item) {
        mealItems.add(item);
    }


}
