package com.bosssoft.hr.train.jsp.example.controller;

import com.bosssoft.hr.train.jsp.example.pojo.Query;
import com.bosssoft.hr.train.jsp.example.pojo.User;
import com.bosssoft.hr.train.jsp.example.service.UserService;
import com.bosssoft.hr.train.jsp.example.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Slf4j
@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        User user = new User(userName, userPassword);

        if(authentication(user)) {

            //将用户姓名存入servletContext 方便后续展示
            ArrayList<User> loginUsers = (ArrayList<User>)request.getSession().getServletContext().getAttribute("loginUsers");
            //如果没有则创建
            if(loginUsers == null) {
                loginUsers = new ArrayList<>();
                loginUsers.add(user);
                request.getSession().getServletContext().setAttribute("loginUsers", loginUsers);
            } else {
                loginUsers.add(user);

            }

            for (User u : loginUsers) {
                log.info("当前在线用户:{}",u.toString());
            }

            response.getWriter().write("OK");

        } else {

            log.info("用户不存在或密码不正确：{}", userName + userPassword);

            response.getWriter().write("账号或密码不正确");
            response.sendRedirect("/bosssoft/login.jsp");
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public Boolean authentication(User user) {

        UserService userService = new UserServiceImpl();
        return userService.authentication(user);

    }


}
