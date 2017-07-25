package com.example.govind.nicappnew;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
 * Created by nicsi on 18-07-2017.
 */

public class mutationentry extends Activity {
    String Districtcode =  new String();
    String Tehsilcode = new String();
    private HttpURLConnection urlConnection;
    StringBuilder sb = null;
   Spinner SpinnerVillage;
    ArrayList<String> villageIdList = new ArrayList<String>();
    ArrayList<String> villagelist = new ArrayList<String>();
    ArrayList<String> muttype = new ArrayList<String>();
    ArrayList<String> mutid = new ArrayList<String>();
    String villlink = new String();
    String maxmut=new String();
    String mutidsetlected=new String();
    Spinner mySpinner;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutation_entry);
        Bundle bundle = getIntent().getExtras();
        Districtcode = bundle.getString("Distcode");
        Tehsilcode = bundle.getString("Tehcode");


            String GetTehsilbydistId = "http://10.130.19.227/WebApiDistrict/api/values/Getvillage?DistCode=";
            String Url = GetTehsilbydistId + Districtcode;
            String Url1 = Url + "&tehcode=";
            String Url2 = Url1 + Tehsilcode;
            try {
                new DownloadJSON2().execute(new URL(Url2));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                new  DownloadJSON().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/getmtype"));
                new  DownloadJSON1().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetMaxMutation"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }



    }
    private class DownloadJSON2 extends AsyncTask<URL, Void, JSONArray> {
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
            SpinnerVillage = (Spinner) findViewById(R.id.spinnerVillage);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable
                    String villageid = c.getString("Vill_link");
                    String village = c.getString("Village_name");
                    villageIdList.add(villageid);
                    villagelist.add(village);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            villagelist.add(0, "गाँव चुने                                                             ▼");
            villageIdList.add(0, "0");

            // Spinner adapter


            SpinnerVillage.setAdapter(new ArrayAdapter<String>(mutationentry.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    villagelist));
            SpinnerVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long Id) {
                    villlink = villageIdList.get(position);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }

            });
        }
        String dd = new String();
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

            mySpinner = (Spinner) findViewById(R.id.mutationtype);
            mySpinner.setPrompt("जिला चुने ");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable

                    String mut_type = c.getString("Mut_Type");
                    String mut_Id = c.getString("Mut_ID");
                   muttype.add(mut_type);
                    mutid.add(mut_Id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mySpinner.setAdapter(new ArrayAdapter<String>(mutationentry.this,
                    android.R.layout.simple_spinner_dropdown_item ,
                    muttype));
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long Id) {
//                    TehsilId.clear();
//                    Tehsillist.clear();
                    String Name = muttype.get(position);
                    mutidsetlected = mutid.get(position);
                    int pos =mySpinner.getSelectedItemPosition();

                }

                public void onNothingSelected(AdapterView<?> parent) {
                }

            });

        }
    }
    private class DownloadJSON1 extends AsyncTask<URL, Void, JSONArray> {
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
            TextView mutno=(TextView) findViewById(R.id.mutationno);
                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(0);
                    //Storing each json item in variable
                     maxmut = c.getString("mut");
                    //countryNameList.add(oname);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            mutno.setText(maxmut);
        }
    }
}