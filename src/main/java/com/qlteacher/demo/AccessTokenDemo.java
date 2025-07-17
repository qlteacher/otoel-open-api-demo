package com.qlteacher.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import com.qlteacher.demo.pojo.conf.ConfigUtil;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取获取登录token demo
 */
public class AccessTokenDemo {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        String accessToken = getAccessToken();

        System.out.println(String.format("accessToken:[%s]", accessToken));
    }

    @SneakyThrows
    public static String getAccessToken() {
        // TODO 示例中采用配置节存储，建议改为将accessToken缓存后使用，有效期建议一小时，过期可以使用refresh_token刷新或重新获取
        if (!ObjectUtils.isEmpty(ConfigUtil.getConfig().getAccessToken())) {
            return ConfigUtil.getConfig().getAccessToken();
        }
        // 创建HttpClient实例
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(ConfigUtil.getConfig().getOauthUrl());

        // 设置请求头
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Authorization", "Basic MXMxazoxczFr");

        // 设置请求参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", ConfigUtil.getConfig().getClient().getUserName()));
        params.add(new BasicNameValuePair("password", ConfigUtil.getConfig().getClient().getUserPassword()));
        params.add(new BasicNameValuePair("scope", "account"));
        params.add(new BasicNameValuePair("grant_type", "password"));
        httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        // 执行请求
        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost)) {
            int statusCode = response.getCode();

            // 处理响应
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            if (statusCode == 200) {
                return parseAccessToken(responseBody);
            } else {
                throw new IOException("HTTP错误 " + statusCode + ": " + responseBody);
            }
        }
    }

    @SneakyThrows
    private static String parseAccessToken(String jsonResponse) {
        // 将JSON字符串转换为ObjectNode
        ObjectNode rootNode = objectMapper.readValue(jsonResponse, ObjectNode.class);
        String accessToken = "";

        if(rootNode.has("code")){
          IntNode code = (IntNode) rootNode.get("code");
          if(!ObjectUtils.isEmpty(code)){
              if(code.intValue() != 200){
                  System.err.printf("请求获取accessToken失败,code:[%d],msg:[%s]", code.intValue(), rootNode.get("msg").asText());
                  return null;
              }
          }
        }

        // 检查响应是否包含data字段
        if (rootNode.has("data")) {
            ObjectNode dataNode = (ObjectNode) rootNode.get("data");

            // 从data字段中获取access_token
            if (dataNode.has("access_token")) {
                accessToken = dataNode.get("access_token").asText();
            } else {
                throw new IllegalArgumentException("响应数据中缺少access_token字段");
            }
            // 过期时间expires_in
            // 刷新refresh_token
        } else {
            throw new IllegalArgumentException("响应数据中缺少data字段");
        }
        System.out.println(String.format("请求获取accessToken:[%s]", accessToken));
        return accessToken;
    }
}
