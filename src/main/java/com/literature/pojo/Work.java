package com.literature.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文学作品实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work {

    private int id;
    private String title;
    private String content;
    private String author;
    private int authorId;
    private String type ;
    private int permission;
    private String brief;
    private String publishTime;
    private String updateTime;
    private int readCount;
    private int del ;
    private String memo;

}
