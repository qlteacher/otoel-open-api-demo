package com.qlteacher.demo.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * UpLessonCaseItemParamContent
 * @author 
 * @date 2024-07-24T09:19:36.329+08:00[Asia/Shanghai]
 */
@Data
public class UploadLessonCaseItemContentParam implements Serializable {

    private static final long serialVersionUID = -1461631833687742921L;
    /**
   * 原地址
   **/
  private String  url;
  /**
   * 显示地址
   **/
  private String  showUrl;
  /**
   * 文件类型
   **/
  private String  type;
  /**
   * 文件名
   **/
  private String  fileName;
  /**
   * 
   **/
  private String status;

}

