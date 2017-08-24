package com.example.govind.nicappnew;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nicsi on 21-08-2017.
 */
public class Adapter extends ArrayAdapter<ClipData.Item> {
    private Activity context;
    private int id;
    ArrayList<ClipData.Item> array;
    public Adapter(Activity context, @LayoutRes int resource, @NonNull ArrayList<ClipData.Item> objects) {
        super(context,resource,objects);
this.context=context;
        this.id=resource;
        this.array=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater=context.getLayoutInflater();
            convertView=inflater.inflate(id,null);
        }
        TextView owner_Name=(TextView)convertView.findViewById(android.R.id.text1);
        CheckBox checkBox=(CheckBox)convertView.findViewById(android.R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        ClipData.Item item=array.get(position);
        owner_Name.setText(item.getText());
        return super.getView(position, convertView, parent);
    }
}
