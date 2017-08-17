package com.example.govind.nicappnew;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.Calendar;

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
    CalendarView simpleCalendarView;
    EditText date;
    DatePickerDialog datePickerDialog;
ProgressDialog pd;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutation_entry);
        Bundle bundle = getIntent().getExtras();
        Districtcode = bundle.getString("Distcode");
        Tehsilcode = bundle.getString("Tehcode");
        pd=new ProgressDialog(mutationentry.this);
        pd.setMessage("Loading Data");
        pd.setCancelable(false);
        pd.show();
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
        date = (EditText) findViewById(R.id.date);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(mutationentry.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

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
            pd.dismiss();
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
                   // String Name = muttype.get(position);
                    mutidsetlected = mutid.get(position);
                   // int pos =mySpinner.getSelectedItemPosition();

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

    public void Getmutdata(View view)
    {
        Intent i = new Intent(mutationentry.this, bechan.class);
//Create the bundle
        Bundle bundle = new Bundle();
//Add your data to bundle
        bundle.putString("Date",date.getText().toString());
        bundle.putString("Villlink", villlink);
        bundle.putString("MutationId", mutidsetlected);
        bundle.putString("Maxmutation", maxmut);
//Add the bundle to the intent
        i.putExtras(bundle);
        startActivity(i);
    }
}
