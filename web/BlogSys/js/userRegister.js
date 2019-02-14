$(document).ready(function () {
    $("body").keydown(function (event) {
        if (event.keyCode==13){
            focusNextInput(this);
        }
    })
})


//用户名验证方法
function checkUsername() {

    var username = $("#username").val().replace(/\s+/g,"");

    //限制用户名只能输入字符数字下划线
    var usernameRegExp = /^[a-zA-Z0-9_]{1,}$/;

    if (username == ""){
        $("#username_error").text("用户名不能为空");
        $("#username_error").css("color","red");
        return false;
    }else if (username.length > 50) {
        $("#username_error").text("用户名过长");
        $("#username_error").css("color","red");
        return false;
    }else if(!username.match(usernameRegExp)){
        $("#username_error").text("用户名非法");
        $("#username_error").css("color","red");
        return false;
    }else{
        $("#username_error").text("");
        $.ajax({
            type:"POST",
            url:"user/checkUsername.do",
            data:username,
            contentType: "application/json; charset=utf-8",
            dataType:'json',
            success:function (data) {
                if (data.result == "success"){
                    $("#username_error").text("用户名可用");
                    $("#username_error").css("color","green");
                    return true;
                }else{
                    $("#username_error").text("该用户已注册");
                    $("#username_error").css("color","red");
                    return false;
                }
            },
            error:function (data) {
                alert("用户名验证错误,请联系管理员");
                return false;
            }
        });
    }
}

//昵称验证方法
function checkNickname() {

    var nickname = $("#nickname").val().replace(/\s+/g,"");
    
    if (nickname == ""){
        $("#nickname_error").text("昵称不能为空");
        $("#nickname_error").css("color","red");
        return false;
    }else if(nickname.length > 50){
        $("#nickname_error").text("昵称过长");
        $("#nickname_error").css("color","red");
        return false;
    }else{
        $("#nickname_error").text("");
        return true;
    }
    
}

//密码验证方法
function checkPassword() {

    var password = $("#password").val().replace(/\s+/g,"");

    var passwordRegExp = "[\\\x4e00-\\\x9fa5]+";

    if (password == ""){
        $("#password_error").text("密码不能为空");
        $("#password_error").css("color","red");
        return false;
    }else if(!password.match(passwordRegExp)){
        $("#password_error").text("密码不能含中文");
        $("#password_error").css("color","red");
        return false;
    }else{
        $("#password_error").text("");
        return true;
    }
    
}

//校验密码方法
function checkPasswordValid() {

    var password = $("#password").val().replace(/\s+/g,"");
    var password_vaild = $("#password_valid").val().replace(/\s+/g,"");

    var passwordRegExp = "[\\\x4e00-\\\x9fa5]+";

    if (password_vaild == ""){
        $("#password_valid_error").text("验证密码不能为空");
        $("#password_error").css("color","red");
        return false;
    }else if(!password.match(passwordRegExp)){
        $("#password_error").text("密码不能含中文");
        $("#password_error").css("color","red");
        return false;
    }else if(password != password_vaild){
        $("#password_valid_error").text("两次输入的密码不同");
        $("#password_error").css("color","red");
        return false;
    }else{
        $("#password_valid_error").text("");
        return true;
    }

}

//邮箱验证方法
function checkEmail() {

    var email = $("#email").val().replace(/\s+/g,"");

    var emailRegExp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    if (email == ""){
        $("#email_error").text("邮箱不能为空");
        $("#email_error").css("color","red");
        return false;
    }else if (!email.match(emailRegExp)){
        $("#email_error").text("邮箱格式非法");
        $("#email_error").css("color","red");
        return false;
    }else{
        $("#email_error").text("");
        return true;
    }
    
}

function registerUser() {

    var allVaild = false;

    var username = $("#username").val().replace(/\s+/g,"");
    var nickname = $("#nickname").val().replace(/\s+/g,"");
    var password = $("#password").val().replace(/\s+/g,"");
    var email = $("#email").val().replace(/\s+/g,"");

    if (checkUsername()){
        allVaild = true;
    }else{
        allVaild = false;
    }

    if (checkNickname()){
        allVaild = true;
    } else {
        allVaild = false;
    }

    if (checkPassword()){
        allVaild = true;
    } else {
        allVaild = false;
    }

    if (checkPasswordValid()){
        allVaild = true;
    } else {
        allVaild = false;
    }

    if (checkEmail()){
        allVaild = true;
    } else {
        allVaild = false;
    }

    var user = {
    }

    user.username = username;
    user.nickname = nickname;
    user.password = password;
    user.userEmail = email;

    if (allVaild){
        $.ajax({
            type:"POST",
            url:"user/registerUser.do",
            data:JSON.stringify(user),
            contentType:"application/json; charset=utf-8",
            dataType:'json',
            success:function (data) {
                if (data == "success"){
                    alert("注册成功");
                    setCookie("username");
                    window.location.href = "main.jsp";
                    return true;
                }else{
                    return false;
                    alert("注册失败");
                }
            },
            error:function (data) {
                alert("注册失败,请联系管理员");
                return false;
            }
        });
    }else{
        alert("请检查输入的信息");
    }
}

function setCookie(key,value) {
    var str = key + "=" + escape(value);
    document.cookie = str;
}

function focusNextInput(thisInput)
{
    var inputs = document.getElementsByTagName("input");
    for(var i = 0;i<inputs.length;i++){
        // 如果是最后一个，则焦点回到第一个
        if(i==(inputs.length - 1)){
            inputs[0].focus(); break;
        }else if(thisInput == inputs[i]){
            inputs[i+1].focus(); break;
        }
    }
}