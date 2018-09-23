package cn.seecu.bookstore.dao.impl;

import cn.seecu.bookstore.bean.User;
import cn.seecu.bookstore.dao.BaseDAO;
import cn.seecu.bookstore.dao.UserDAO;

/**
 * @program: WebDemo
 * @description: 实现UserDAO
 * @author: Mr.Wang 
 * @create: 2018-09-06 12:23
 **/
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    /**
     * 如果返回值是null，代表查询不到用户，登录失败
     * 如果不为null，查询成功
     */
    @Override
    public User getUserByUsernameAndPassword(User user) {
        //sql可以确定时就写上
        String sql = "SELECT id , username , password , email FROM bs_user WHERE username=? AND password=? ";
        //用户名和密码起始在用户提交登录请求servlet获取之后传过来的
        return this.getBean(sql, user.getUsername(),user.getPassword());
    }

    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO bs_user(username , password, email) VALUES(?,?,?)";
        return this.upDate(sql, user.getUsername(),user.getPassword(),user.getEmail());
    }


}
