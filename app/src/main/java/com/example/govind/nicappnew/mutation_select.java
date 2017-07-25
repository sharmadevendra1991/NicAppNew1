package com.example.govind.nicappnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageButton;
import android.view.View;


/**
 * Created by nicsi on 10-07-2017.
 */

public class mutation_select  extends AppCompatActivity {
    String District =  new String();
    String Tehsil = new String();
    String Districtcode =  new String();
    String Tehsilcode = new String();
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutation_select);
      //  getSupportActionBar().hide();
//       Bundle bundle = getIntent().getExtras();
//        District = bundle.getString("DistName");
//        Tehsil = bundle.getString("TehName");
//        Districtcode = bundle.getString("Distcode");
//        Tehsilcode = bundle.getString("Tehcode");
        String ss = new String();
        District="टोंक";
        Tehsil="ऊनियारा";
        Districtcode="12";
        Tehsilcode="20";
        TextView txtview=(TextView) findViewById(R.id.msg);
        txtview.setText("      जिला :-"+District+" तहसील :- "+Tehsil);
        ImageButton imageButton=(ImageButton)findViewById(R.id.imageButton);
        ImageButton imageButton1=(ImageButton)findViewById(R.id.imageButton1);
        ImageButton imageButton2=(ImageButton)findViewById(R.id.imageButton2);
        ImageButton imageButton3=(ImageButton)findViewById(R.id.imageButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mutation_select.this, mutationentry.class);
                Bundle bundle = new Bundle();
//Add your data to bundle
                bundle.putString("Distcode", Districtcode);
                bundle.putString("Tehcode", Tehsilcode);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
