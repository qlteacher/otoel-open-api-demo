package com.qlteacher.demo.baseinfo;

import com.qlteacher.demo.AccessTokenDemo;
import com.qlteacher.demo.Constant;
import com.qlteacher.demo.pojo.conf.ConfigUtil;
import com.qlteacher.demo.utils.SystemOutEncode;
import lombok.SneakyThrows;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author 江立国
 */
public class DownloadRecommendLessonCaseDemo {

    public static void main(String[] args) {
        SystemOutEncode.outEncodeSet();
        String outPath = Constant.getFile("test-download-recommend-lesson-case.xlsx").getAbsolutePath();
        downloadRecommendLessonCase(outPath);
        System.out.println("下载文件到：" + outPath);
    }

    @SneakyThrows
    public static void downloadRecommendLessonCase(String outPath) {
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        //获取token
        String accessToken = AccessTokenDemo.getAccessToken();
        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                ConfigUtil.getConfig().getBaseInfo().getDownloadRecommendLessonCaseUrl().concat(String.format("?actId=%s", Constant.activityId)))
                .setAccessToken(accessToken).buildHeaderMessage();
        OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
        InputStream inputStream = resourceResponse.getBodyAsInputStream();
        //下载文件到outPath指定的文件
        File outFile = new File(outPath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(bytes)) != -1) {
            if (len > 0)
                out.write(bytes, 0, len);
        }
        out.flush();
        byte[] byteArray = out.toByteArray();
        FileOutputStream out1 = new FileOutputStream(outFile);
        out1.write(byteArray);
        out1.flush();
        out1.close();
    }
}
