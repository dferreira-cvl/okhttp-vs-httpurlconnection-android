package com.codavel.okhttp_vs_httpurlconnection;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHTTPController {

    private OkHttpClient client;

    public OkHTTPController() {
        client = new OkHttpClient();
    }

    public void performTranfer() {
        Request getSpeedTest = new Request.Builder()
                .url("http://porto.speedtest.net.zon.pt:8080/speedtest/random5000x5000.jpg")
                .build();

        final long startTime = System.currentTimeMillis();

        client.newCall(getSpeedTest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("data", "failure " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    InputStream is = response.body().byteStream();
                    BufferedInputStream input = new BufferedInputStream(is);
                    byte[] data = new byte[64 * 1024];

                    long count;

                    while ((count = input.read(data)) != -1) {
                        // manage file
                    }

                    input.close();
                    Log.i("data", "okhttp time: " + (System.currentTimeMillis() - startTime) + "ms");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
