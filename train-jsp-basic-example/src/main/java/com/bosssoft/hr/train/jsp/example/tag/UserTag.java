package com.bosssoft.hr.train.jsp.example.tag;


import com.bosssoft.hr.train.jsp.example.pojo.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @description: 定义<boss:userTag /> 标签
 * @author: Administrator
 * @create: 2020-05-29 13:50
 * @since
 **/
public class UserTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        //获取到request对象
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        JspWriter jspWriter = pageContext.getOut();

        ServletContext servletContext = request.getServletContext();
        ArrayList<User> loginUsers = (ArrayList<User>) servletContext.getAttribute("loginUsers");

        try {

            if(loginUsers != null) {
                for (User user : loginUsers) {
                    jspWriter.write(user.getName());
                    jspWriter.write("<br>");
                }
            } else {
                jspWriter.write("当前无用户在线");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }
}

