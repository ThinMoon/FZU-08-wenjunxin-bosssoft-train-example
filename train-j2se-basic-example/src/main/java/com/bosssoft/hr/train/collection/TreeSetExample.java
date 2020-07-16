package com.bosssoft.hr.train.collection;

/**
 * Set用于存储不重复的元素集合
 * TreeSet是有序的，因为它实现了SortedSet接口。
 * 添加的元素必须正确实现Comparable接口，如果没有实现Comparable接口，那么创建TreeSet时必须传入一个Comparator对象
 * @author 温俊欣
 */
public interface TreeSetExample<T> {

    /**
     * 添加元素o
     * @param o
     * @return
     */
    boolean add(T o);

    /**
     * 移除元素
     * @param o
     * @return
     */
    boolean remove(T o);

    /**
     * 判断是否包含元素
     * @param o
     * @return
     */
    boolean contains(T o);


}
