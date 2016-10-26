package com.piyapat.piyapat_restaurant;

import android.app.DownloadManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Explicit
    private UserTABLE objUserTABLE;
    private FoodTABLE objFoodTABLE;
    private OrderTABLE objOrderTABLE;
    private MySQLite mySQLite;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Connected SQLite
        connectedSQLite();

        //Test add Value
        // testAddValue();

        // Syn and Delete
        synAndDelete();

        //Request SQLite
        mySQLite = new MySQLite(this);

    }//onCreate

    private void synAndDelete() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(mySQLiteOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(mySQLite.user_table, null, null);
        MySynJSON mySynJSON = new MySynJSON();
        mySynJSON.execute();
    }

    private void testAddValue() {

        objUserTABLE.addNewUser("testUser", "testPass", "testName");
        objFoodTABLE.addNewFood("testFood", "testSource", "testPrice");
        objOrderTABLE.addNewOder("testOfficer", "testDesk", "testFood", "testItem");


    }//test

    private void connectedSQLite() {
        objFoodTABLE = new FoodTABLE(this);
        objUserTABLE = new UserTABLE(this);
        objOrderTABLE = new OrderTABLE(this);
    }

    //Create Inner Class for cConnected JSON
    private class MySynJSON extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                String strURL = "http://csclub.ssru.ac.th/s56122201021/food/php_get_userTABLE.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("JSON", "doInBack === >" + e.toString());
                return null;
            }

        } //doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("JSON", "strJSON ===> " + s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strUser = jsonObject.getString(objUserTABLE.COLUMN_USER);
                    String strPassword = jsonObject.getString(objUserTABLE.COLUMN_PASS);
                    String strName = jsonObject.getString(objUserTABLE.COLUMN_NAME);
                    objUserTABLE.addNewUser(strUser, strPassword, strName);
                }//for
                Toast.makeText(getApplicationContext(), "Synchronize mySQL to SQLIte Finish", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("JSON", "onPost == > " + e.toString());

            }

        }// onPostExecute

    }// MySynJson
}//Main class
