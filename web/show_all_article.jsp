<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/16
  Time: 14:01
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
    #index_article{
        width: 100%;
        min-width: 1000px;
        height: 100%;
    }
    #showA{
        width: 850px;
        height: 100%;
        margin: 0 auto;
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
</style>
<script>



    //查询记录总数
    $.ajax({
        type:"POST",
        url:"article/countPublishPage.do",
        contentType: "application/json; charset=utf-8",
        dataType:'json',
        success:function (data) {
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
                totalCount: '合计'+ num +'条数据', // 条目总数
                slideSpeed: 600, // 缓动速度。单位毫秒
                callback: function(page) { // 回调函数
                    var pageNow = page;

                    var start;
                    var max = 6;

                    if (pageNow > 1) {
                        start = (pageNow-1) * 6;
                    } else {
                        start = 0;
                    }

                    var pageInfo = {}

                    pageInfo.start = start;
                    pageInfo.max = max;


                    //显示当页数据
                    $.ajax({
                        type:"POST",
                        url:"article/showAllPublishArticle.do",
                        data:JSON.stringify(pageInfo),
                        contentType:"application/json; charset=utf-8",
                        dataType:'json',
                        success:function (data) {

                            $("#showA").empty();

                            for (var i = 0;i < data.length;i++) {
                                var show;

                                var articleId = data[i].id;
                                var pictureUrl = data[i].pictureUrl;
                                var articleTitle = data[i].articleTitle;
                                var authorUsername = data[i].authorUsername;
                                var articleContent = data[i].articleContent;

                                show = "<div class='article_items'>"+
                                    "<img src='/res/"+ pictureUrl +"'>" +
                                    "<h3 class='article_items_h3'><a href='article_info.jsp?id=" + articleId + "'>" + articleTitle +"</a></h3>" +
                                    "<p class='article_items_p'>" + articleContent + "</p>" +
                                    "</div>"

                                $("#showA").append(show);
                            }

                        },
                        error:function (data) {
                            alert("没数据");
                            return false;
                        }
                    });
                    console.log(page);
                }
            });


        },
        error:function (data) {
            return false;
        }
    });


</script>
<div id="index_article">
    <div id="showA">

    </div>
    <div id="box" class="box">

    </div>
</div>

