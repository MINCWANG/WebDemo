package cn.seecu.bookstore.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @program: WebDemo
 * @description: 把web的请求通过BeanUtils进行封装
 * @author: Mr.Wang mc
 * @create: 2018-09-23 14:03
 **/
public class WebUtils {
    /**
     * 从request对象中获取参数自动封装给对应的JavaBean
     *
     * @param request 请求对象，为了获取请求参数的集合
     * @param t       集合要封装的哪个对象中
     * @param <T>
     * @return
     */
    public static <T> T param2Bean(HttpServletRequest request, T t) {
        //1.获取map 数据源
        Map<String, String[]> Map = request.getParameterMap();
        //2.调用beanUtils方法将集合封装
        try {
            BeanUtils.populate(t, Map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }
}
