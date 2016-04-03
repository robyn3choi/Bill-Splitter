package com.nwhacks.billsplitter;

import android.app.DialogFragment;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nwhacks.billsplitter.logic.Bill;
import com.nwhacks.billsplitter.logic.Person;
import com.nwhacks.billsplitter.logic.SplitItem;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable{

    ArrayList<SplitItem> mealItems;
    ArrayList<com.nwhacks.billsplitter.logic.Person> guests;
    ImageView plateIcon;

    String msg;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    Intent intent;
    Person person;
    Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();
        bill = (Bill)intent.getSerializableExtra("sendBill");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment addDialog = new addFood();
                addDialog.show(getFragmentManager(), "add");
            }
        });

        Button taxButton = (Button) findViewById(R.id.taxButton);
        taxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment addDialog = new CalculateTax();
                addDialog.show(getFragmentManager(), "add");
            }
        });

        guests = bill.getPeople();
        mealItems = bill.getItems();

        setUpIcons();

//        Button viewItemsButton = (Button)findViewById(R.id.viewItemsButton);
//        viewItemsButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // go to itemsListActivity
//            }
//        });
    }

    public void calculateTax(double taxPercentage) {
        double actualPercentage = taxPercentage*0.01+1;
        for (int x=0; x<bill.getItems().size(); x++) {
            SplitItem item = bill.getItems().get(x);
            if (!item.getIsLiquor()) {
                item.setPrice(item.getPrice() * actualPercentage);
                item.recalculateCostPerPerson();
            }
        }

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        TextView totalPrice = (TextView) relativeLayout.findViewById(R.id.totalPrice);
        String text = "Total: $" + Double.toString(bill.getTotalCost());
        totalPrice.setText(text);
    }

    public void calculateLiquorTax(double liquorTaxPercentage) {
        double actualPercentage = liquorTaxPercentage*0.01+1;
        for (int x=0; x<bill.getItems().size(); x++) {
            SplitItem item = bill.getItems().get(x);
            if (item.getIsLiquor()) {
                item.setPrice(item.getPrice() * actualPercentage);
                item.recalculateCostPerPerson();
            }
        }

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        TextView totalPrice = (TextView) relativeLayout.findViewById(R.id.totalPrice);
        String text = "Total: $" + String.format("%.2f", bill.getTotalCost());
        totalPrice.setText(text);
    }

    /**
     * callback method from QuantityDialogFragment, returning the value of user
     * input.
     *
     */
    public void addFoodItem(String itemName, String price, boolean isLiquor) {
        Double priceDouble = Double.parseDouble(price);
        SplitItem item = new SplitItem(itemName, priceDouble, isLiquor);
        bill.getItems().add(item);

        //ImageView Setup
        ImageView plateIcon = new ImageView(this);
        //setting image resource
        plateIcon.setImageResource(R.mipmap.plate_icon);
        //setting image position

        plateIcon.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams)plateIcon.getLayoutParams();
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        plateIcon.setLayoutParams(layoutParams);
        plateIcon.getLayoutParams().height = dpToPixels(140);
        plateIcon.getLayoutParams().width = dpToPixels(140);

        //adding view to layout
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        relativeLayout.addView(plateIcon);

        TextView itemNameText = (TextView) findViewById(R.id.itemNameTextView);
        itemNameText.setText(itemName);

        plateIcon.setOnTouchListener(new ImageTouchListener());

//        CircleLayout circleLayout = (CircleLayout) findViewById(R.id.circleLayout);
//        for (int x=0; x<circleLayout.getChildCount(); x++) {
//            GradientDrawable drawable = (GradientDrawable) circleLayout.getChildAt(x).getBackground();
//            drawable.setStroke(dpToPixels(3), Color.parseColor("#000000")); // set stroke width and stroke color
//        }

        String text = "Total: " + String.format("$%.2f", bill.getTotalCost());
        TextView totalPrice = (TextView) relativeLayout.findViewById(R.id.totalPrice);
        totalPrice.setText(text);

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
                    drawable.setStroke(dpToPixels(3), Color.parseColor("#00E1FF")); // set stroke width and stroke color
                    String guestName = dropTarget.getText().toString();
                    Person guest = bill.getGuestFromName(guestName);
                    bill.addPersonToItem(guest, bill.getItems().get(bill.getItems().size()-1));
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



    public void setUpIcons() {
        CircleLayout circleLayout = (CircleLayout) findViewById(R.id.circleLayout);
        for(int x=0; x < guests.size(); x++) {

            final Button guestName = new Button(MainActivity.this);
            guestName.setText(guests.get(x).getName());
            guestName.setBackgroundResource(R.drawable.rounded_button);

            int pixels = dpToPixels(80);
            CircleLayout.LayoutParams params = new CircleLayout.LayoutParams(pixels,pixels);
            guestName.setLayoutParams(params);
            circleLayout.addView(guestName);
            guestName.setOnDragListener(new ImageDragListener());
            guestName.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view){
                    person = bill.getGuestFromName(guestName.getText().toString());
                    intent = new Intent(MainActivity.this, UserSummary.class);
                    intent.putExtra("userSend", (Serializable)person);
                    startActivity(intent);
                }
            });
        }
    }





    public int dpToPixels(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public void viewItems(View view){
        intent = new Intent(this, FoodToPeople.class);
        intent.putExtra("itemSend", bill);
        startActivity(intent);
    }
}
