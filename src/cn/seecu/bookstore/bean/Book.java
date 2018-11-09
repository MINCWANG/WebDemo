package cn.seecu.bookstore.bean;

/**
 * @program: WebDemo
 * @description: Book 实例
 * @author: Mr.Wang Mc
 * @create: 2018-09-26 23:42
 */
public class Book {
    /**
     * 图书id
     */
    private Integer id;
    /**
     * 图书标题
     */
    private String title;
    /**
     * 图书作者
     */
    private String author;
    /**
     * 图书img路径
     */
    private String imgPath;
    /**
     * 图书价格
     */
    private double price;
    /**
     * 图书销量
     */
    private int sales;
    /**
     * 图书库存
     */
    private int stock;


    public Book(Integer id, String title, String author, String imgPath, double price, int sales, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imgPath = imgPath;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", price='" + price + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                '}';
    }

    public Book() {
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

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
