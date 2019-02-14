<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/1
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <style>

        #user_login{
            width: 300px;
        }

        .items{
            width: 300px;
            float: left;
        }
        
        input{
            float: left;
        }

        button{
            margin: 5px 0 0 0;
            width: 180px;
            height: 30px;
        }
    </style>
    <script src=”http://ajax.aspnetcdn.com/ajax/jquery/jquery-2.1.1.min.js”></script>
    <script src="BlogSys/js/userRegister.js"></script>
</head>
<body>
    <form name="user_login" action="/checkUsername" method="post">
        <div id="user_login">
            <div id="username" class="items">
                <p>用户名 : </p><input name="username" type="text"><span id="username_error"></span>
            </div>
            <div id="nickname" class="items">
                <p>昵称 : </p><input name="nickname" type="text"><span id="nickname_error"></span>
            </div>
            <div id="password" class="items">
                <p>密码 : </p><input name="password" type="text"><span id="password_error"></span>
            </div>
            <div id="email" class="items">
                <p>邮箱 : </p><input name="email" type="text"><span id="email_error"></span>
            </div>
            <div id="button_login" class="items">
                <button name="login" type="submit">注册</button>
            </div>
        </div>
    </form>
</body>
</html>
