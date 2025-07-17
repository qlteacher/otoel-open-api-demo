package com.qlteacher.demo.lesson;

import com.qlteacher.demo.Constant;
import com.qlteacher.demo.pojo.conf.ConfigUtil;
import com.qlteacher.demo.utils.TestMarkerTemplate;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 测试添加课例
 *
 * @author 江立国 2024/8/15 8:19
 */
public class AddLessonDemo {

    private static String TEST_ACTIVITY_ID = Constant.activityId;

    private static String TEST_TITLE = "测试添加课例测试添加测试添加课例测试添加测试添加课例测试添加01";

    /**
     * 指导教师数
     */
    private static final int ADVISER_COUNT = 1;

    @SneakyThrows
    public static void main(String[] args) {
        String body = testAddLessonDemo();
        File outFile = Constant.getFile("test-result.json");
        FileOutputStream out = new FileOutputStream(outFile);
        out.write(body.getBytes("utf-8"));
        out.flush();
    }

    public static String testAddLessonDemo() {
        //申请添加课例 body是返回的模板
        String body = ApplyLessonDemo.applyLesson(TEST_ACTIVITY_ID, ConfigUtil.getConfig().getLesson().getCatalogId(), ADVISER_COUNT);
        //通过模板生成添加课例用提交数据
        body = TestMarkerTemplate.assemble(body, TEST_TITLE);
        //提交数据添加课例
        String result = SubmitLessonDemo.submitLessonDemo(body);
        //如果正常添加成功，就会返回完整的课例信息（包括课例结构信息）
        return result;
//        System.out.println(result);
    }
}
