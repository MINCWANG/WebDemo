package cn.seecu.bookstore.bean;

import java.io.Serializable;

/**
 * @program: WebDemo
 * @description: 对应user表的
 * @author: Mr.Wang
 * @create: 2018-09-03 23:10
 **/
public class User implements Serializable {
    /**
     * user 的id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String  username;
    /**
     * 用户密码
     */
    private String  password;
    /**
     * 电子邮箱
     */
    private String  email;

    public User() {
        super();

    }

    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
