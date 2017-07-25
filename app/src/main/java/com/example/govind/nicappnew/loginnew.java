package com.example.govind.nicappnew;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.net.URLEncoder;

/**
 * Created by nicsi on 12-07-2017.
 */

public class loginnew extends AppCompatActivity  {
    // Will show the string "data" that holds the results
    private HttpURLConnection urlConnection;
    JSONArray jarray;
    ArrayList<String> Info = new ArrayList<String>();
    TextView txtview1;
    TextView txtview2;
    StringBuilder sb = null;
    String data=new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        Button b2=(Button)findViewById(R.id.button);

    }

  /*  private class DownloadJSON extends AsyncTask<URL, Void, JSONArray> {
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
                        String  Distcode = c.getString("distcode");
                        String  Tehcode = c.getString("TehName");
                        Toast.makeText(getApplicationContext(),"hello dev",Toast.LENGTH_SHORT).show();
                        Info.add( Distcode);
                        Info.add( Tehcode);

                    } catch (final JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            else{

                Toast.makeText(getApplicationContext(),"please insert right info",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(loginnew.this, mutation_select.class);
                startActivity(i);
            }
        }
    }*/
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void login(View view)
    {
        try {
            txtview1=(TextView)findViewById(R.id.editText);
            txtview2=(TextView)findViewById(R.id.editText2);
            String txt1String = txtview1.getText().toString();
            String password=txtview2.getText().toString();
            String encodedString = URLEncoder.encode(password);
            if(txt1String.isEmpty() && password.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"कृपया userid and password dale ",Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                  //new loginnew.DownloadJSON().execute(new URL("http://10.130.19.227/WebApiDistrict/api/values/Getlogin?code=" + txt1String + "&password=" + encodedString +""));
                    // Instantiate the RequestQueue.
                    String url = "http://10.130.19.227/WebApiDistrict/api/values/Getlogin?code=" + txt1String + "&password=" + encodedString +"";
                    JsonObjectRequest jsObjRequest = new JsonObjectRequest
(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
  @Override
  public void onResponse(JSONObject response) {
      String Distcode = null;
      String Tehcode = null;
      try {
          Distcode = response.getString("distcode");
      } catch (JSONException e) {
          e.printStackTrace();
      }
      try {
           Tehcode = response.getString("TehName");
      } catch (JSONException e) {
          e.printStackTrace();
      }
      Info.add(Distcode.toString());
      Info.add(Tehcode.toString());
  }
                    }, new Response.ErrorListener() {

  @Override
  public void onErrorResponse(VolleyError error) {
  // TODO Auto-generated method stub

  }
                    });

// Add the request to the RequestQueue.
                    MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(loginnew.this, mutation_select.class);
//Create the bundle
                String newdata=Info.get(0);
                String newdata1=Info.get(0);
                Bundle bundle = new Bundle();
//Add your data to bundle
                bundle.putString("Distcode",newdata);
                bundle.putString("Tehcode", newdata1);
         //   bundle.putString("Distcode",Info.get(0));
           // bundle.putString("Tehcode", Info.get(1));
//Add the bundle to the intent
                i.putExtras(bundle);
                startActivity(i);
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
