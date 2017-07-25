package com.example.govind.nicappnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by govind on 02-Jun-17.
 */

public class getkhasara extends Activity{
    ArrayList<String> khasaralist = new ArrayList<String>();
    StringBuilder sb = null;
    private Context context;
    String District =  new String();
    String Tehsil = new String();
    String vill_link = new String();
    private HttpURLConnection urlConnection;
    MultiAutoCompleteTextView simpleMultiAutoCompleteTextView;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getkhasara );
        Bundle bundle = getIntent().getExtras();
        vill_link = bundle.getString("Vill_link");
        District = bundle.getString("DistId");
        Tehsil = bundle.getString("TId");
        try {
            new getkhasara.DownloadJSON().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetKhasara?vill_link="+ vill_link));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // initiate a MultiAutoCompleteTextView
        simpleMultiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.simpleMultiAutoCompleteTextView);
// set adapter to fill the data in suggestion list
        ArrayAdapter<String> versionNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, khasaralist);
        simpleMultiAutoCompleteTextView.setAdapter(versionNames);

// set threshold value 1 that help us to start the searching from first character
        simpleMultiAutoCompleteTextView.setThreshold(1);
// set tokenizer that distinguish the various substrings by comma
        simpleMultiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        simpleMultiAutoCompleteTextView.getText();
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
                    String khasara = c.getString("khasra2");
                    khasaralist.add(khasara);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    public void GetKhasara(View view)
    {
        String txt1String = simpleMultiAutoCompleteTextView.getText().toString();
       /* String txt1String1[] =txt1String.split(",");
        List<String[]> someList = new ArrayList<>();
        String someListnew = new String();
             someList.add(txt1String1);*/
        txt1String=txt1String.trim();
        txt1String = txt1String.substring(0, txt1String.length() - 1);
        //String jk[] = new String[] {"202","303"};
        String someList1 = new String();
        String jk[]=txt1String.split(",");
        for(int i=0;i<jk.length;i++)
        {
            someList1+="'"+jk[i]+"'"+",";
        }

        
        if(someList1.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"कृपया खसरा चुने ",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(getkhasara.this, informationbykhasara.class);

//Create the bundle
            Bundle bundle = new Bundle();

//Add your data to bundle

            someList1=someList1.trim();
            someList1 = someList1.substring(0, someList1.length() - 1);
          //  someList1 = someList1.substring(1, someList1.length());
            bundle.putString("Vill_link", vill_link.toString());
            bundle.putString("khasara", someList1.toString());
//Add the bundle to the intent
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}


