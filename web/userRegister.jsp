<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/12
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户注册</title>
    <style>
        *{
            margin: 0;
        }
        body{
            width: 60%;
            margin: 0 auto;
        }
        #center{
            width: 100%;
            height: 700px;
            margin: 50px auto;
        }
        #bottom{
            float: left;
        }
    </style>
</head>
<body>
    <div id="top">
        <jsp:include page="top.jsp" flush="true"></jsp:include>
    </div>
    <div id="center">
        <jsp:include page="register_form.jsp" flush="true"></jsp:include>
    </div>
    <div id="bottom">
        <jsp:include page="bottom.jsp" flush="true"></jsp:include>
    </div>
</body>
</html>
