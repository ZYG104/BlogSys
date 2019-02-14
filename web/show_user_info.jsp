<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/12
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <style>
        #showUserInfo{
            width: 250px;
            height: 500px;
            background: gray;
        }
        #userProfilePicture{
            width: 120px;
            height: 120px;
            margin: 60px 0 0 60px;
            border-radius: 50%;
        }
        #showName{
            width: 250px;
            margin-top: 25px;
            font-size: 26px;
            float: left;
            text-align: center;
        }
        #icon_info{
            width: 200px;
            height: 50px;
            float: left;
            margin-top: 50px;
            padding: 0 25px 0 25px;
        }
        .icons{
            width: 40px;
            height: 40px;
            margin: 5px;
        }
        #manager{
            width: 240px;
            height: 80px;
            float: left;
            margin-top: 20px;
            padding: 0 0 0 10px;
        }
        .show_user_info_li{
            list-style: none;
            width: 120px;
            height: 30px;
            margin: 5px 0 5px 0;
            float: left;
        }
        .manager_icons{
            width: 25px;
            height: 25px;
            margin: 5px 0 0 0;
        }
        .show_user_info_a{
            text-decoration: none;
            color: black;
            margin: 0 0 0 5px;
        }
    </style>





<div id="showUserInfo">
    <a href="">
        <img src="BlogSys/img/user_profile_picture/<%= session.getAttribute("user_profile_picture_url")%>" id="userProfilePicture"></a>
    <p id="showName">
        <%= session.getAttribute("username")%>
    </p>
    <div id="icon_info">
        <a href=""><img class="icons" src="BlogSys/img/sys_img/youxiang.png"></a>
        <a href=""><img class="icons" src="BlogSys/img/sys_img/github4.png"></a>
        <a href=""><img class="icons" src="BlogSys/img/sys_img/qq.png"></a>
        <a href=""><img class="icons" src="BlogSys/img/sys_img/gengduomore12.png"></a>
    </div>
    <div id="manager">
        <li class="show_user_info_li"><img class="manager_icons" src="BlogSys/img/sys_img/combinedshapecopy2.png">
            <a class="show_user_info_a" href="addArticle.jsp">撰写文章</a></li>
        <li class="show_user_info_li"><img class="manager_icons" src="BlogSys/img/sys_img/shoucang.png">
            <a class="show_user_info_a" href="">我的收藏</a></li>
        <li class="show_user_info_li"><img class="manager_icons" src="BlogSys/img/sys_img/guanli.png">
            <a class="show_user_info_a" href="">文章管理</a></li>
        <li class="show_user_info_li"><img class="manager_icons" src="BlogSys/img/sys_img/guanli.png">
            <a class="show_user_info_a" href="">评论管理</a></li>
    </div>
</div>
