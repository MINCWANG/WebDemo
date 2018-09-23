package cn.seecu.bookstore.service;

import cn.seecu.bookstore.bean.User;

/**
 * @program: WebDemo
 * @description:
 * @author: Mr.Wang
 * @create: 2018-09-06 23:37
 **/
public interface UserService {

    User login(User user);

    boolean regist(User user);
}
