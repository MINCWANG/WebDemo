package cn.seecu.bookstore.bean;

import java.io.Serializable;
import java.util.Date;


/**
 * @Auther: Wang MC
 * @Date: 2018/10/21 18:36
 * @Description:
 */
public class Order implements Serializable {
    /**
     * 订单编号
     */
    private String id;
    /**
     * 订单总数量
     */
    private int totalCount;
    /**
     * 订单总价格
     */
    private double totalAmount;
    /**
     * 订单创建时间
     */
    private Date orderTime;
    /**
     * 订单状态
     */
    private int state;
    /**
     * 订单所属用户
     */
    private int userId;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", totalCount=" + totalCount +
                ", totalAmount=" + totalAmount +
                ", orderTime=" + orderTime +
                ", state=" + state +
                ", userId=" + userId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Order() {
    }

    public Order(String id, int totalCount, double totalAmount, Date orderTime, int state, int userId) {
        this.id = id;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.orderTime = orderTime;
        this.state = state;
        this.userId = userId;
    }
}
