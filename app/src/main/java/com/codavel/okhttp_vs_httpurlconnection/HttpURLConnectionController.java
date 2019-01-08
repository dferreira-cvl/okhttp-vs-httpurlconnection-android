package com.codavel.okhttp_vs_httpurlconnection;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionController {

    private HTTPReqTask task;

    public HttpURLConnectionController() {
        task = new HTTPReqTask();
    }

    public void performTransfer() {
        task.execute();
    }

    private static class HTTPReqTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL("http://porto.speedtest.net.zon.pt:8080/speedtest/random5000x5000.jpg");
                final long startTime = System.currentTimeMillis();

                urlConnection = (HttpURLConnection) url.openConnection();

                int code = urlConnection.getResponseCode();
                if (code != 200) {
                    throw new IOException("Invalid response from server: " + code);
                }

                BufferedInputStream input = new BufferedInputStream(urlConnection.getInputStream());
                byte[] data = new byte[64 * 1024];

                long count;

                while ((count = input.read(data)) != -1) {
                    // manage file
                }

                input.close();
                Log.i("data", "httpurlconnection time " + (System.currentTimeMillis() - startTime) + "ms");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }
    }
}
