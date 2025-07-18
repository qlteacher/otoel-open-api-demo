package com.qlteacher.demo.lesson;

import com.alibaba.fastjson.JSON;
import com.qlteacher.demo.AccessTokenDemo;
import com.qlteacher.demo.Constant;
import com.qlteacher.demo.pojo.conf.ConfigUtil;
import com.qlteacher.demo.pojo.vo.SignatureVO;
import com.qlteacher.demo.pojo.vo.activity.UploadLessonCaseInfoVO;
import lombok.SneakyThrows;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

/**
 * 申请上传课例封面许可证
 *
 * @author 江立国 2024/8/15 10:39
 */
public class ApplyLessonCoverDemo {

    @SneakyThrows
    public static void main(String[] args) {
        File file = Constant.getFile("test-update.json");
        if(file.exists()) {
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            String body = new String(bytes, "utf-8");
            UploadLessonCaseInfoVO infoVO = JSON.parseObject(body, UploadLessonCaseInfoVO.class);
            SignatureVO signatureVO = applyLessonCoverDemo(infoVO.getId(), "big-cover.jpg");
            System.out.println(JSON.toJSONString(signatureVO));
        }
    }

    @SneakyThrows
    public static SignatureVO applyLessonCoverDemo(String id, String fileName) {
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        //获取token
        String accessToken = AccessTokenDemo.getAccessToken();
        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                ConfigUtil.getConfig().getLesson().getLessonCoverUrl().concat(id).concat("?fileName=").concat(URLEncoder.encode(fileName,"utf-8")))
                .setAccessToken(accessToken).buildHeaderMessage();
        OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
        String body = resourceResponse.getBody();
        SignatureVO signatureVO = JSON.parseObject(body, SignatureVO.class);
        return signatureVO;
    }
}
