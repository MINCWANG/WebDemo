package cn.seecu.bookstore.bean;

import java.io.Serializable;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/21 18:41
 * @Description:
 */
public class OrderItem implements Serializable {
    /**
     * 订单项Id
     */
    private Integer id;
    /**
     * 订单项图书标题
     */
    private String title;
    /**
     * 订单项图书作者
     */
    private String author;
    /**
     * 订单项默认封面
     */
    private String imgPath;
    /**
     * 订单项单价
     */
    private double price;
    /**
     * 订单项总数量
     */
    private int count;
    /**
     * 订单项总金额
     */
    private double amount;
    /**
     * 订单项所属的订单id
     *  通过id将订单项与订单关联
     */
    private String orderId;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", amount=" + amount +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderItem() {

    }

    public OrderItem(Integer id, String title, String author, String imgPath, double price, int count, double amount, String orderId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imgPath = imgPath;
        this.price = price;
        this.count = count;
        this.amount = amount;
        this.orderId = orderId;
    }
}
