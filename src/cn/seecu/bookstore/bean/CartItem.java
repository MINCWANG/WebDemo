package cn.seecu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  购物项
 */
public class CartItem implements Serializable {
    /**
     * 购物车对应的图书
     */
    private Book book;
    /**
     * 当前图书的数量
     */
    private int count;
    /**
     * 当前单品的总金额
     *   计算得到【amount = count*book.getPrice()】
     */
    private double amount;

    public CartItem() {
    }

    public CartItem(Book book, int count) {
        this.book = book;
        this.count = count;

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
            // 使用BigDecimal转换精度
        BigDecimal bd1 = new BigDecimal(getBook().getPrice()+"");
        BigDecimal bd2 = new BigDecimal(getCount() + "");
        amount = bd1.multiply(bd2).doubleValue();
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", amount=" + getAmount() +
                '}';
    }
}
