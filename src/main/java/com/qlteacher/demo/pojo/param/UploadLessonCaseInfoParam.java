package com.qlteacher.demo.pojo.param;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 更新课例信息
 *
 * @author
 * @date 2024-07-24T09:19:36.329+08:00[Asia/Shanghai]
 */
@Data
public class UploadLessonCaseInfoParam implements Serializable {

    private static final long serialVersionUID = 4813262939125731611L;
    /**
     * 课例编号
     **/
    private String id;
    /**
     * 标题
     **/
    private String title;
    /**
     * 标签
     **/
    private List<String> tags;
    /**
     * 概要
     **/
    private String summary;
    /**
     * 学校编号
     **/
    private String schoolId;
    /**
     * 区域路径
     */
    private String regionPath;
    /**
     * 活动编号
     **/
    private String activityId;
    /**
     * 活动目录编号
     **/
    private String catalogId;
    /**
     * 学段学科
     */
    private String stage;
    /**
     * 创课团队
     **/
    private List<CreativeTeamDTO> authors;
    /**
     * 排名
     **/
    private Integer ranking;
    /**
     * 课程结构项目集合
     **/
    private List<UploadLessonCaseItemParam> structures = new ArrayList<>();

}

