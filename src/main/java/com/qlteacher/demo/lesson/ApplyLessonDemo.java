package com.qlteacher.demo.lesson;

import com.qlteacher.demo.AccessTokenDemo;
import com.qlteacher.demo.Constant;
import com.qlteacher.demo.pojo.conf.ConfigUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 申请课例上传
 *
 * @author 江立国 2024/8/14 16:43
 */
@Slf4j
public class ApplyLessonDemo {

    /**
     * 指导教师数
     */
    private static final int ADVISER_COUNT = 1;

    @SneakyThrows
    public static void main(String[] args) {
        log.error("========ApplyLessonDemo=========");
        String body = applyLesson(Constant.activityId, ConfigUtil.getConfig().getLesson().getCatalogId(), ADVISER_COUNT);
        File outFile = Constant.getFile(String.format("test-%s-%s.json", Constant.activityId, ConfigUtil.getConfig().getLesson().getCatalogId()));
        FileOutputStream out = new FileOutputStream(outFile);
        out.write(body.getBytes("utf-8"));
        out.flush();
    }

    @SneakyThrows
    public static String applyLesson(String activityId, String catalogId, int adviserCount) {
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        //获取token
        String accessToken = AccessTokenDemo.getAccessToken();
        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                ConfigUtil.getConfig().getLesson().getApplyLessonUrl())
                .setAccessToken(accessToken).buildHeaderMessage();
        bearerClientRequest.setBody(String.format("activityId=%s&catalogId=%s&adviserCount=%d", activityId, catalogId, adviserCount));
        OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
        if (resourceResponse.getResponseCode() != 200) {
            throw new RuntimeException(String.format("请求失败,状态码:%d,body:%s", resourceResponse.getResponseCode(), resourceResponse.getBody()));
        }
        String body = resourceResponse.getBody();
        //输出访问结果
        System.out.println(body);
        return body;

    }
}
