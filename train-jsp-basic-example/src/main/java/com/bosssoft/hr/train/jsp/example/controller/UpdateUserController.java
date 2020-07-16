package com.bosssoft.hr.train.jsp.example.controller;

import com.bosssoft.hr.train.jsp.example.pojo.User;
import com.bosssoft.hr.train.jsp.example.service.UserService;
import com.bosssoft.hr.train.jsp.example.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 温俊欣
 * 修改用户 通过用户名查找用户 只能修改用户名与密码
 */
@WebServlet(name = "UpdateUserController", urlPatterns = "/update")
public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        User user = new User(Integer.parseInt(userId), userName, userCode, userPassword);

        boolean result = update(user);

        try {
            String contextPath = request.getContextPath();
            //通过meta标签来模拟页面跳转
            String message = "<meta http-equiv='refresh' content='3;url=" + contextPath +"/index.jsp'>恭喜你操作成功，页面将在3秒内跳转，如不跳转请点击<a href=''>超链接</a>";
            this.getServletContext().setAttribute("message", message);
            //页面跳转到jsp页面
            this.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (IOException e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private boolean update(User user) {
        UserService userService = new UserServiceImpl();
        return userService.update(user);
    }
}
