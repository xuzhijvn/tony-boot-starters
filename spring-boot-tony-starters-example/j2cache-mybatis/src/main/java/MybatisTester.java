import cn.hutool.core.util.RandomUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Random;

/**
 * 测试入口
 */
public class MybatisTester {

    public static void main(String[] args) {

        String resource = "/mybatis.xml";
        InputStream inputStream = MybatisTester.class.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            System.out.println("mybatis init.");

            Blog blog = new Blog();
            blog.setId(136);
            blog.setTitle("博客标题136");
            blog.setBody("博客内容136");
            session.insert("new", blog);

            System.out.println("blog inserted");
            session.commit();

//            Blog blog = new Blog();
//            blog.setId(111);
//            blog.setTitle("博客标题-" + RandomUtil.randomInt(100, 1000000));
//            session.update("update", blog);
//
//            System.out.println("blog updated");
            session.commit();
            for(int i=0;i<1000;i++) {
                SqlSession session1 = sqlSessionFactory.openSession();
                Blog db = session1.selectOne("read", 136);
                System.out.printf("id=%d,title=%s,body=%s,i=%s%n", db.getId(), db.getTitle(), db.getBody(), i);
                session1.commit();
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            System.exit(0);
        }

    }

}
