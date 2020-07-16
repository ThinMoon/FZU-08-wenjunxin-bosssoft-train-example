<%--
  Created by IntelliJ IDEA.
  User: 瘦明月
  Date: 2020/7/14
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="boss" uri="/WEB-INF/userTag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>-----------------当前在线人数 ${onlineUserCount}-------------------------</h1>
<h1>-----------------当前在线人员-------------------------</h1>

    <boss:userTag/>

    <h1>-----------------增加用户-------------------------</h1>
    <form name="addUser" action="${pageContext.request.contextPath}/add" method="post">
        username : <input type="text" name="userName"><br>
        usercode : <input type="text" name="userCode"><br>
        password : <input type="text" name="userPassword"><br>
        <input type="submit" value="提交">
    </form>
    <h1>------------------------------------------------</h1>

    <h1>-----------------删除用户-------------------------</h1>
    <form name="removeUser" action="${pageContext.request.contextPath}/remove" method="post">
        请输入需要删除的用户id :
        <input type="text" name="userId"><br>
        <input type="submit" value="提交">
    </form>
    <h1>------------------------------------------------</h1>

    <h1>-----------------修改用户-------------------------</h1>
    <form name="addUser" action="${pageContext.request.contextPath}/update" method="post">

        请输入需要修改的用户id : <input type="text" name="userId"><br>

        请输入新的的usercode : <input type="text" name="userCode"><br>
        请输入新的username : <input type="text" name="userName"><br>
        请输入新的password : <input type="text" name="userPassword"><br>
        <input type="submit" value="提交">
    </form>
    <h1>------------------------------------------------</h1>

    <h1>---------------查询所有用户-----------------------</h1>

    <a href="${pageContext.request.contextPath}/queryAll">点击查询所有用户</a><br>

    <c:forEach  var="user" items="${allUsers}" >
        ${user.name}<br>
    </c:forEach>





</body>
</html>
