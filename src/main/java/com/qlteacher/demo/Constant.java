package com.qlteacher.demo;

import java.io.File;
import java.net.URL;

/**
 * 常量
 *
 * @author 江立国 2024/8/16 10:08
 */
public class Constant {

    /**
     * 2025年度项目编号
     */
    public static final String activityId = "sd2025";

    //临时跟目录
    public static final String baseOutPath = "/tmp/1s1k/";

    public static File getFile(String name) {
        File basedir = new File(baseOutPath);
        //如果目录不存在就创建目录
        if (!basedir.exists()) {
            basedir.mkdirs();
        }
        return new File(basedir, name);
    }

}
