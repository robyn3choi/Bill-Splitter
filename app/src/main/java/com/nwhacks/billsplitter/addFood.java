package com.nwhacks.billsplitter;

/**
 * Created by Johnny on 28/02/2016.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Johnny on 27/02/2016.
 */
public class addFood extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add food item!");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(v)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        addFood.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText itemNameText = (EditText) d.findViewById(R.id.ItemName);
                    EditText priceText = (EditText) d.findViewById(R.id.Price);
                    String itemName = itemNameText.getText().toString();
                    String price = priceText.getText().toString();

                    MainActivity callingActivity = (MainActivity) getActivity();
                    callingActivity.addFoodItem(itemName, price);
                    d.dismiss();
                }
            });
        }
    }

    public double calculatePercentage(double percentage, double totalPrice) {
        return percentage*0.01*totalPrice;
    }


}
