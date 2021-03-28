package com.yxjajxy.week2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyOkHttp {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8801")
                .build();

        Response response = client.newCall(request).execute();

        if (response != null) {
            System.out.println("响应状态为:" + response.toString());
            System.out.println("响应内容长度为:" + response.header("Content-Length"));
            System.out.println("响应内容为:" + response.body().string());
        }

    }
}
