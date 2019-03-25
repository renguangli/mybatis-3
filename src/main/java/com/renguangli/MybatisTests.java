package com.renguangli;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis
 * Created by renguangli at 2019/1/14 11:09
 *
 * @since JDK1.8
 */
public class MybatisTests {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("com/renguangli/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User("zhangshan", "zhangsan");
        Long id = userMapper.insert(user);

        // 将代理类保存到文件
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{UserMapper.class});
        try (
                FileOutputStream fos = new FileOutputStream(new File("C:/Users/rengu/Desktop/$Proxy.class"))
        ) {
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.commit();
        System.out.println(id);
        System.out.println(user);
    }
}
