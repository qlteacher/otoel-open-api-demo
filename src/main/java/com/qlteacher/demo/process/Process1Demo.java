package com.qlteacher.demo.process;

import com.qlteacher.demo.Constant;
import com.qlteacher.demo.lesson.ApplyLessonDemo;
import com.qlteacher.demo.pojo.conf.ConfigUtil;
import com.qlteacher.demo.utils.SystemOutEncode;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 第一步 申请传课模板
 *
 * @author 江立国 2024/8/16 9:58
 */
public class Process1Demo {

    @SneakyThrows
    public static void main(String[] args) {
        SystemOutEncode.outEncodeSet();
        //申请传课模板
        String template = ApplyLessonDemo.applyLesson(Constant.activityId, ConfigUtil.getConfig().getLesson().getCatalogId(), 0);
        //获取保存传课模板文件
        File outFile = Constant.getFile(String.format("test-%s-%s.template", Constant.activityId, ConfigUtil.getConfig().getLesson().getCatalogId()));
        //保存传课模板
        FileOutputStream out = new FileOutputStream(outFile);
        out.write(template.getBytes("utf-8"));
        out.flush();
        out.close();
    }
}
