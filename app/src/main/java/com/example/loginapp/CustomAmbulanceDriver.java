package com.example.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomAmbulanceDriver extends BaseAdapter {

    Context context;
    ArrayList<String> driverName;
    ArrayList<String> driverContact;
    private LayoutInflater inflater;


    public CustomAmbulanceDriver(Context context, ArrayList<String> driverName, ArrayList<String> driverContact) {
        this.context =  context;
        this.driverName = driverName;
        this.driverContact = driverContact;
    }

    @Override
    public int getCount() {
        return driverName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ambulance_driver_view,parent,false);
        }


        TextView name = convertView.findViewById(R.id.view_driver_name_text_view_Id);
        //TextView contact = convertView.findViewById(R.id.view_driver_contact_text_view_Id);

        name.setText(driverName.get(position));
        //contact.setText(driverContact.get(position));
        return convertView;

    }
}
