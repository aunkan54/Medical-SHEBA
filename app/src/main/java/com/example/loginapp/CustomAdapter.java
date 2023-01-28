package com.example.loginapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DatabaseSavedInfo> {

    private Activity context;
    private List<DatabaseSavedInfo> dataList;
    private LayoutInflater inflater;

    public CustomAdapter(Activity context,List<DatabaseSavedInfo> dataList) {
        super(context, R.layout.retrieve_info_view, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.retrieve_info_view,null,true);

        DatabaseSavedInfo databaseSavedInfo = dataList.get(position);

        TextView nameInfo = view.findViewById(R.id.retrieve_name_text_view_Id);
        TextView ageInfo = view.findViewById(R.id.retrieve_age_text_view_Id);
        TextView sexInfo = view.findViewById(R.id.retrieve_sex_text_view_Id);
        TextView contactInfo = view.findViewById(R.id.retrieve_contact_text_view_Id);

        nameInfo.setText(databaseSavedInfo.getName());
        ageInfo.setText(databaseSavedInfo.getAge());
        sexInfo.setText(databaseSavedInfo.getSex());
        contactInfo.setText(databaseSavedInfo.getContact());

        return view;
    }
}
