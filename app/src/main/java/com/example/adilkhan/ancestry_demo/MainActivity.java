package com.example.adilkhan.ancestry_demo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText searchquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchquery = (EditText) findViewById(R.id.editText);
    }

    public void searchqueryfire(View v)
    {
        String item = searchquery.getText().toString();
        Log.d("adil",item);

        if(NetworkConnectivity.checkInternetConnection(MainActivity.this))
        {
            new GetItems().execute(item);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Internet connection not avaliable!",Toast.LENGTH_SHORT).show();
        }

    }

private class GetItems extends AsyncTask<String, Void, ArrayList<CemeteryDetails>>
{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<CemeteryDetails> doInBackground(String... params) {
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String url = "https://www.findagrave.com/cgi-bin/api.cgi?mode=cemetery&cemeteryName="+params[0]+"&limit=25&skip=0";
        //String url = "https://www.findagrave.com/cgi-bin/api.cgi?mode=memorialPhotos&memorialId=13756942";

        String jsonStr = sh.makeServiceCall(url);
        ArrayList<CemeteryDetails> cemdetails = new ArrayList<CemeteryDetails>();

        Log.e("adil", "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray cemetery = jsonObj.getJSONArray("cemetery");

                    for(int i=0;i<cemetery.length();i++)
                    {
                        JSONObject cemobject = cemetery.getJSONObject(i);
                        int cemeteryID = cemobject.getInt("cemeteryId");
                        String cemeteryName = cemobject.getString("cemeteryName");
                        String latitude = cemobject.getString("latitude");
                        String longitude = cemobject.getString("longitude");
                        String countryName = cemobject.getString("countryName");
                        String stateName  = cemobject.getString("stateName");
                        String countyName = cemobject.getString("countyName");
                        String cityName = cemobject.getString("cityName");

                        cemdetails.add(new CemeteryDetails(cemeteryID,cemeteryName,latitude,longitude,countryName, stateName, countyName, cityName));
                    }

                }
             catch (final JSONException e) {
                Log.e("adil", "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }

        } else {
            Log.e("adil", "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
      return cemdetails;
    }

   @Override
    protected void onPostExecute(ArrayList<CemeteryDetails> result) {

           Intent i = new Intent(MainActivity.this, MapsActivity.class);
           i.putExtra("adil",result);
           startActivity(i);
   }
}
}

