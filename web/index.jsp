<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/6
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Blog</title>
<style>
    body{
        width: 100%;
        /*margin: 0 auto;*/
    }
    #index_top{
        min-width: 1000px;
    }
    #index_middle{
        min-width: 1000px;
        min-height: 1000px;
        margin-top: 50px;
    }
    #index_bottom{
        margin-top: 50px;
        min-width: 1000px;
    }

    @media screen and (min-width: 1200px) {

        body{
            width: 80%;
            margin: 0 auto;
        }
        #index_top{
        }
        #index_middle{
            margin: 50px;
        }
        #index_bottom{
        }
    }

    @media screen and (min-width: 1500px){
        body{
            width: 60%;
            margin: 0 auto;
        }
        #index_top{
        }
        #index_middle{
            margin: 50px;
        }
        #index_bottom{
        }
    }

</style>
</head>
<body>
    <div id="index_top">
        <jsp:include page="top.jsp"></jsp:include>
    </div>
    <div id="index_middle">
        <jsp:include page="show_all_article.jsp"></jsp:include>
    </div>
    <div id="index_bottom">
        <jsp:include page="bottom.jsp"></jsp:include>
    </div>
</body>
</html>
