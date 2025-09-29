package com.qlteacher.demo.pojo.param;

import com.qlteacher.demo.pojo.dto.lessoncasestructure.UpLessonCaseItemLimitDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 上传课例子项提交参数
 *
 * @author
 * @date 2024-07-24T09:19:36.329+08:00[Asia/Shanghai]
 */
@Data
public class UploadLessonCaseItemParam implements Serializable {

    private static final long serialVersionUID = -2075318264327931460L;
    /**
     * 活动编号
     */
    private String activityId;

    /**
     * 课例编号
     **/
    private String lessonCaseId;

    /**
     * 结构项编号
     */
    private String lessonCaseStructureId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 文件数
     */
    private Integer maxFileCount;

    /**
     * 限制，类型type等于data时才有
     */
    private List<UpLessonCaseItemLimitDTO> limit;

    /**
     * 内容，类型type等于data时才有
     */
    private List<UploadLessonCaseItemContentParam> content;

    /**
     * 标题，类型type等于info时才有
     */
    private String title;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 简介
     */
    private String summary;

    /**
     * 讲师编号，类型type等于info时才有
     */
    private String lecturerId;

    /**
     * 讲师姓名，类型type等于info时才有
     */
    private String lecturerName;

    /**
     * 下级节点
     */
    private List<UploadLessonCaseItemParam> children;

}

