package com.yxjajxy.week2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyHttpClient {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        CloseableHttpResponse response = null;

        response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();

        System.out.println("响应状态为:" + response.getStatusLine());
        if (responseEntity != null) {
            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
            System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
        }

        httpClient.close();
        response.close();
    }
}
