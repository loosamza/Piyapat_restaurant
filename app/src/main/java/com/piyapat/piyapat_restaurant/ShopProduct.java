package com.piyapat.piyapat_restaurant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShopProduct extends AppCompatActivity {
    //Explicit
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_product);

        listView = (ListView) findViewById(R.id.listView);

        //SynJSON
        synJSON();
    }

    private void synJSON() {
        SynJSON synJSON = new SynJSON();
        synJSON.execute();

    }

    //Create inner class synsjon

    public class SynJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                String strURL = "http://csclub.ssru.ac.th/s56122201021/food/php_get_foodtable.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                Log.d("ShopProduct", "doIn ===>" + e.toString());
                return null;

            }
        }//doinbackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.d("ShopProduct", "onPost ===>" + s);
                JSONArray jsonArray = new JSONArray(s);

                String[] iconStrings = new String[jsonArray.length()];
                String[] titleStrings = new String[jsonArray.length()];
                String[] priceStrings = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    iconStrings[i] = jsonObject.getString("Source");
                    titleStrings[i] = jsonObject.getString("Food");
                    priceStrings[i] = jsonObject.getString("Price");
                }

                FoodAdapter foodAdapter = new FoodAdapter(ShopProduct.this,
                        iconStrings, titleStrings, priceStrings);
                listView.setAdapter(foodAdapter);

            } catch (Exception e) {
                Log.d("ShopProduct", "onPost ===>" + e.toString());

            }
        }//onpostexecute
    }

}// Main Class
