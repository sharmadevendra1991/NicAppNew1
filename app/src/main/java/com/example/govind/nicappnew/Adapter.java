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
    public Adapter(Activity context,int resource,ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context=context;
        this.id=resource;
        this.array=objects;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = context.getLayoutInflater();
           // convertView = inflater.inflate(id, null);
            convertView=inflater.inflate(id, parent, false);
        }
            final Item item = array.get(position);
            TextView OwnerNameText = (TextView) convertView.findViewById(R.id.ownername);
     /*   TextView Nabalig=(TextView)convertView.findViewById(R.id.nabalig);*/
            TextView RelationName = (TextView) convertView.findViewById(R.id.relation);
            TextView ParentsName = (TextView) convertView.findViewById(R.id.relative);
            TextView CategoryName = (TextView) convertView.findViewById(R.id.category);
            TextView CastName = (TextView) convertView.findViewById(R.id.cast);
            TextView NiwasiName = (TextView) convertView.findViewById(R.id.niwasi);
            TextView LandTypeName = (TextView) convertView.findViewById(R.id.type);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.chk_action);
       /* TextView txtOldHissa=(TextView)convertView.findViewById(R.id.oldhissa);
       TextView txtNewHissa=(TextView)convertView.findViewById(R.id.newhissa);
       TextView txt_Hissa=(TextView)convertView.findViewById(R.id.txt_Hissa);*/
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setIschecked(isChecked);
            }
        });
        OwnerNameText.setText(item.getOwnerNameText());
        RelationName.setText(item.getRelationName());
        ParentsName.setText(item.getParentsName());
        CastName.setText(item.getCategoryName());
        CategoryName.setText(item.getCastName());

        NiwasiName.setText(item.getNiwasiName());
        LandTypeName.setText(item.getLandTypeName());

       checkBox.setChecked(item.ischecked());

            //Item item=array.get(position);
           // return super.getView(position, convertView, parent);

        return convertView;

    }
}
