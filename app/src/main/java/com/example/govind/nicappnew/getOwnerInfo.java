package com.example.govind.nicappnew;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by govind on 02-Jun-17.
 */

public class getOwnerInfo extends AppCompatActivity {
List<String> countryNameList = new ArrayList<String>();
    String vill_link = new String();
    String khasara =  new String();
    String District =  new String();
    String Tehsil = new String();
    String gkhata = new String();
    private HttpURLConnection urlConnection;
    JSONArray jarray;
    private Context context;
    StringBuilder sb = null;
    AutoCompleteTextView simpleAutoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.getownername);
        // getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        vill_link = bundle.getString("Vill_link");
        District = bundle.getString("DistId");
        Tehsil = bundle.getString("TId");
        try {
            new getOwnerInfo.DownloadJSON().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetuserInfo?vill_link="+ vill_link));

        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
        //initiate an auto complete text view
         simpleAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.simpleAutoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, countryNameList);

        simpleAutoCompleteTextView.setAdapter(adapter);
        simpleAutoCompleteTextView.setThreshold(1);        //start searching from 1 character
        simpleAutoCompleteTextView.setAdapter(adapter);   //set the adapter for displaying country name list
    }
    private class DownloadJSON extends AsyncTask<URL, Void, JSONArray> {
        JSONArray jarray;
        @Override
        protected JSONArray doInBackground(URL... urls) {
            URL url = urls[0];
            String line;
            String c;
            System.out.println(url.toString().substring(url.toString().lastIndexOf("=") + 1));
            System.out.println(url);
            sb = new StringBuilder();
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject jsnobject = new JSONObject(sb.toString());
                jarray = jsnobject.getJSONArray("Table");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(jarray);
            return jarray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            // Locate the spinner in activity_main.xml


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable

                    String oname = c.getString("oname");
                    countryNameList.add(oname);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
    }
    public void GetOwnername(View view)
    {
        String txt1String = simpleAutoCompleteTextView.getText().toString();

        if(txt1String.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"कृपया कास्तकार का नाम चुने ",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(getOwnerInfo.this, informationbyownername.class);

//Create the bundle
            Bundle bundle = new Bundle();

//Add your data to bundle
            txt1String=txt1String.trim();
            txt1String = txt1String.substring(0, txt1String.length() - 1);
            bundle.putString("Vill_link", vill_link.toString());
            bundle.putString("user", txt1String.toString());
//Add the bundle to the intent
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}
