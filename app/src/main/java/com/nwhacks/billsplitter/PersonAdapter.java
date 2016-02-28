package com.nwhacks.billsplitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Asus Q502L on 2/27/2016.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    private static class ViewHolder {
        private TextView peopleView;
    }

    public PersonAdapter(Context context, int resourceId, ArrayList<Person> peopleList){
        super(context, resourceId, peopleList);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.set_up_textview, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.peopleView = (TextView) convertView.findViewById(R.id.personName);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = getItem(position);

        if(person != null){
            viewHolder.peopleView.setText(person.getName());
        }
        return convertView;
    }
}
