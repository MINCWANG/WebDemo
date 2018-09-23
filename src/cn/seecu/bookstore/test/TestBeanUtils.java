package cn.seecu.bookstore.test;

import cn.seecu.bookstore.bean.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @loginUser Wang MC
 * @program: WebDemo
 * @description: 测试BeanUtils
 * @author: Mr.Wang mc
 * @create: 2018-09-23 12:58
 **/
public class TestBeanUtils {
    /**
     * 标准的JavaBean
     * 私有属性
     * 共有的getter setter方法
     * 一个无参构造器
     * 有参构造器
     * toString()
     */
    @Test
    public void Test() {
        // 使用BeanUtils工具类   BeanUtils 依赖于 logging.jar
        // 可以将Map 的值封装给一个空的JavaBean
        //1.有数据的Map   map中存放键值对 key=JavaBean的属性名
        // 》》BeanUtils 根据key通过反射调用getXXX方法给属性值赋值
        // 属性是私有的 beanUtils在给JavaBean赋值时，找的是get或者set方法，和属性名无关
        // beanUtils 不管私有属性，只考虑set get方法
        Map map = new HashMap();
        map.put("id", "11");
        map.put("username", "xiaowang");
        map.put("password", "12345");
        map.put("email", "31651@qq.com");
        // 2.一个空的JavaBean对象
        User user = new User();
        System.out.println(user);
        // 使用beanUtils封装属性值
        try {
            BeanUtils.populate(user,map);
            System.out.println(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 以将一个初始化的JavaBean封装为一个Map
        // 提供一个初始过的JavaBean
        try {
            Map map1 = BeanUtils.describe(user);
            System.out.println(map1);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
