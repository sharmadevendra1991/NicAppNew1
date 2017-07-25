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

/**
 * Created by govind on 01-Jun-17.
 */

public class getkhata extends Activity {
    ArrayList<String> khatalist = new ArrayList<String>();
    StringBuilder sb = null;
    private Context context;
    String District =  new String();
    String Tehsil = new String();
    String vill_link = new String();
    private HttpURLConnection urlConnection;
    MultiAutoCompleteTextView simpleMultiAutoCompleteTextView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getkhata);
        // Download JSON file AsyncTask
        Bundle bundle = getIntent().getExtras();
        vill_link = bundle.getString("Vill_link");
        District = bundle.getString("DistId");
        Tehsil = bundle.getString("TId");
        try {
            new getkhata.DownloadJSON().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetKhata?vill_link="+ vill_link));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // initiate a MultiAutoCompleteTextView
         simpleMultiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.simpleMultiAutoCompleteTextView);
// set adapter to fill the data in suggestion list
        ArrayAdapter<String> versionNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, khatalist);
        simpleMultiAutoCompleteTextView.setAdapter(versionNames);

// set threshold value 1 that help us to start the searching from first character
        simpleMultiAutoCompleteTextView.setThreshold(1);
// set tokenizer that distinguish the various substrings by comma
        simpleMultiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        simpleMultiAutoCompleteTextView.getText();

    }
    // Download JSON file AsyncTask
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
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable

                    String khata = c.getString("gkhata");

                    khatalist.add(khata);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
           // khatalist.add(0, "खाता चुने");


        }

    }
    public void GetKhata(View view)
    {
        String txt1String = simpleMultiAutoCompleteTextView.getText().toString();

        if(txt1String.isEmpty())
        {
             Toast.makeText(getApplicationContext(),"कृपया खाता चुने ",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(getkhata.this, informationbykhata.class);
//Create the bundle
            Bundle bundle = new Bundle();

//Add your data to bundle
         // String khata = (txt1String.lastIndexOf(','));
            txt1String=txt1String.trim();
            txt1String = txt1String.substring(0, txt1String.length() - 1);
            bundle.putString("Vill_link", vill_link.toString());
            bundle.putString("khata", txt1String.toString());
//Add the bundle to the intent
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}
