package org.taoding.test;

import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @Date 6/27/24 18:31
 * @Author Tao Ding
 * @Description: TODO
 */
//@SpringBootTest
public class HttpClientTest {
    private static final String token = "eyJhbGciOiJIUzM4NCJ9.eyJlbXBJZCI6MSwiZXhwIjoxNzIwMTk5OTY0fQ.UDICB3s71ZRTb9-i8jya9RXeeIV5rS78haMursfTS-PZzg9BiaHs0q_LwaRvVA_O";
    @Test
    public void GetTest(){

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/user/shop/status");
            httpGet.setHeader("token", token);
            try(CloseableHttpResponse response = httpClient.execute(httpGet)){
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println(statusCode);
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                System.out.println("body = " + body);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //@Test
    public void PostTest(){
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/admin/employee/login");
            httpPost.setHeader("token", token);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username","admin");
            jsonObject.put("password","123456");
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString());
            stringEntity.setContentEncoding("utf-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            try(CloseableHttpResponse response = httpClient.execute(httpPost)){
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println(statusCode);
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                System.out.println("body = " + body);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
