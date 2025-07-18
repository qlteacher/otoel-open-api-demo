package com.qlteacher.demo.process;

import com.alibaba.fastjson.JSON;
import com.qlteacher.demo.Constant;
import com.qlteacher.demo.lesson.LessonCheckDemo;
import com.qlteacher.demo.pojo.conf.ConfigUtil;
import com.qlteacher.demo.pojo.vo.activity.UploadLessonCaseInfoVO;
import com.qlteacher.demo.utils.SystemOutEncode;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * 第六步 检查课例上传完成及转换状态
 * 课程所有必填项必须上传完成且转换完成，才能进入下一步
 *
 * @author 江立国 2024/8/16 18:32
 */
public class Process6Demo {

    @SneakyThrows
    public static void main(String[] args) {
        SystemOutEncode.outEncodeSet();
        File file = Constant.getFile(String.format("test-%s-%s-result.json", Constant.activityId, ConfigUtil.getConfig().getLesson().getCatalogId()));
        FileInputStream in = new FileInputStream(file);
        byte[] buf = new byte[in.available()];
        in.read(buf);
        in.close();
        String lessonBody = new String(buf, "utf-8");
        UploadLessonCaseInfoVO infoVO = JSON.parseObject(lessonBody, UploadLessonCaseInfoVO.class);
        List<String> errors = LessonCheckDemo.lessonCheck(infoVO.getId());
        if (errors.isEmpty()) {
            System.out.println("课例检查完成，课例已经补充完善，可以进行确认！");
        } else {
            System.out.println("课例检查完成,还有内容需要完善：" + errors.toString());
        }
    }
}
