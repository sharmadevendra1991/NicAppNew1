package com.example.govind.nicappnew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

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
 * Created by nicsi on 27-09-2017.
 */

public class bechanadd extends Activity {
    ArrayList<String> Ownernamelist = new ArrayList<String>();
    ArrayList<String> Owneridlist = new ArrayList<String>();
    ArrayList<String> gkhatalist = new ArrayList<String>();
    ArrayList<String> spinnerarray = new ArrayList<String>();
    ArrayList<String> spinnerRelationName = new ArrayList<String>();
    ArrayList<String> spinnerRelationCode = new ArrayList<String>();
    ArrayList<String> spinnerCategoryName = new ArrayList<String>();
    ArrayList<String> spinnerCategoryCode = new ArrayList<String>();
    ArrayList<String> spinnerCastName = new ArrayList<String>();
    ArrayList<String> spinnerCastCode = new ArrayList<String>();
    ArrayList<String> spinnerLandTypeName = new ArrayList<String>();
    ArrayList<String> spinnerLandTypeCode = new ArrayList<String>();
    ArrayList<String> spinnerarrayid = new ArrayList<String>();
    ArrayList<String> khasaralist = new ArrayList<String>();
    private HttpURLConnection urlConnection;
    StringBuilder sb = null;
    String Date = new String();
    String Villlink = new String();
    String MutationId = new String();
    String Maxmutation = new String();
    String KhataSelected=new String();
    String Ownerid=new String();
    String RelationCode=new String();
    String CategoryCode=new String();
    String CastCode=new String();
    String LandTypeCode=new String();
    Spinner Khata;
    Spinner OwnerSpinner;
    Spinner Relation;
    Spinner Category;
    Spinner Cast;
    Spinner LandType;
    private Button btn_add;
    private ListView lv_list;
    private EditText OwnerName,Niwasi,oldHissa,NewHissa;
    String RelationName=new String();
    String CategoryName=new String();
    String CastName=new String();
    String LandTypeName=new String();
    private CheckBox chk_Nabalig;
    private RadioGroup rg_sellbuy;
    String radiosell_buy="";
    CheckBox repeatChkBx;
    private ArrayList<Item> array;
    private Adapter adapter;
    int rows=3;

    String Name = new String();
    String Relationnamenew = new String();
    String Relationcode=new String();
    String nabalig=new String();
    String father=new String();
    String Husband=new String();
    String Guardion=new String();
    String Caste=new String();
    String OIndArea=new String();
    String Ohissa=new String();
    String Nhissa=new String();
    String Niwasinew=new String();
    String Owntype=new String();
    String Owntypetext=new String();
    String Parents=new String();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bechan_add);
        try {

            new bechanadd.DownloadJSON2().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetMobapp_FillMutsale1"));
            new bechanadd.DownloadJSON3().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetMobapp_FillMutsale2"));
            new bechanadd.DownloadJSON5().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetMobapp_FillMutsale4"));
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    class DownloadJSON2 extends AsyncTask<URL, Void, JSONArray> {
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

            Relation = (Spinner) findViewById(R.id.relation);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable
                    String Name = c.getString("Name");
                    String Code = c.getString("Code");
                    spinnerRelationName.add(Name);
                    spinnerRelationCode.add(Code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            spinnerRelationName.add(0, "चयन ▼");
            Relation.setAdapter(new ArrayAdapter<String>(bechanadd.this,
                    android.R.layout.simple_spinner_dropdown_item ,
                    spinnerRelationName));
            Relation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long Id) {
                    // Ownernamelist.clear();
                    // Owneridlist.clear();
                    RelationCode = spinnerRelationCode.get(position);
                    // int pos =Khata.getSelectedItemPosition();

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }
    class DownloadJSON3 extends AsyncTask<URL, Void, JSONArray> {
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

            Category = (Spinner) findViewById(R.id.jati);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable
                    String Name = c.getString("Name");
                    String Code = c.getString("Code");
                    spinnerCategoryName.add(Name);
                    spinnerCategoryCode.add(Code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            spinnerCategoryName.add(0, "चयन   ▼");
            Category.setAdapter(new ArrayAdapter<String>(bechanadd.this,
                    android.R.layout.simple_spinner_dropdown_item ,
                    spinnerCategoryName));
            Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long Id) {
                    spinnerCastName.clear();
                    spinnerCastCode.clear();
                    CategoryCode = spinnerCategoryCode.get(position);
                    // int pos =Khata.getSelectedItemPosition();
                    if (position!=0) {

                            try {
                            new bechanadd.DownloadJSON4().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/GetMobapp_FillMutsale3?jama="+CategoryCode));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
//                        String Url = "http://10.130.19.227/WebApiDistrict/api/values/GetuserInfoByKhata?vill_link=" + Villlink + "&gkhata=" + KhataSelected;
//                        try {
//                            new bechan.DownloadJSON1().execute(new URL(Url));
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }
    class DownloadJSON4 extends AsyncTask<URL, Void, JSONArray> {
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

            Cast = (Spinner) findViewById(R.id.jatinew);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable
                    String Name = c.getString("Name");
                    String Code = c.getString("Code");
                    spinnerCastName.add(Name);
                    spinnerCastCode.add(Code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //  gkhatalist.add(0, "खाता चुने   ▼");
            Cast.setAdapter(new ArrayAdapter<String>(bechanadd.this,
                    android.R.layout.simple_spinner_dropdown_item ,
                    spinnerCastName));
            Cast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long Id) {
                    //   Ownernamelist.clear();
                    //  Owneridlist.clear();
                    CastCode = spinnerCastCode.get(position);
                    // int pos =Khata.getSelectedItemPosition();

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }
    class DownloadJSON5 extends AsyncTask<URL, Void, JSONArray> {
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
            LandType = (Spinner) findViewById(R.id.spinnerLandType);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = null;
                try {
                    c = jsonArray.getJSONObject(i);
                    // Storing each json item in variable
                    String Name = c.getString("Name");
                    String Code = c.getString("Code");
                    spinnerLandTypeName.add(Name);
                    spinnerLandTypeCode.add(Code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            spinnerLandTypeName.add(0, "चयन  ▼");
            LandType.setAdapter(new ArrayAdapter<String>(bechanadd.this,
                    android.R.layout.simple_spinner_dropdown_item ,
                    spinnerLandTypeName));
            LandType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long Id) {
                  /*  Ownernamelist.clear();
                    Owneridlist.clear();*/
                    LandTypeCode = spinnerLandTypeCode.get(position);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }
}
