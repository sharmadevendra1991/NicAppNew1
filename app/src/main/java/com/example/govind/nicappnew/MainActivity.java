package com.example.govind.nicappnew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import android.app.ProgressDialog;
public class MainActivity extends Activity {
    ProgressDialog mProgressDialog;
    ArrayList<String> Districtlist = new ArrayList<String>();
    ArrayList<String> DistrictId = new ArrayList<String>();
    ArrayList<String> TehsilId = new ArrayList<String>();
    ArrayList<String> Tehsillist = new ArrayList<String>();
    ArrayList<String> IlrIdlist = new ArrayList<String>();
    ArrayList<String> Ilrlist = new ArrayList<String>();
    ArrayList<String> PatwarIdList = new ArrayList<String>();
    ArrayList<String> Patwarlist = new ArrayList<String>();
    ArrayList<String> villageIdList = new ArrayList<String>();
    ArrayList<String> villagelist = new ArrayList<String>();
    ArrayList<String> khasaralist = new ArrayList<String>();
    StringBuilder sb = null;
    String DistId = new String();
    String TId = new String();
    String IlrId = new String();
    String patwarId = new String();
    String villlink = new String();
    String khasara = new String();
    Spinner mySpinner;
    Spinner Tehsilspinner;
    Spinner SpinnerVillage;
    private HttpURLConnection urlConnection;

    private Context context;
    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Download JSON file AsyncTask
        try {
            new DownloadJSON().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/getdistrict"));

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading..."); // Setting Message
            progressDialog.setTitle("ProgressDialog"); // Setting Title
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
            progressDialog.show(); // Display Progress Dialog
            progressDialog.setCancelable(false);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(6000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                }
            }).start();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
            // Locate the spinner in activity_main.xml
             mySpinner = (Spinner) findViewById(R.id.spinnerDistrict);
            mySpinner.setPrompt("जिला चुने ");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable
                    String id = c.getString("distCode");
                    String District = c.getString("distname");

                    DistrictId.add(id);
                    Districtlist.add(District);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Districtlist.add(0, "जिला चुने                                                           ▼");
            DistrictId.add(0,"0");
            // Spinner adapter
                           mySpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item ,
                        Districtlist));
                    mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long Id) {
                        TehsilId.clear();
                        Tehsillist.clear();
                        String Name = Districtlist.get(position);
                        DistId = DistrictId.get(position);
                       int pos =mySpinner.getSelectedItemPosition();
                        if (!Name.equals("जिला ")) {
                            String GetTehsilbydistId = "http://10.130.19.227/WebApiDistrict/api/values/GetTehsil?DistCode=";
                            String Url = GetTehsilbydistId + DistId;
                            try {
                                new DownloadJSON1().execute(new URL(Url));
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }

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
             Tehsilspinner = (Spinner) findViewById(R.id.spinnerTehsil);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable
                    String Tehid = c.getString("tehcode");
                    String Tehsil = c.getString("tehname");

                    TehsilId.add(Tehid);
                    Tehsillist.add(Tehsil);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Tehsillist.add(0, "तहसील चुने                                                        ▼");
            TehsilId.add(0, "0");

                // Spinner adapter

                Tehsilspinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        Tehsillist));


                Tehsilspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long Id) {
                        villagelist.clear();
                        villageIdList.clear();
                        String TehName = Tehsillist.get(position);
                        TId = TehsilId.get(position);
                        if (!TehName.equals("तहसील चुने ")) {
                            String GetTehsilbydistId = "http://10.130.19.227/WebApiDistrict/api/values/Getvillage?DistCode=";
                            String Url = GetTehsilbydistId + DistId;
                            String Url1 = Url + "&tehcode=";
                            String Url2 = Url1 + TId;

                            try {
                                new DownloadJSON2().execute(new URL(Url2));
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }

                });
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


                SpinnerVillage.setAdapter(new ArrayAdapter<String>(MainActivity.this,
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
    }
    public void GetKhata(View view)
    {
        /*String DistValue= mySpinner.getSelectedItem().toString();
        String Teh1Value= mySpinner.getSelectedItem().toString();
        String villValue= mySpinner.getSelectedItem().toString();*/
       // String dist = mySpinner.getSelectedItemId();
        int Distposition = mySpinner.getSelectedItemPosition();
        int Tehposition = Tehsilspinner.getSelectedItemPosition();
        int Villposition = SpinnerVillage.getSelectedItemPosition();

        if(Distposition==0 || Tehposition==0 || Villposition==0)
        {
                          Toast.makeText(getApplicationContext(),"कृपया जिला और तहसील और गाँव चुने ",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(MainActivity.this, getkhata.class);

//Create the bundle
            Bundle bundle = new Bundle();

//Add your data to bundle
            bundle.putString("DistId", DistId.toString());
            bundle.putString("TId", TId.toString());
            bundle.putString("Vill_link", villlink.toString());

//Add the bundle to the intent
            i.putExtras(bundle);
            startActivity(i);
        }
  }
  public void GetKhasara(View view)
  {
      int Distposition = mySpinner.getSelectedItemPosition();
      int Tehposition = Tehsilspinner.getSelectedItemPosition();
      int Villposition = SpinnerVillage.getSelectedItemPosition();

      if(Distposition==0 || Tehposition==0 || Villposition==0)
      {
          Toast.makeText(getApplicationContext(),"कृपया जिला और तहसील और गाँव चुने ",Toast.LENGTH_SHORT).show();

      }
      else {
          Intent i = new Intent(MainActivity.this, getkhasara.class);
          //Create the bundle
          Bundle bundle = new Bundle();
          //Add your data to bundle
          bundle.putString("DistId", DistId.toString());
          bundle.putString("TId", TId.toString());
          bundle.putString("Vill_link", villlink.toString());
          //Add the bundle to the intent
          i.putExtras(bundle);
          startActivity(i);
      }
  }
 public void GetOwnerInfo(View view)
    {
        int Distposition = mySpinner.getSelectedItemPosition();
        int Tehposition = Tehsilspinner.getSelectedItemPosition();
        int Villposition = SpinnerVillage.getSelectedItemPosition();

        if(Distposition==0 || Tehposition==0 || Villposition==0)
        {
            Toast.makeText(getApplicationContext(),"कृपया जिला और तहसील और गाँव चुने ",Toast.LENGTH_SHORT).show();

        }
        else {
            Intent i = new Intent(MainActivity.this, getOwnerInfo.class);
            //Create the bundle
            Bundle bundle = new Bundle();
            //Add your data to bundle
            bundle.putString("DistId", DistId.toString());
            bundle.putString("TId", TId.toString());
            bundle.putString("Vill_link", villlink.toString());
            //Add the bundle to the intent
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}



