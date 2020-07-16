package com.bosssoft.hr.train.jsp.example.controller;

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

/**
 * @author 温俊欣
 * 添加用户
 */
@Slf4j
@WebServlet(name = "AddUserController", urlPatterns = "/add")
public class AddUserController extends HttpServlet {
    /**
     * 用户对象
     */
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        User user = new User(null, userName, userCode, userPassword);
        boolean result = saveUser(user);
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

    private boolean saveUser(User user) {
        log.info(user.toString());
        return userService.save(user);

    }

}
