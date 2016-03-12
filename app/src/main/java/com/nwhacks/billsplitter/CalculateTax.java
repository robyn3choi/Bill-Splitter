package com.nwhacks.billsplitter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Robyn on 2016-02-28.
 */
public class CalculateTax extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Calculate Tax");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_calculate_tax, null);
        builder.setView(v)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CalculateTax.this.getDialog().cancel();
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

                    MainActivity callingActivity = (MainActivity) getActivity();
                    EditText taxPercentageEditText = (EditText) d.findViewById(R.id.taxPercentage);
                    EditText liquorTaxPercentageEditText = (EditText) d.findViewById(R.id.liquorTaxPercentage);


                    String taxPercentageString = taxPercentageEditText.getText().toString();
                    String liquorTaxPercentageString = liquorTaxPercentageEditText.getText().toString();

                    if (taxPercentageString.trim().length() != 0) {
                        callingActivity.calculateTax(Double.parseDouble(taxPercentageString));
                    }
                    else {
                        callingActivity.calculateTax(0);
                    }

                    if (liquorTaxPercentageString.trim().length() != 0) {
                        callingActivity.calculateLiquorTax(Double.parseDouble(liquorTaxPercentageString));
                    }
                    else {
                        callingActivity.calculateLiquorTax(0);
                    }

                    d.dismiss();
                }
            });
        }
    }
}
