package cn.seecu.bookstore.dao;

import cn.seecu.bookstore.bean.Book;
import cn.seecu.bookstore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: WebDemo
 * @description: 封装对数据库的基本操作
 * @author: Mr.Wang
 * @create: 2018-09-06 09:44
 **/
public class BaseDAO<T> {
    /**
     * 数据库操作类  dbutils提供
     */
    private QueryRunner runner = new QueryRunner();
    /**
     * 泛型的类型
     */
    private Class<T> type;

    /**
     * BaseDAO 是提供给其他具体的DAO继承的，不会直接创建它的对象
     * UserDAO extends BaseDAO<User>
     * BaseDAO 构造器以后只会被子类对象调用
     */
    public BaseDAO() {
        // this 代表子类的对象
        // 获取子类的类型 UserDAO
        Class<? extends BaseDAO> aClass = this.getClass();
        // 获取父类的类型 获取带参数的父类类型 BaseDAO<User>
        ParameterizedType pt = (ParameterizedType) aClass.getGenericSuperclass();
        // 获取类的泛型列表
        Type[] types = pt.getActualTypeArguments();
        // 第一个位置就是所需要的泛型
        type = (Class<T>) types[0];
    }

    /**
     * 对数据库表增删改查操作
     *
     * @param sql
     * @param params
     * @return
     */
    public int upDate(String sql, Object... params) {
        Connection conn = JDBCUtils.getConn();
        int update = 0;
        try {
            /**
             * 返回影响了几条数据
             */
            update = runner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConn(conn);
        }
        return update;
    }

    /**
     * 批处理方法
     * @param sql   批处理执行的语句
     * @param params    第一维：sql语句需要的次数 第二维：sql语句需要的参数
     */
     public void bitchUpdate(String sql,Object[][] params){
         Connection conn = JDBCUtils.getConn();

         try {
             runner.batch(conn,sql,params);
         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
             JDBCUtils.releaseConn(conn);
         }
     }

    /**
     * 查询一条记录 并封装为对象的方法
     *
     * @param sql
     * @param params
     * @return
     */
    public T getBean(String sql, Object... params) {
        Connection conn = JDBCUtils.getConn();
        T t = null;
        try {
            t = runner.query(conn, sql, new BeanHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConn(conn);
        }

        return t;
    }

    /**
     * 查询多条记录 并封装为对象的方法
     *
     * @param sql
     * @param params
     * @return
     */
    public List<T> getBeanList(String sql, Object... params) {
        Connection conn = JDBCUtils.getConn();
        List<T> list = null;
        try {
            list = runner.query(conn, sql, new BeanListHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConn(conn);
        }
        return list;
    }

    /**
     * 查询记录总条数方法
     *
     * @param sql
     * @param params
     * @return
     */
    public long getCount(String sql, Object... params) {
        Connection conn = JDBCUtils.getConn();
        long query = 0;
        try {
            // ScalarHandler：默认将查找第一行第一列的数据并封装为对象返回
            query = (long) runner.query(conn, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConn(conn);
        }
        return query;
    }
}
