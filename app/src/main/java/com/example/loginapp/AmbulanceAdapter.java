package com.example.loginapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class AmbulanceAdapter extends ArrayAdapter<Ambulance> {

    private Activity context;
    private List<Ambulance> list;
    private LayoutInflater inflater;

    public AmbulanceAdapter(Context context,List<Ambulance> list) {
        super(context,R.layout.ambulance_driver_view, list);
        this.context = (Activity) context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.ambulance_driver_view,null,false);


        TextView name = view.findViewById(R.id.view_driver_name_text_view_Id);
/*
        TextView contact = view.findViewById(R.id.view_driver_contact_text_view_Id);
*/

        Ambulance ambulance = list.get(position);

        name.setText(ambulance.getName());
        //contact.setText(ambulance.getContact());
        return view;

    }
}
