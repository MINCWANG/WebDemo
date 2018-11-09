package cn.seecu.bookstore.bean;

import java.util.List;

/**
 * @program: WebDemo
 * @description: 分页类
 * @author: Mr.Wang Mc
 * @create: 2018-09-30 09:58
 */
public class Page<T> {
    /**
     * 当前分页展示的图书集合
     */
    private List<T> data;
    /**
     * 当前的页码
     * 用户传入
     */
    private int pageNumber;
    /**
     * 总页码
     * 可以计算得到
     */
    private int totalPage;
    /**
     * 图书记录总条数
     * 查询表得到
     */
    private int totalCount;
    /**
     * 每页显示的记录条数
     * 设置好的
     */
    private int size;
    /**
     * 查询分页数据的起始索引
     * 可以计算得到
     */
    private int index;
    /**
     * 访问当前分页的路径
     */
    private String path;
    public Page() {
    }

    public Page(List<T> data, int pageNumber, int totalPage, int totalCount, int size, int index) {
        this.data = data;
        this.pageNumber = pageNumber;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.size = size;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Page{" +
                "data=" + data +
                ", pageNumber=" + pageNumber +
                ", totalPage=" + getTotalPage() +
                ", totalCount=" + totalCount +
                ", size=" + size +
                ", index=" + getIndex() +
                '}';
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNumber() {
        if (pageNumber < 1) {
            // 如同页码小于1 则显示首页
            pageNumber = 1;
        } else if (pageNumber > getTotalPage()) {
            // 如果页码，则显示最大页 末页
            pageNumber = getTotalPage();
        }
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 计算得到，不需要set()
     * 根据totalCount和size计算得到
     * page类中比较多的属性都是计算得到的，我们在方法中s使用属性时需要调用get()方法取值
     *
     * @return
     */
    public int getTotalPage() {
        if (getTotalCount() % getSize() == 0) {
            //总记录条数 整除 页记录数
            return getTotalCount() / getSize();
        } else {
            // 不能整除
            return getTotalCount() / getSize() + 1;
        }
    }

  /*  public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }*/

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 计算得到
     * size*(pageNumber-1)
     *
     * @return
     */
    public int getIndex() {
        return getSize() * (getPageNumber() - 1);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

  /*  public void setIndex(int index) {
        this.index = index;
    }*/
}
