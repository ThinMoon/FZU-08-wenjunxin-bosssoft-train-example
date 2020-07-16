package com.bosssoft.hr.train.collection;

/**
 * @author 温俊欣
 */
public interface ArrayListExample<T> extends ListExample<T> {

    /**
     *
     * @param position
     * @param node
     * 将对象插入到指定位置
     * @return
     */
    boolean insert(Integer position,T node);


}
