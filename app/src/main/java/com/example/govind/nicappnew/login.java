package com.example.govind.nicappnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by nicsi on 05-07-2017.
 */
public class login extends AppCompatActivity {
    StringBuilder sb = null;
    private Context context;
    public HttpURLConnection urlConnection;
    ArrayList<String> Info = new ArrayList<String>();
    JSONArray jarray;
    String Distcode=new String();
    String Tehcode=new String();
    String DistName=new String();
    String TehName=new String();
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
          }
    public void cancel(View view)
    {
        Intent i = new Intent(login.this, loginselect.class);
        startActivity(i);
    }
    public void loginDirect(View view)
    {
        Intent i = new Intent(login.this, mutation_select.class);
        startActivity(i);
    }
/*   public void loginDirect(View view)
   {
       Intent i = new Intent(login.this, testnew.class);
       startActivity(i);
   }*/
    public void login(View view) {
        final EditText ed1,ed2;
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);
        String st1=ed1.getText().toString();
        String st2=ed2.getText().toString();
        if(st1.isEmpty() && st2.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"कृपया उपयोगकर्ता का नाम/पासवर्ड डाले ",Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                String encodedString = URLEncoder.encode(st2);
                new  login.DownloadJSON().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/Getlogin?code=" + st1 + "&password=" + encodedString+""));
                }

          catch (MalformedURLException e)
          {
            e.printStackTrace();
          }
        }
    }
    // Download JSON file AsyncTask
    private class DownloadJSON extends AsyncTask<URL, Void, JSONArray> {
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
                    JSONObject c = null;
            if(jsonArray.length()==0)
            {
                Toast.makeText(getApplicationContext(),"कृपया सही पासवर्ड दर्ज़ करे",Toast.LENGTH_SHORT).show();
                Intent j = new Intent(login.this, login.class);
                startActivity(j);
            }
               else
            {
                try {
                    c = jsonArray.getJSONObject(0);

                    // Storing each json item in variable
                    DistName = c.getString("DistName");
                    TehName = c.getString("TehName");
                    Distcode = c.getString("Distcode");
                    Tehcode = c.getString("Tehsilcode");

                    if(Distcode!=""|| Distcode!=null) {
                        Intent j = new Intent(login.this, mutation_select.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Distcode", Distcode);
                        bundle.putString("Tehcode", Tehcode);
                        bundle.putString("DistName", DistName);
                        bundle.putString("TehName", TehName);
                        j.putExtras(bundle);
                        startActivity(j);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"कृपया सही पासवर्ड दर्ज़ करे",Toast.LENGTH_SHORT).show();
                        Intent j = new Intent(login.this, login.class);
                        startActivity(j);
                    }
                }
                catch (final JSONException e) {
                    e.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
