package com.qlteacher.demo.pojo.conf;

import lombok.Data;
import lombok.ToString;

/**
 * 配置
 *
 * @author 江立国 2024/8/12 11:21
 */
@Data
@ToString
public class Sd1s1kConfiguration {

    /**
     * 登录url
     */
    private String oauthUrl;

    /**
     * accessToken
     */
    private String accessToken;

    private ClientConf client;

    /**
     * 课例信息
     */
    private BaseInfoConf baseInfo;

    private LessonConf lesson;

}
