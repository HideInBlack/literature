# 文学网站

## 一.模块功能说明:

1.用户搜索:用户可以通过关键字进行作品搜集查找。√

2.用户管理:用户管理是对所有人身份的管理。

3.读者管理:读者对阅读相关的增删改查。√

4.作者管理:作者相关的作品进行增删改查。√

5.论坛管理员系统:论坛管理员是对所有的作品增删改查的功能 

难点

1.作品信息管理：可以添加增加、删除、修改作品信息。√

2.查询图书信息：通过关键字查找作品并在用户查询时智能推荐相似图书。√

创新之处:

读者可以对喜爱文学作品的进行收藏。 √

（1）新书发行，热门书籍，个人收藏，

（2）在线查看

## 二.数据库设计(literature)

1.用户表：lt_user( id , username , email , password , create_time ,motto,del , memo ,role) 

2.文学作品表：lt_work(id ,title ,content,author ,author_id ,type ,publish_time ,update_time, del ,memo)

3.阅读表：lt_read(id , user_id , work_id ,read_time , memo ,del )

4.收藏表：lt_collect(id ,user_id , work_id ,collect_time ,memo, del)

## 三.补充
一共有三个权限：
    1.admin：管理员权限，管理员可以管理所有作品，所有的用户的身份管理，也可以收藏阅读作品
    2.作者权限：可以写作。
    3.读者权限：只能阅读作品，不可以创作，可以向管理员申请作者权限进行写作