<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/12/23
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

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

    </style>

</head>
<body>
    <jsp:include page="top_user_menu.jsp"></jsp:include>
    <div id="manager_main">
        <div id="manager_left">
            <a href="">
                <li id="li_unPublish" class="manager_li">未发表的文章</li>
            </a>
            <a href="">
                <li id="li_publish" class="manager_li">已发表的文章</li>
            </a>
        </div>
        <div>

        </div>
    </div>
    <jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
