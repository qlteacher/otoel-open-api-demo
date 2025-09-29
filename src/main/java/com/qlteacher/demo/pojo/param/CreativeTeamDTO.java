package com.qlteacher.demo.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创作团队成员
 *
 * @author
 * @date 2024-07-24T09:19:36.329+08:00[Asia/Shanghai]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreativeTeamDTO implements Serializable {

    private static final long serialVersionUID = 2055996852482017610L;
    /**
     * 用户编号
     **/
    private String userId;
    /**
     * 学校编号
     **/
    private String schoolId;
    /**
     * 类型
     **/
    private String type;

    private String userName;
}

