package com.literature.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 阅读纪录实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Read {

    private int id ;
    private int userId;
    private int workId;
    private String readTime;
    private String memo;
    private int del;

    /**
     * 阅读纪录扩展
     */

    private String title;
    private String author;
    private String type;
    private String brief;

}
