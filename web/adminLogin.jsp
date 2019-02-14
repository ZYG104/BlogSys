<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/12/3
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <style>
        *{
            margin: 0;
        }
        body{
            width: 60%;
            margin: 0 auto;
        }
        #index_middle{
            width: 300px;
            height: 100%;
            margin: 150px auto;
            text-align: center;
            font-family: 楷体;
        }
        #user_login_p{
            font-size: 32px;
            margin-bottom: 50px;
        }
        .user_login_items{
            width: 300px;
            height: 50px;
            margin-top: 5px;
            border: 0;
            border-bottom: 2px solid black;
            font-size: 24px;
            font-family: 楷体;
        }
        #user_login_button{
            width: 300px;
            height: 50px;
            margin-top: 30px;
            font-size: 24px;
            font-family: 楷体;
        }
    </style>
</head>


<script src="/BlogSys/js/jquery-3.3.1.js"></script>
<script>
    $(document).ready(function () {
        $("body").keydown(function (event) {
            if (event.keyCode==13){
                login();
            }
        })
    })

    function login() {

        var username = $("#user_login_username").val();
        var password = $("#user_login_password").val();
        var userLoginInfo = {};

        userLoginInfo.username = username;
        userLoginInfo.password = password;

        $.ajax({
            type:"POST",
            url:"admin/adminLogin.do",
            data:JSON.stringify(userLoginInfo),
            contentType:"application/json; charset=utf-8",
            dataType:'json',
            success:function (data) {
                if (data == "falid"){
                    alert("用户名或密码错误");
                    return false;
                }else{
                    alert("登录成功");
                    setCookie("username",username);
                    window.location.href = "manager.jsp";
                    return true;
                }
            },
            error:function (data) {
                alert("登录失败");
                return false;
            }
        })
    }

    function setCookie(key,value) {
        var str = key + "=" + escape(value);
        document.cookie = str;
    }
</script>


<body>
    <div id="index_top">
        <jsp:include page="top.jsp"></jsp:include>
    </div>
    <div id="index_middle">
        <p id="user_login_p">用户名登录</p>
        <input class="user_login_items" id="user_login_username" placeholder="请输入用户名">
        <input class="user_login_items" id="user_login_password" placeholder="请输入密码">
        <button id="user_login_button" onclick="login()">登录</button>
    </div>
    <div id="index_bottom">
        <jsp:include page="bottom.jsp"></jsp:include>
    </div>
</body>
</html>
