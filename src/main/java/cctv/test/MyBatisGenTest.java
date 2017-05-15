package cctv.test;

import cctv.bean.User;
import cctv.mybatis.SQLColumnData;
import cctv.mybatis.SQLTableData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.config.Context;
import sun.security.krb5.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CCTV-1 on 2017/5/12.
 */
public class MyBatisGenTest {
    @Test
    public void testGenBeanStr(){
        SQLTableData sqlTableData = new SQLTableData();
        String result = sqlTableData.getBeanField("ssm_user");
        System.out.println(result);
    }

    @Test
    public void testGenBeanFile() throws IOException, TemplateException {
        SQLTableData sqlTableData = new SQLTableData();
        String className = sqlTableData.getTableName2ClassName("ssm_user");
        String modelPath = "G:\\IdeaProject\\Mybatis\\src\\main\\java\\cctv\\bean\\"+className+".java";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("className",className);
        map.put("hasPage",true);

        map.put("fields",sqlTableData.getBeanField("ssm_user"));

        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("G:\\IdeaProject\\Mybatis\\src\\main\\resources\\Theme"));

        Template t = config.getTemplate("JavaClass.ftl");
        t.process(map, new OutputStreamWriter(new FileOutputStream(modelPath)));
    }

    @Test
    public void testGenDaoFile() throws IOException, TemplateException {
        SQLTableData sqlTableData = new SQLTableData();
        String className = sqlTableData.getTableName2ClassName("ssm_user");
        String modelPath = "G:\\IdeaProject\\Mybatis\\src\\main\\java\\cctv\\dao\\"+className+"Dao.java";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("beanName",className);
        map.put("hasPage",true);

        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("G:\\IdeaProject\\Mybatis\\src\\main\\resources\\Theme"));

        Template t = config.getTemplate("JavaDao.ftl");
        t.process(map, new OutputStreamWriter(new FileOutputStream(modelPath)));
    }

    @Test
    public void testGenMapperFile() throws IOException, TemplateException {
        SQLTableData sqlTableData = new SQLTableData();
        String className = sqlTableData.getTableName2ClassName("ssm_user");
        String modelPath = "G:\\IdeaProject\\Mybatis\\src\\main\\resources\\mapping\\cctv\\dao\\"+className+"Mapper.xml";
        List<SQLColumnData> list = sqlTableData.getColsData("ssm_user");

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("package","cctv.dao.");
        map.put("beanName",className);
        map.put("properties",list);
        map.put("tableName","ssm_user");

        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("G:\\IdeaProject\\Mybatis\\src\\main\\resources\\Theme"));

        Template t = config.getTemplate("JavaMapper.ftl");
        t.process(map, new OutputStreamWriter(new FileOutputStream(modelPath)));
    }

    public SqlSession loadResourceByMybatis() throws IOException {
        Reader reader = Resources.getResourceAsReader("Configuration.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory.openSession();
    }

    @Test
    public void testMybatisQuery() throws IOException {
        SqlSession session = loadResourceByMybatis();
        User user = session.selectOne("cctv.dao.UserDao.getUserById", new Integer(1));
        System.out.println(user.getUserId()+user.getUsername()+user.getPassword()+user.getDescription());
    }

    @Test
    public void testMybatisQuerys() throws IOException {
        SqlSession session = loadResourceByMybatis();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(3);
        List<User> users = session.selectList("cctv.dao.UserDao.getUsersByIds", list);
        for(User user : users){
            System.out.println(user.getUsername()+user.getDescription());
        }
    }

    @Test
    public void testMybatisAdd() throws IOException {
        for(int i=0;i<10;i++){
            SqlSession session = loadResourceByMybatis();
            User user = new User();
            user.setUserId(4+i).setUsername("李四").setPassword("123456").setDescription("我就是李四");
            int j = session.insert("cctv.dao.UserDao.addUser", user);
            session.commit();
            System.out.println(i);
        }
    }

    @Test
    public void testMybatisUpdate() throws IOException {
        SqlSession session = loadResourceByMybatis();
        User user = new User();
        user.setUserId(2).setDescription("我真的是李四了");
        int i = session.update("cctv.dao.UserDao.updateUserById", user);
        session.commit();
        System.out.println(i);
    }

    @Test
    public void testMybatisDel() throws IOException {
        SqlSession session = loadResourceByMybatis();
        int i = session.delete("cctv.dao.UserDao.delUsersById", 2);
        session.commit();
        System.out.println(i);
    }

    @Test
    public void testMybatisDels() throws IOException {
        SqlSession session = loadResourceByMybatis();
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);

        int i = session.delete("cctv.dao.UserDao.delUsersByIds", list);
        session.commit();
        System.out.println(i);
    }

    @Test
    public void testMybatisCount() throws IOException {
        SqlSession session = loadResourceByMybatis();
        int i = session.selectOne("cctv.dao.UserDao.getUserListCount", null);
        System.out.println(i);
    }

    @Test
    public void testMybatisWithPage() throws IOException {
        SqlSession session = loadResourceByMybatis();
        User user = new User();
        user.setPageSize(3).setStartRow(1);
        List<User> users  = session.selectList("cctv.dao.UserDao.getUserListWithPage", user);
        for(User u : users){
            System.out.println(u.getUserId()+"-->"+u.getUsername()+u.getDescription());
        }
    }
}
