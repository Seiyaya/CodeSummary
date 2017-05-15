# 我的代码总结

## 利用freemarker生成mybatis相关文件(dao、mapper、bean)
 + 引入freemarker和测试用的mybatis
 ```xml
 <dependencys>
     <dependency>
        <groupId>org.freemarker</groupId>
        <artifactId>freemarker</artifactId>
        <version>2.3.9</version>
     </dependency>
     <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.2.1</version>
     </dependency>
 </dependencys>
 ```
 + 需要更新的内容
   1. 分页查询时候的模糊查询
   2. 使用网页的方式进行更简单的配置和修改