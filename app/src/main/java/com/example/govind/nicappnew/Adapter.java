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
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by nicsi on 21-08-2017.
 */
public class Adapter extends ArrayAdapter<Item> {
private Activity context;
    private int id;
    ArrayList<Item>array;
    static int counter=0;
    public Adapter(Activity context,int resource,ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context=context;
        this.id=resource;
        this.array=objects;
    }

    @NonNull
    @Override
    public View getView(int position ,View convertView,ViewGroup parent) {

            if (convertView == null) {
                counter++;
                LayoutInflater inflater = context.getLayoutInflater();
                // convertView = inflater.inflate(id, null);
                convertView = inflater.inflate(id, parent, false);
            }
        try {
            final Item item = array.get(position);
            TextView OwnerNameText = (TextView) convertView.findViewById(R.id.ownername);
            TextView Nabalig=(TextView)convertView.findViewById(R.id.nabalig);
            TextView RelationName = (TextView) convertView.findViewById(R.id.relation);
            TextView ParentsName = (TextView) convertView.findViewById(R.id.relative);
            TextView CategoryName = (TextView) convertView.findViewById(R.id.category);
            TextView CastName = (TextView) convertView.findViewById(R.id.cast);
            TextView NiwasiName = (TextView) convertView.findViewById(R.id.niwasi);
            TextView LandTypeName = (TextView) convertView.findViewById(R.id.type);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.chk_action);
        TextView OldHissa=(TextView)convertView.findViewById(R.id.oldhissa);
       TextView OldHissa_area=(TextView)convertView.findViewById(R.id.oldhissa_area);
       TextView newHissa=(TextView)convertView.findViewById(R.id.newhissa);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setIschecked(isChecked);
                }
            });
            OwnerNameText.setText(item.getOwnerNameText());
            RelationName.setText(item.getRelationName());
            ParentsName.setText(item.getParentsName());
            Nabalig.setText(item.getNabalig());
            CastName.setText(item.getCategoryName());
            CategoryName.setText(item.getCastName());

            NiwasiName.setText(item.getNiwasiName());
            LandTypeName.setText(item.getLandTypeName());
            OldHissa.setText(item.getOldHissa());
            OldHissa_area.setText(item.getOldHissa_area());
            newHissa.setText(item.getNewHissa());

            checkBox.setChecked(item.ischecked());


            return convertView;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
