$(document).ready(function () {
    $("body").keydown(function (event) {
        if (event.keyCode==13){
            focusNextInput(this);
        }
    })
})


//�û�����֤����
function checkUsername() {

    var username = $("#username").val().replace(/\s+/g,"");

    //�����û���ֻ�������ַ������»���
    var usernameRegExp = /^[a-zA-Z0-9_]{1,}$/;

    if (username == ""){
        $("#username_error").text("�û�������Ϊ��");
        $("#username_error").css("color","red");
        return false;
    }else if (username.length > 50) {
        $("#username_error").text("�û�������");
        $("#username_error").css("color","red");
        return false;
    }else if(!username.match(usernameRegExp)){
        $("#username_error").text("�û����Ƿ�");
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
                    $("#username_error").text("�û�������");
                    $("#username_error").css("color","green");
                    return true;
                }else{
                    $("#username_error").text("���û���ע��");
                    $("#username_error").css("color","red");
                    return false;
                }
            },
            error:function (data) {
                alert("�û�����֤����,����ϵ����Ա");
                return false;
            }
        });
    }
}

//�ǳ���֤����
function checkNickname() {

    var nickname = $("#nickname").val().replace(/\s+/g,"");
    
    if (nickname == ""){
        $("#nickname_error").text("�ǳƲ���Ϊ��");
        $("#nickname_error").css("color","red");
        return false;
    }else if(nickname.length > 50){
        $("#nickname_error").text("�ǳƹ���");
        $("#nickname_error").css("color","red");
        return false;
    }else{
        $("#nickname_error").text("");
        return true;
    }
    
}

//������֤����
function checkPassword() {

    var password = $("#password").val().replace(/\s+/g,"");

    var passwordRegExp = "[\\\x4e00-\\\x9fa5]+";

    if (password == ""){
        $("#password_error").text("���벻��Ϊ��");
        $("#password_error").css("color","red");
        return false;
    }else if(!password.match(passwordRegExp)){
        $("#password_error").text("���벻�ܺ�����");
        $("#password_error").css("color","red");
        return false;
    }else{
        $("#password_error").text("");
        return true;
    }
    
}

//У�����뷽��
function checkPasswordValid() {

    var password = $("#password").val().replace(/\s+/g,"");
    var password_vaild = $("#password_valid").val().replace(/\s+/g,"");

    var passwordRegExp = "[\\\x4e00-\\\x9fa5]+";

    if (password_vaild == ""){
        $("#password_valid_error").text("��֤���벻��Ϊ��");
        $("#password_error").css("color","red");
        return false;
    }else if(!password.match(passwordRegExp)){
        $("#password_error").text("���벻�ܺ�����");
        $("#password_error").css("color","red");
        return false;
    }else if(password != password_vaild){
        $("#password_valid_error").text("������������벻ͬ");
        $("#password_error").css("color","red");
        return false;
    }else{
        $("#password_valid_error").text("");
        return true;
    }

}

//������֤����
function checkEmail() {

    var email = $("#email").val().replace(/\s+/g,"");

    var emailRegExp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    if (email == ""){
        $("#email_error").text("���䲻��Ϊ��");
        $("#email_error").css("color","red");
        return false;
    }else if (!email.match(emailRegExp)){
        $("#email_error").text("�����ʽ�Ƿ�");
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
                    alert("ע��ɹ�");
                    setCookie("username");
                    window.location.href = "main.jsp";
                    return true;
                }else{
                    return false;
                    alert("ע��ʧ��");
                }
            },
            error:function (data) {
                alert("ע��ʧ��,����ϵ����Ա");
                return false;
            }
        });
    }else{
        alert("�����������Ϣ");
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
        // ��������һ�����򽹵�ص���һ��
        if(i==(inputs.length - 1)){
            inputs[0].focus(); break;
        }else if(thisInput == inputs[i]){
            inputs[i+1].focus(); break;
        }
    }
}