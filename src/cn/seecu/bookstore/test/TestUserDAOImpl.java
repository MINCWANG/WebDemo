package cn.seecu.bookstore.test;

import cn.seecu.bookstore.bean.User;
import cn.seecu.bookstore.dao.UserDAO;
import cn.seecu.bookstore.dao.impl.UserDAOImpl;
import org.junit.Test;

/**
 * @program: WebDemo
 * @description: UserDAOImpl 测试
 * @author: Mr.Wang
 * @create: 2018-09-06 12:56
 **/
public class TestUserDAOImpl {
    private UserDAO dao = new UserDAOImpl();

    @Test
    public void test() {
        int i = dao.saveUser(new User(null, "zhang", "123456", "111@qq.com"));
        System.out.println(i);
    }
    @Test
    public void testQuery(){

        User user = dao.getUserByUsernameAndPassword(new User(null, "zhang", "123456", "111@qq.com"));
        System.out.println(user);
    }
}
