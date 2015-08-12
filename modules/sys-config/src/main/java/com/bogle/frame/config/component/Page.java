package com.bogle.frame.config.component;

/**
 * Created by Administrator on 2015/5/23.
 */


import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2015/5/23.
 */
public class Page<T> implements Serializable {

    public static final int DEFAULT_SIZE = 10;
    private final List<T> content = new ArrayList<T>();

    private int page;
    private int size = DEFAULT_SIZE;
    private Sort sort;
    private long total;
    private Map<String,Object> condition = new HashMap<String, Object>();


    /**
     * 总页数
     *
     * @return
     */
    public int getTotalPages() {

        return getSize() == 0 ? 0 : (int) Math.ceil((double) total / (double) getSize());
    }

    /**
     * 当前页内容数目
     *
     * @return
     */
    public int getNumberOfElements() {

        return content.size();
    }

    /**
     * 总数量
     *
     * @return
     */
    public long getTotalElements() {

        return total;
    }

    public List<T> getContent() {
        return content;
    }

    public boolean addContent(T...t) {
        return Collections.addAll(this.content,t);
    }

    public boolean addContent(List<T> c) {
        return this.content.addAll(c);
    }

    public int getPage() {
        if(this.page <= 0) {
            page = 1;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * 是否有下一页
     * @return
     */
    public boolean hasPreviousPage() {
        return this.getPage() > 1;
    }

    /**
     * 是否是第一页
     * @return
     */
    public boolean isFirstPage() {
        return !this.hasPreviousPage();
    }

    /**
     * 是否有上一页
     * @return
     */
    public boolean hasNextPage(){
        return page < getTotalPages();
    }

    /**
     * 是否是最后一页
     * @return
     */
    public boolean isLastPage(){
        return this.hasNextPage();
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }
}
