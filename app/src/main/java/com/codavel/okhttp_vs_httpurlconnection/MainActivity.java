package com.codavel.okhttp_vs_httpurlconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Perform HttpURLConnection transfer
        HttpURLConnectionController httpURLConnectionController = new HttpURLConnectionController();
        httpURLConnectionController.performTransfer();

        // Perform OkHTTP transfer
        OkHTTPController okHTTPController = new OkHTTPController();
        okHTTPController.performTranfer();
    }
}
