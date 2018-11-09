package cn.seecu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Auther: Wang MC
 * @Date: 2018/10/18 11:01
 * @Description: 购物车
 */
public class ShoppingCart implements Serializable {
    /**
     * 购物项集合
     */
    private Map<String, CartItem> map = new LinkedHashMap<>();
    /**
     * 购物项所有商品的总数量
     * 遍历map得到
     */
    private int totalCount;
    /**
     * 所有商品的总价格
     * 遍历map得到
     */
    private double totalAmount;

    /**
     * 更新购物车的数量
     * @param bookId    修改的购物项id
     * @param count     修改的之后的数量
     */
    public void updateCount(String bookId,String count){
        CartItem item = map.get(bookId);
        int itemCount = item.getCount();
        // i的默认值是购物项之前的count
        int i = itemCount;
        try {
            i = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
//        System.out.println("update-item");
        // 修改cartItem的数量
        item.setCount(i);
    }

    /**
     * 根据id删除指定购物项
     * @param bookId
     */
    public void delCartItemByBookId(String bookId){
        map.remove(bookId);
    }
    /**
     * 清除购物车item
     */
    public void clearCart() {
        if (map != null) {
            map.clear();
        }
    }

    public ShoppingCart(Map<String, CartItem> map, int totalCount, double totalAmount) {
        this.map = map;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
    }

    public ShoppingCart() {

    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "map=" + map +
                ", totalCount=" + getTotalCount() +
                ", totalAmount=" + getTotalAmount() +
                '}';
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    public int getTotalCount() {
        // 每次调用此方法时初始化totalCount
        totalCount = 0;
        List<CartItem> itemList = getCartItemList();
        for (CartItem item : itemList) {
            totalCount += item.getCount();
        }
        return totalCount;
    }

//    public void setTotalCount(int totalCount) {
//        this.totalCount = totalCount;
//    }

    public double getTotalAmount() {

        totalAmount = 0;
        BigDecimal bd1 = new BigDecimal(totalAmount + "");
        List<CartItem> itemList = getCartItemList();
        for (CartItem item : itemList) {
            BigDecimal bd2 = new BigDecimal(item.getAmount() + "");
            bd1 = bd1.add(bd2);

        }
        totalAmount = bd1.doubleValue();
        return totalAmount;
    }

//    public void setTotalAmount(double totalAmount) {
//        this.totalAmount = totalAmount;
//    }

    /**
     * 提供将map转化为list的方法
     *
     * @return
     */
    public List<CartItem> getCartItemList() {
        // 将map所有的value封装到集合中
        Collection<CartItem> values = map.values();

        List<CartItem> cartItemList = new ArrayList<>(values);
        return cartItemList;

    }

    /**
     * 添加图书到购物车中
     * book对象是通过bookService.getBookById()方法 查询到的
     *
     * @param book
     */
    public void addBook2Cart(Book book) {
        // 1、从map中查询购物项CartItem
        CartItem cartItem = map.get(book.getId() + "");
        //2、判断是否能查到此购物项
        if (cartItem != null) {
            // 2.1、如果查到购物项，给购物项+1
            cartItem.setCount(cartItem.getCount() + 1);
        } else {
            // 2.2、 如果没有查到对应的购物项，根据图书id查询图书创建CartItem对象设置到map中
            //  根据图书创建购物项
            // 默认添加一本图书
            int count = 1;
            CartItem item = new CartItem(book, count);
            map.put(book.getId() + "", item);


        }
    }
}
