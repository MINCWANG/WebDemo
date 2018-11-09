package cn.seecu.bookstore.dao;

import cn.seecu.bookstore.bean.User;

import javax.jws.soap.SOAPBinding;

/**
 * @program: WebDemo
 * @description: 约束bs_user对表的操作
 * @author: Mr.Wang
 * @create: 2018-09-06 12:17
 **/
public interface UserDAO {
    /**
     * 根据账号密码查询user对象
     * @param user  封装了用户名和密码
     *
     * @return  返回值封装了用户的所有信息
     */
    User getUserByUsernameAndPassword(User user);

    /**
     *
     * @param user  封装了账号 密码 email
     * @return  影响了几条数据 返回值<=0 代表插入失败
     */
    int saveUser(User user);

    /**
     * 通过Username在数据库中查询用户是否存在
     * @param name
     * @return
     */
    User checkUerByUsername(String name);

}
