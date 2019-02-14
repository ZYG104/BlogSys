<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/12
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    #top{
        width: 100%;
        min-width: 1000px;
        height: 50px;
        background-color: gray;
    }
    #logo{
        width: 200px;
        height: 100%;
        margin: 0 0 0 1rem;
        border-right: 5px solid white;
        font-size: 2rem;
        font-family: Arial;
        color: white;
        line-height: 50px;
        float: left;
    }
    #menu{
        width: 300px;
        height: 50px;
        float: right;
    }

    .top_li{
        list-style: none;
        width: 100px;
        height: 50px;
        float: left;
        font-family: 楷体;
        text-align: center;
        font-size: 1.5rem;
        color: whitesmoke;
        line-height: 50px;

    }
    .top_a{
        text-decoration: none;
        color: white;
    }

    #register{
        background: deepskyblue;
    }
</style>


<script src="BlogSys/js/jquery-3.3.1.js"></script>
<script>
    $(document).ready(function () {
        $(".top_li").mouseenter(function(){
            $(this).css("background-color","black");
        });
        $(".top_li").mouseleave(function(){
            $(this).css("background-color","gray");
        });
        $("#register").mouseleave(function () {
            $(this).css("background-color","deepskyblue");
        })
    })
</script>

<div id="top">
    <div id="logo">BlogSys</div>
    <div id="menu">
        <li class="top_li"><a class="top_a" href="index.jsp">主页</a></li>
        <li class="top_li"><a class="top_a" href="user_login.jsp">登录</a></li>
        <li class="top_li" id="register"><a class="top_a" href="userRegister.jsp">注册</a></li>
    </div>
</div>
