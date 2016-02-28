package com.nwhacks.billsplitter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.nwhacks.billsplitter.logic.Bill;
import com.nwhacks.billsplitter.logic.Person;
import com.nwhacks.billsplitter.logic.SplitItem;


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
    public Person getChild(int groupPosition, int childPosition) {
        return this.headerItems.getItems().get(groupPosition).getParticipants().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).getName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        TextView txt = (TextView) convertView.findViewById(R.id.textView);
        String text = String.format("$%.2f", this.headerItems.getItems().get(groupPosition).getCostPerPerson());
        txt.setText(text);
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
