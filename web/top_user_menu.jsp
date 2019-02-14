<%@ page import="pers.zhuye.blogsys.entities.UserInfoEntity" %><%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/12
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+
            "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <style>
        #top{
            width: 100%;
            height: 50px;
            background-color: gray;
        }
        #logo{
            width: 200px;
            height: 100%;
            margin: 0 0 0 20px;
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

        .top_user_menu_li{
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
        .top_user_menu_a{
            text-decoration: none;
            color: white;
        }
        #logout{
            float: right;
            margin-right: 50px;
        }

    </style>
<body>
    <div id="top">
        <div id="logo">BlogSys</div>
        <div id="menu">
            <li class="top_user_menu_li"><a class="top_user_menu_a" href="main.jsp">主页</a></li>
            <li class="top_user_menu_li" id="logout">
                <a class="top_user_menu_a" onclick="logout()">退出登录</a>
            </li>
        </div>
    </div>
</body>
<script>
    function logout(){
        $.ajax({
            type:"POST",
            url:"user/userLogout.do",
            contentType:"application/json; charset=utf-8",
            success:function (data) {
                if (data == "success"){
                    alert("安全登出成功");
                    window.location.href = "index.jsp";
                    return true;
                }else{
                    return false;
                    alert("登出时遇到一点问题");
                }
            },
            error:function (data) {
                alert("登出失败失败,请联系管理员");
                return false;
            }
        });
    }
</script>