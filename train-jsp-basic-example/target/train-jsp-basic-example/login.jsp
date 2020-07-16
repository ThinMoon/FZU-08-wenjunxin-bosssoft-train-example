<%--
  Created by IntelliJ IDEA.
  User: 瘦明月
  Date: 2020/7/15
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script typet="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>


<h1>登录</h1>
<div>用户名:<input type="text" id="uid" ></input></div>
<div>密码:&nbsp;<input type="text" id="pwd" ></input></div>
<div><input type="button" value="登录" id="btn"></input></div>



</body><!--用JQuery写ajax jquery是不能直接处理数据库的-->

<script type="text/javascript">
    $(document).ready(function(e){

        $("#btn").click(function(){

            var uid = $("#uid").val();
            var pwd = $("#pwd").val();
            $.ajax({
//必须要写的四个参数,顺序不限
                url:"${pageContext.request.contextPath}/login",
                //处理页面的路径
                data:{userName:uid,userPassword:pwd},
                //传递的数据.提交数一般以json格式来写,key是自定义的,:后面的值 就是上面的值
                type:"POST",
                //数据的提交传递方式,GET,POST 最好用POST
                datatype:"TEXT",
                //返回值的类型,TEXT,JSON,XML三种类型可选
                success:function(data){
                    //如果ajax执行成功,返回来调用success函数即回调函数,返回值以参数的形式返回

                    if(data=="OK")
                    {
                        window.location="index.jsp";
                    }
                    else
                    {
                        alert(data);
                    }

                },

            });



        })
    });

</script>

</html>
