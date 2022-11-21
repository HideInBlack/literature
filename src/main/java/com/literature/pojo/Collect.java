package com.literature.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户收藏实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect {

    private int id;
    private int userId;
    private int workId;
    private String collectTime;
    private String memo;
    private int del ;

    /**
     * 收藏夹扩展
     */
    private String title;
    private String author;
    private String type;
    private String brief;
}
