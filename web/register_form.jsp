<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/1
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #user_login{
        width: 300px;
        height: 100%;
        margin: 50px auto;
    }

    .items{
        width: 500px;
        height: 60px;
        float: left;
    }

    #register_form_p{
        width: 100%;
        text-align: center;
        font-size: 30px;
        color: #000;
    }

    .register_form_input{
        width: 300px;
        height: 50px;
        margin: 0 0 5px 5px;
        border: 0;
        border-bottom: 2px solid black;
        float: left;
        font-size: 24px;
        font-family: 楷体;
    }

    #register_form_button{
        width: 300px;
        height: 50px;
        margin: 5px 0 0 5px;
        font-size: 24px;
        font-family: 楷体;
    }

    .register_form_span{
        width: 190px;
        height: 50px;
        margin-left: 5px;
        float: left;
        color: red;
        line-height: 50px;
        font-size: 20px;
        font-family: 楷体;
    }
</style>

<script src="BlogSys/js/jquery-3.3.1.js"></script>
<script src="BlogSys/js/userRegister.js"></script>

<p id="register_form_p">用户注册</p>

<%--<form name="user_login" action="" method="post">--%>
    <div id="user_login">
        <div class="items">
            <input class = "register_form_input" id="username" name="username" type="text"
                   placeholder="请输入用户名" onblur="checkUsername()"/>
            <span class="register_form_span" id="username_error"></span>
        </div>
        <div class="items">
            <input class = "register_form_input" id="nickname" name="nickname" type="text"
                   placeholder="请输入昵称" onblur="checkNickname()"/>
            <span class="register_form_span" id="nickname_error"></span>
        </div>
        <div class="items">
            <input class = "register_form_input" id="password" name="password" type="password"
                   placeholder="请输入密码" onblur="checkPassword()"/>
            <span class="register_form_span" id="password_error"></span>
        </div>
        <div class="items">
            <input class = "register_form_input" id="password_valid" name="password_valid" type="password"
                   placeholder="请验证你的密码" onblur="checkPasswordValid()"/>
            <span class="register_form_span" id="password_valid_error"></span>
        </div>
        <div class="items">
            <input class = "register_form_input" id="email" name="userEmail" type="text"
                   placeholder="请输入邮箱" onblur="checkEmail()"/>
            <span class="register_form_span" id="email_error"></span>
        </div>
        <div id="button_login" class="items">
            <button id="register_form_button" name="login" type="submit" onclick="registerUser()">注册</button>
        </div>
    </div>
<%--</form>--%>
