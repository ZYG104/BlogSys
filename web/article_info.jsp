<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/22
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="BlogSys/js/jquery-3.3.1.js"></script>
    <script>
        var id = getUrlParam('id');

        var jsonId = {};

        jsonId.id = id;

        //显示当页数据
        $.ajax({
            type:"POST",
            url:"article/findArticleInfoByID.do",
            data:JSON.stringify(jsonId),
            contentType: "application/json; charset=utf-8",
            dataType:'json',
            success:function (data) {

                    var show;

                    var articleId = data[0].id;
                    var pictureUrl = data[0].pictureUrl;
                    var articleTitle = data[0].articleTitle;
                    var authorUsername = data[0].authorUsername;
                    var articleContent = data[0].articleContent;

                    show = "<h1>" + articleTitle +"</h1>" +
                        "<p class='article_items_p'>" + articleContent + "</p>" ;


                    $(".article_info_content").append(show);

            },
            error:function (data) {
                alert("没数据");
                return false;
            }
        });

        //获取Url所带的参数
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
    </script>
</head>
<body>
    <jsp:include page="top.jsp"></jsp:include>
    <div class="article_info_content">

    </div>
    <jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
