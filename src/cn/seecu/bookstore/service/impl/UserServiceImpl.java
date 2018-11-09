package cn.seecu.bookstore.service.impl;

import cn.seecu.bookstore.bean.User;
import cn.seecu.bookstore.dao.UserDAO;
import cn.seecu.bookstore.dao.impl.UserDAOImpl;
import cn.seecu.bookstore.service.UserService;

/**
 * @program: WebDemo
 * @description: Service的实现
 * @author: Mr.Wang
 * @create: 2018-09-06 23:39
 **/
public class UserServiceImpl implements UserService {
    private UserDAO dao = new UserDAOImpl();
    // 处理业务逻辑登录和注册简单调用DAO层就可以实现

    @Override
    public User login(User user) {


        return dao.getUserByUsernameAndPassword(user);
    }

    @Override
    public boolean regist(User user) {
        return dao.saveUser(user)>0;
    }

    // 如果user==null true 代表用户名可用
    // 如果user==null false 代表用户名已占用
    @Override
    public boolean checkUserByUsername(String name) {
        User user = dao.checkUerByUsername(name);
        return user==null;
    }
}
