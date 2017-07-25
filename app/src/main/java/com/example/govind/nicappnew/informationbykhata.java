package com.example.govind.nicappnew;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//
//* Created bimport android.widget.ExpandableListView;

public class informationbykhata extends Activity{

    List<String> Khasaradata = new ArrayList<String>();
    List<String> SoilData = new ArrayList<String>();
    List<String> UserInfo = new ArrayList<String>();
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;
    String vill_link = new String();
    String khasara =  new String();
    String District =  new String();
    String Tehsil = new String();
    String gkhata = new String();
    private HttpURLConnection urlConnection;
    JSONArray jarray;
    private Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informationbykhata);
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        vill_link= bundle.getString("Vill_link");
       //  khasara = bundle.getString("khasara");
        gkhata = bundle.getString("khata");
      //  District = bundle.getString("DistId");
       // Tehsil = bundle.getString("TId");
        try {
           new informationbykhata.DownloadJSON().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetkhasaraInfoByKhata?vill_link="+ vill_link +"&khata="+ gkhata +""));
            new informationbykhata.DownloadJSON1().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetSoilInfoByKhata?vill_link="+ vill_link +"&gkhata="+ gkhata +""));
            new informationbykhata.DownloadJSON2().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetuserInfoByKhata?vill_link="+ vill_link +"&gkhata="+ gkhata +""));
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
        expListView = (ExpandableListView) findViewById(R.id.exListView);
        //
        Drawable d = getResources().getDrawable(R.drawable.settings_selector);
//position it in the group layout by setting the bounds
//params are left and right bounds
        expListView.setIndicatorBounds(345,375);
        expListView.setGroupIndicator(d);
        //
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }
    private class DownloadJSON extends AsyncTask<URL, Void, JSONArray> {
        StringBuilder sb = null;
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
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(jarray);
            return jarray;
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if(jsonArray.length()!=0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = null;
                    try {
                        c = jsonArray.getJSONObject(i);
                        // Storing each json item in variable
                        String gkhata = c.getString("gkhata");
                        String garea = c.getString("garea");
                        String girrigation = c.getString("girrigation");
                        String gplotnm = c.getString("gplotnm");
                        String gincludeno = c.getString("gincludeno");
                        String khasra2 = c.getString("khasra2");

                        Khasaradata.add("खसरा संख्या :-" + khasra2);
                        Khasaradata.add("खाता संख्या :-" + gkhata);
                        Khasaradata.add("क्षेत्रफल :-" + garea);
                        Khasaradata.add("सिंचाई के साधन :-" + girrigation);
                        Khasaradata.add("खेत का नाम :-" + gplotnm);
                        Khasaradata.add("शामिल नंबर :-" + gincludeno);


                    } catch (final JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            else{

                Khasaradata.add("सूचना उपलब्द नहीं है !");
            }
        }
    }
    private class DownloadJSON1 extends AsyncTask<URL, Void, JSONArray> {
        StringBuilder sb1 = null;
        @Override
        protected JSONArray doInBackground(URL... urls) {
            URL url = urls[0];
            String line;

            System.out.println(url.toString().substring(url.toString().lastIndexOf("=") + 1));
            System.out.println(url);
            sb1 = new StringBuilder();
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = in.readLine()) != null) {
                    sb1.append(line);
                }
                JSONObject jsnobject = new JSONObject(sb1.toString());
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
if(jsonArray.length()!=0) {
    for (int i = 0; i < jsonArray.length(); i++) {

        JSONObject c = null;
        try {
            c = jsonArray.getJSONObject(i);
            // Storing each json item in variable
            String soilnm = c.getString("soilnm");
            String khasra2 = c.getString("khasra2");
            String gkhata = c.getString("gkhata");
            String garea = c.getString("garea");
            String mutationno = c.getString("mutationno");
            SoilData.add("भूमि का प्रकार :-" + soilnm);
            SoilData.add("खसरा :-" + khasra2);
            SoilData.add("खाता संख्या :-" + gkhata);
            SoilData.add("क्षेत्रफल :-" + garea);
            SoilData.add("नामांतरण संख्या :-" + mutationno);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
else
{
    SoilData.add("सूचना उपलब्द नहीं है !");
}
        }
    }
    private class DownloadJSON2 extends AsyncTask<URL, Void, JSONArray> {
        StringBuilder sb2 = null;

        @Override
        protected JSONArray doInBackground(URL... urls) {
            URL url = urls[0];
            String line;
            String c;
            System.out.println(url.toString().substring(url.toString().lastIndexOf("=") + 1));
            System.out.println(url);
            sb2 = new StringBuilder();
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = in.readLine()) != null) {
                    sb2.append(line);
                }
                JSONObject jsnobject = new JSONObject(sb2.toString());
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
            if(jsonArray.length()!=0) {
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject c = null;
                    try {
                        c = jsonArray.getJSONObject(i);
                        // Storing each json item in variable
                        String oname = c.getString("oname");
                        String okhata = c.getString("okhata");
                        UserInfo.add("खाता संख्या :-" +okhata);
                        UserInfo.add(oname);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{

                UserInfo.add("सूचना उपलब्द नहीं है !");
            }

        }
    }
    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        // Adding child data
        listDataHeader.add("                        खसरा विवरण");
        listDataHeader.add("                        भूमि का विवरण");
        listDataHeader.add("                        काश्तकार का विवरण");
        listDataChild.put(listDataHeader.get(0), Khasaradata); //Header,Child data
        listDataChild.put(listDataHeader.get(1), SoilData);
        listDataChild.put(listDataHeader.get(2), UserInfo);
            }

   

}
