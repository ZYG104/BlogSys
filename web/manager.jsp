<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/29
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<style>
    /*页面css*/
    #manager_main{
        width: 100%;
        height: 100%;
    }
    #manager_left{
        width: 20%;
        height: 100%;
        float: left;
        margin-right: 50px;
        border-right: 2px black solid;
    }
    .manager_li{
        list-style: none;
        width: 100%;
        height: 50px;
        text-align: center;
        line-height: 50px;
        font-size: 24px;
    }
    #my_info{
        float: left;
        display: block;
    }
    .manager_p{
        height: 50px;
        font-size: 24px;

    }
    .manager_info{
        text-decoration: none;
        color: black;
    }
    .manager_article{
        text-decoration: none;
        color: black;
    }
    .manager_user{
        text-decoration: none;
        color: black;
    }
</style>


<div id="manager_main">
    <div id="manager_left">
        <a class="manager_info" href="manager.jsp">
            <li id="li_myInfo" class="manager_li">我的信息</li>
        </a>
        <a class="manager_user" href="manager_userList.jsp">
            <li id="li_UserManager" class="manager_li">用户管理</li>
        </a>
        <a class="manager_article" href="manager_article.jsp">
            <li id="li_ArticleManager" class="manager_li" >文章管理</li>
        </a>
    </div>
    <div id="my_info">
        <p class="manager_p">我的信息</p>
        <jsp:include page="user_info.jsp"></jsp:include>
    </div>
</div>



