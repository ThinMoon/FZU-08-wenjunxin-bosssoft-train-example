package com.bosssoft.hr.train.collection;

/**
 * @description: 对Map的主要方法做测试
 * @author 温俊欣
 * @since
 **/
public interface MapExample<T,V> {
    /**
     *
     * @param key  map的键
     * @param value 值
     * @return 返回值 返回值为原先key所关联的value
     */
    V put(T key,V value);
    /**
     *
     * @param key  map的键
     * @return 返回值
     */
    V remove(T key);

    /**
     *
     * @param key  map的键
     * @return 返回值 是否存在的判断
     */
    boolean containsKey(T key);

    /**
     * 迭代方式1
     * 通过entrySet一个保存了key value的键值对的set集合进行遍历
     */
    void visitByEntryset();
    /**
     * 迭代方式2
     * 通过keySet一个保存了key值的set集合进行遍历
     * 可通过map.get(key)获得相应的value值
     */
    void visitByKeyset();
    /**
     * 迭代方式3
     * 通过一个保存了value值的collection集合进行遍历
     * 该方法无法获取key
     */
    void visitByValues();

}
