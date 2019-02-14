<%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/12/3
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    #showAdmin{
        width: 850px;
        height: 100%;
        margin: 0 auto;
    }
</style>

<script src="BlogSys/js/jquery-3.3.1.js"></script>

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

    var adminInfo={};

    adminInfo.username = username;

    //用户信息
    $.ajax({
        type:"POST",
        url:"admin/adminInfo.do",
        data:JSON.stringify(adminInfo),
        contentType: "application/json; charset=utf-8",
        dataType:'json',
        success:function (data) {

            $("#showA").empty();

            for (var i = 0;i < data.length;i++) {
                var show;

                var username = data[i].username;
                var nickname = data[i].nickname;
                var pictureUrl = data[i].userProfilePictureUrl;
                var userEmail = data[i].userEmail;
                var userPhone = data[i].userPhone;
                var userPermission = data[i].userPermission;
                
                var permissionInfo = "";
                switch (userPermission){
                    case 0:
                        permissionInfo = "普通用户";
                        break;
                    case 1:
                        permissionInfo = "1级管理员";
                        break;
                }


                show = "<img src='BlogSys/img/user_profile_picture/"+ pictureUrl +"'/>"+
                        "<p class='user_info_p'>用户名 : </p>" +
                        "<input id='user_info_username' type='text' disabled='disabled' value='" + username + "'>" +
                        "<p class='user_info_p'>权限 : </p>" +
                        "<input type='text' disabled='disabled' value='" + permissionInfo + "'>" +
                        "<p class='user_info_p'>昵称 : </p>" +
                        "<input type='text' id='user_info_nickname' class='user_info_input' value='" + nickname + "'>" +
                        "<p class='user_info_p'>邮箱 : </p>" +
                        "<input type='text' id='user_info_userEmail' class='user_info_input' value='" + userEmail + "'>" +
                        "<p class='user_info_p'>手机号码 : </p>" +
                        "<input type='text' id='user_info_userPhone' class='user_info_input' value='" + userPhone + "'>";


                $("#showAdmin").append(show);
            }

        },
        error:function (data) {
            alert("没数据");
            return false;
        }
    });


</script>

<div id="showAdmin">
</div>

<input id="upadteUserInfo" type="button" value="更新信息" onclick="updateUser()">

<script>
function updateUser() {

    var username = $("#user_info_username").val().replace(/\s+/g,"");
    var nickname = $("#user_info_nickname").val().replace(/\s+/g,"");
    var userEmail = $("#user_info_userEmail").val().replace(/\s+/g,"");
    var userPhone = $("#user_info_userPhone").val().replace(/\s+/g,"");

    var userInfo={};

    userInfo.username = username;
    userInfo.nickname = nickname;
    userInfo.userEmail = userEmail;
    userInfo.userPhone = userPhone;

    if (checkEmail(userEmail)){
        $.ajax({
            type:"POST",
            url:"user/updateUser.do",
            data:JSON.stringify(userInfo),
            contentType:"application/json; charset=utf-8",
            dataType:'json',
            success:function (data) {
                if (data == "success"){
                    alert("更新成功");
                    return true;
                }else{
                    return false;
                    alert("更新失败");
                }
            },
            error:function (data) {
                alert("更新失败,请联系管理员");
                return false;
            }
        });
    }

}

//邮箱验证方法
function checkEmail(value) {

    var email = value;

    var emailRegExp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    if (email == ""){
        alert("邮箱不能为空");
        return false;
    }else if (!email.match(emailRegExp)){
        alert("邮箱格式非法");
        return false;
    }else{
        return true;
    }

}

</script>