package com.piyapat.piyapat_restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignupActivity extends AppCompatActivity {
    private EditText usernameEdt, passwordEdt, nameEdt;
    private String strUsername, strPassword, strName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //BlindWidget
        initWidget();

    }// On create

    private void initWidget() {
        usernameEdt = (EditText) findViewById(R.id.usernameEdt);
        passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        nameEdt = (EditText) findViewById(R.id.nameEdt);


    } // initwidget

    public void signUpClick(View view) {
        strUsername = usernameEdt.getText().toString().trim();
        strPassword = passwordEdt.getText().toString().trim();
        strName = nameEdt.getText().toString().trim();

        //check space
        if (strUsername.length() == 0 || strPassword.length() == 0 || strName.length() == 0) {
            //have space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "กรอกไม่ครบ", "กรุณากรอกให้ครบด้วย !!");
        } else {
            // no space
            upDateValueToServer();
        }
    }

    private void upDateValueToServer() {

        new AsyncTask<Void, Void, String>() {
            String strURL = "http://csclub.ssru.ac.th/s56122201021/food/add_user.php";
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("User", strUsername)
                    .add("Password", strPassword)
                    .add("Name", strName)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strURL).post(requestBody).build();


            @Override
            protected String doInBackground(Void... params) {
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();

                }

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //finish();
            }
        }.execute();

        /*call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    Toast.makeText(getApplicationContext(), "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    Log.d("addValue", "error " + e.toString());
                }

            }
        });*/

    }

}// Main class
