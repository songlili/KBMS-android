package com.cwp.kbms.Http;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HttpClient{
    private static  OkHttpClient client = new OkHttpClient();

    public static String getStringFromUrl(String url){
        String result = "";

        Request req = new Request.Builder().url(url).build();
        try {
            Response resp = client.newCall(req).execute();
            if (resp.isSuccessful()){
                result =  resp.body().string();
            }else {
                new IOException("bad exception"+resp).printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
/*
    public static String getJsonFromUrl(String url) throws IOException {
        Request req = new Request.Builder().url(url).build();
        Response resp = client.newCall(req).execute();
        if (resp.isSuccessful()){
            resp
        }

    }*/
}
