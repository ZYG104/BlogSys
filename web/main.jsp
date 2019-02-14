<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/12
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户界面</title>
    <style>
        @media screen and (min-width: 1250px){
            *{
                margin: 0;
            }
            #main{
                width: 100%;
                height: 100%;
                min-height: 800px;
                margin: 50px auto;
            }
            #showArticle{
                width: 80%;
                height: 100%;
                float: left;
            }
            #showUserFrag{
                width: 250px;
                height: 500px;
                float: left;
            }
        }
        @media screen and (max-width: 1249px){
            *{
                margin: 0;
            }
            #main{
                width: 100%;
                height: 100%;
                min-height: 800px;
                margin: 50px auto;
            }
            #showArticle{
                width: 80%;
                height: 100%;
                margin: 0 auto;
            }
            #showUserFrag{
                display: none;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="top_user_menu.jsp"></jsp:include>
    <div id="main">
        <div id="showArticle">
            <jsp:include page="show_all_article.jsp"></jsp:include>
        </div>
        <div id="showUserFrag">
            <jsp:include page="show_user_info.jsp"></jsp:include>
        </div>
    </div>
    <jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
