package com.bosssoft.hr.train.jsp.example.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 温俊欣
 * 监听session的创建、销毁 进而统计在线人数
 */
public class CountOnline implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {

        //获取得到Context对象，使用Context域对象保存用户在线的个数
        ServletContext context = se.getSession().getServletContext();

        //直接判断Context对象是否存在这个域，如果存在就人数+1,如果不存在，那么就将属性设置到Context域中
        Integer num = (Integer) context.getAttribute("onlineUserCount");

        if (num == null) {
            context.setAttribute("onlineUserCount", 0);
        } else {
            num++;
            context.setAttribute("onlineUserCount", num);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //获取得到Context对象，使用Context域对象保存用户在线的个数
        ServletContext context = se.getSession().getServletContext();

        //直接判断Context对象是否存在这个域，如果存在就人数+1,如果不存在，那么就将属性设置到Context域中
        Integer num = (Integer) context.getAttribute("onlineUserCount");

        if (num == null) {
            context.setAttribute("onlineUserCount", 1);
        } else {
            num--;
            context.setAttribute("onlineUserCount", num);
        }
    }
}
