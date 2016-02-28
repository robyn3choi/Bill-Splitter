package com.nwhacks.billsplitter;

import android.app.DialogFragment;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nwhacks.billsplitter.logic.*;

import com.nwhacks.billsplitter.logic.Bill;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<SplitItem> mealItems;
    ArrayList<com.nwhacks.billsplitter.logic.Person> guests;
    ImageView plateIcon;

    String msg;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    Bill bill = new Bill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment addDialog = new addFood();
                addDialog.show(getFragmentManager(), "add");
            }
        });

        guests = bill.getPeople();

        //for testing
        Person robyn = new Person("robyn");
        Person johnny = new Person("johnny");
        Person tony = new Person("tony");
        Person ian = new Person("ian");
        Person stuart = new Person("stuart");
        Person cam = new Person("cam");
        Person chris = new Person("chris");
        Person isaac = new Person("isaac");
        Person sam = new Person("sam");
        Person jlee = new Person("jlee");
        Person sarah = new Person("sarah");
        Person brian = new Person("brian");

        guests.add(robyn);
        guests.add(johnny);
        guests.add(tony);
        guests.add(ian);
        guests.add(stuart);
        guests.add(cam);
        guests.add(chris);
        guests.add(isaac);
        guests.add(sam);
        guests.add(jlee);
        guests.add(sarah);
        guests.add(brian);


        setUpIcons();
    }

    private final class ImageTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
            else {
                return false;
            }
        }
    }

    private class ImageDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle the dragged view being dropped over a drop view
            View view = (View) event.getLocalState();
            //view dragged item is being dropped on
            Button dropTarget = (Button) v;
            //view being dragged and dropped
            ImageView dropped = (ImageView) view;

            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    GradientDrawable drawable = (GradientDrawable) dropTarget.getBackground();
                    drawable.setStroke(3, Color.parseColor("#00E1FF")); // set stroke width and stroke color
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;

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


    public void addMealItem(SplitItem item) {
        mealItems.add(item);
    }

    public void setUpIcons() {

        plateIcon = (ImageView) findViewById(R.id.plate_icon);
        plateIcon.setOnTouchListener(new ImageTouchListener());

        CircleLayout circleLayout = (CircleLayout) findViewById(R.id.circleLayout);
        for(int x=0; x < guests.size(); x++) {

            Button guestName = new Button(MainActivity.this);
            guestName.setText(guests.get(x).getName());
            guestName.setBackgroundResource(R.drawable.rounded_button);

            final float scale = getResources().getDisplayMetrics().density;
            int pixels = (int) (80 * scale + 0.5f);

            CircleLayout.LayoutParams params = new CircleLayout.LayoutParams(pixels,pixels);
            guestName.setLayoutParams(params);
            circleLayout.addView(guestName);

            guestName.setOnDragListener(new ImageDragListener());
        }
    }
}
