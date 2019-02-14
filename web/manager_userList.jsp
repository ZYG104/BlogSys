<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/12/11
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script src="BlogSys/js/jquery-3.3.1.js"></script>
<script src="BlogSys/js/paging.js"></script>

<style>
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }
    .fl {
        float: left;
    }
    .box2 {
        width: 850px;
        height: 40px;
        line-height: 40px;
        margin: 0 auto;
        text-align: center;
        float: left;
    }
    .box2 button {
        padding: 0 10px;
        margin: 0 10px;
        height: 40px;
        float: left;
        cursor: pointer;
        border: 1px solid #ebebeb;
        background-color: #ffffff;
    }
    .box2 .first-page,
    .box2 .last-page {
        margin: 0;
    }
    .box2 .pageWrap {
        height: 40px;
        float: left;
        overflow: hidden;
    }
    .box2 .pageWrap ul {
        width: 100000px;
        height: 40px;
        float: left;
    }
    .box2 .pageWrap ul li {
        width: 60px;
        height: 40px;
        border: 1px solid #ebebeb;
        line-height: 40px;
        box2-sizing: border-box2;
        cursor: pointer;
        float: left;
    }
    .box2 .pageWrap ul .sel-page {
        background-color: gray;
    }
    .box2 .jump-text {
        width: 60px;
        height: 40px;
        box2-sizing: border-box2;
        text-align: center;
        margin: 0 5px;
        float: left;
    }
    .box2 .jump-button {
        margin: 0;
        float: left;
    }
    .box2 .total-pages,
    .box2 .total-count {
        width: 100px;
        height: 40px;
        margin-left: 10px;
        text-align: center;
        float: left;
        font-size: 14px;
    }

    #index_user{
        width: 1000px;
    }
    .user_items{
        width: 1000px;
        min-width: 600px;
        height: 50px;
        margin-top: 20px;
        float: left;
    }
    .user_items_p{
        width: 100px;
        height: 50px;
        float: left;
    }
    .user_items_p_long{
        margin-right: 10px;
        width: 250px;
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
            url: "user/countOrdinaryUser.do",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (data) {
                var num = JSON.parse(data);
                var initPageNo = 1;
                var totalPages;

                if (num < 6) {
                    totalPages = 1;
                } else {
                    totalPages = parseInt((num) / 6) + 1;
                }


                //设置分页按钮
                $('#box2').paging({
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
                            url: "user/showAllOrdinaryUser.do",
                            data: JSON.stringify(pageInfo),
                            contentType: "application/json; charset=utf-8",
                            dataType: 'json',
                            success: function (data) {

                                $("#showB").empty();

                                for (var i = 0; i < data.length; i++) {
                                    var show;

                                    var id = data[i].id;
                                    var username = data[i].username;
                                    var nickname = data[i].nickname;
                                    var userEmail = data[i].userEmail;
                                    var userPhone = data[i].userPhone;

                                    show = "<div class='user_items'>" +
                                        "<p class='user_items_p'>" + id + "</p>" +
                                        "<p class='user_items_p'>" + username + "</p>" +
                                        "<p class='user_items_p'>" + nickname + "</p>" +
                                        "<p class='user_items_p_long'>" + userEmail + "</p>" +
                                        "<p class='user_items_p_long'>" + userPhone + "</p>" +
                                        "</div>";


                                    $("#showB").append(show);
                                }

                            },
                            error: function (data) {
                                alert("没数据");
                                return false;
                            }
                        });
                        console.log(page2);
                    }
                });


            },
            error: function (data) {
                return false;
            }
        });
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
    #user_manager{
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
        <div id="user_manager">
            <p class="manager_p">用户管理</p>
            <div id="index_user">
                <div class="user_itmes">
                    <p class='user_items_p'>用户编号</p>
                    <p class='user_items_p'>用户名</p>
                    <p class='user_items_p'>昵称</p>
                    <p class='user_items_p_long'>邮箱</p>
                    <p class='user_items_p_long'>手机号</p>
                </div>
                <div id="showB">

                </div>
                <div id="box2" class="box2">

                </div>
            </div>
        </div>
</div>
