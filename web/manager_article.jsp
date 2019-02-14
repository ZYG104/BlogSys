<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/12/6
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="BlogSys/css/paging.css">
<script src="BlogSys/js/jquery-3.3.1.js"></script>
<script src="BlogSys/js/paging.js"></script>
<style>
    *{
        margin: 0;
        padding: 0;
        border: 0;
    }
    #manager_article{
        width: 100%;
        height: 100%;
        min-width: 1200px;
    }
    #showA{
        width: 850px;
        min-width: 1200px;
        margin: 0 auto;
        float: left;
    }
    .article_items{
        width: 100%;
        height: 200px;
        margin-bottom: 20px;
    }
    img{
        width: 200px;
        height: 200px;
        margin-right: 50px;
        float: left;
    }
    .article_items_h3{
        width: 600px;
        height: 50px;
        float: left;
        line-height: 50px;
    }
    .article_items_p{
        position: relative;
        width: 600px;
        height: 100px;
        line-height: 33px;
        float: left;
        display: -webkit-box;
        -webkit-line-clamp: 3;
        overflow: hidden;
        -webkit-box-orient: vertical;
        text-indent: 30px;
    }
    .article_items a{
        color: black;
        text-decoration: none;
    }
    #deleteArticle{
        width: 100px;
        height: 30px;
    }
    #box{
        height: 50px;
        float: left;
    }

</style>
<script>
    //value指定返回的cookie对象
    function getCookie(value){
        var cookieArray = document.cookie.split(";");
        var cookie = new Object();
        for (var i=0;i < cookieArray.length;i++){
            var arr = cookieArray[i].split("=");
            if (arr[0]==value)
                return unescape(arr[1]);
        }
        return "";
    }

    var username = getCookie("username");


    if (username != "") {
        //查询记录总数
        $.ajax({
            type: "POST",
            url: "article/countPublishPage.do",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (data) {
                var num = JSON.parse(data);
                var initPageNo = 1;
                var totalPages;

                if (num < 6){
                    totalPages = 1;
                }else if (num % 6 == 0){
                    totalPages = parseInt((num) / 6);
                }else{
                    totalPages = parseInt((num) / 6) + 1;
                }


                //设置分页按钮
                $('#box').paging({
                    initPageNo: initPageNo, // 初始页码
                    totalPages: totalPages, //总页数
                    totalCount: '合计' + num + '条数据', // 条目总数
                    slideSpeed: 600, // 缓动速度。单位毫秒
                    callback: function (page) { // 回调函数
                        var pageNow = page;

                        var start;
                        var max = 6;

                        if (pageNow > 1) {
                            start = (pageNow - 1) * 6;
                        } else {
                            start = 0;
                        }

                        var pageInfo = {}

                        pageInfo.start = start;
                        pageInfo.max = max;


                        //显示当页数据
                        $.ajax({
                            type: "POST",
                            url: "article/showAllPublishArticle.do",
                            data: JSON.stringify(pageInfo),
                            contentType: "application/json; charset=utf-8",
                            dataType: 'json',
                            success: function (data) {

                                $("#showA").empty();

                                for (var i = 0; i < data.length; i++) {
                                    var show;

                                    var articleId = data[i].id;
                                    var pictureUrl = data[i].pictureUrl;
                                    var articleTitle = data[i].articleTitle;
                                    var authorUsername = data[i].authorUsername;
                                    var articleContent = data[i].articleContent;

                                    show = "<div class='article_items'>" +
                                        "<img src='/res/" + pictureUrl + "'>" +
                                        "<h3 class='article_items_h3'><a href=" + articleId + "'article_info.jsp?id='>" + articleTitle + "</a></h3>" +
                                        "<p class='article_items_p'>" + articleContent + "</p>" +
                                        "<button id='deleteArticle' onclick='reallyDelete(" + articleId + ")'>删除文章" +
                                        "</button>" +
                                        "</div>";


                                    $("#showA").append(show);
                                }

                            },
                            error: function (data) {
                                alert("没数据");
                                return false;
                            }
                        });
                        console.log(page);
                    }
                });


            },
            error: function (data) {
                return false;
            }
        });
    }

    function reallyDelete(articleId){
        var is = confirm("确定删除?");

        var id = articleId;

        var articleInfo = {};

        articleInfo.id = id;

        if (is){
            $.ajax({
                type:"POST",
                url:"admin/deleteArticleById.do",
                data:JSON.stringify(articleInfo),
                contentType:"application/json; charset=utf-8",
                dataType:'json',
                success:function (data) {
                    if (data == "success"){
                        alert("删除成功");
                        window.location.href = "manager_article.jsp";
                        return true;
                    }else{
                        alert("删除失败");
                        return false;
                    }
                },
                error:function (data) {
                    alert("删除失败，联系管理员");
                    return false;
                }
            })
        }
    }
</script>


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
    #article_manager{
        width: 60%;
        display: block;
        float: left;
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
    <div id="article_manager">
        <p class="manager_p">文章管理</p>
        <div id="manager_article">
            <div id="showA">

            </div>
            <div id="box" class="box">

            </div>
        </div>
    </div>
</div>



