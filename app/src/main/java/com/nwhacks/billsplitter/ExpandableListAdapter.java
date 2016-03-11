package com.nwhacks.billsplitter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.nwhacks.billsplitter.logic.Bill;
import com.nwhacks.billsplitter.logic.Person;
import com.nwhacks.billsplitter.logic.SplitItem;

import java.util.ArrayList;


/**
 * Created by Johnny on 28/02/2016.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private Bill headerItems;

    public ExpandableListAdapter(Context context, Bill headerItems) {
        this._context = context;
        this.headerItems = headerItems;
    }

    @Override
    public ArrayList<String> getChild(int groupPosition, int childPosition) {
        ArrayList<String> personAndCost = new ArrayList<String>();
        String personName = this.headerItems.getItems().get(groupPosition).getParticipants().get(childPosition).getName();
        double costPerPerson = this.headerItems.getItems().get(groupPosition).getCostPerPerson();
        String costString = String.format("$%.2f", costPerPerson);
        personAndCost.add(personName);
        personAndCost.add(costString);
        return personAndCost;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ArrayList<String> personAndCost = getChild(groupPosition, childPosition);
        String person = personAndCost.get(0);
        String cost = personAndCost.get(1);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView personText = (TextView) convertView
                .findViewById(R.id.lblListItem);

        personText.setText(person);


        EditText txt = (EditText) convertView.findViewById(R.id.textView);

        String existingCostText = txt.getText().toString();
        String actualCostText = String.format("$%.2f", this.headerItems.getItems().get(groupPosition).getCostPerPerson());

        // getChildView runs twice
        // the following ensures that only the correct costperperson is displayed in each group
        if (existingCostText == actualCostText) {
            return convertView;
        }
        txt.setText(cost);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.headerItems.getItems().get(groupPosition).getParticipants().size();
    }

    @Override
    public SplitItem getGroup(int groupPosition) {
        return this.headerItems.getItems().get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.headerItems.getItems().size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).getName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
