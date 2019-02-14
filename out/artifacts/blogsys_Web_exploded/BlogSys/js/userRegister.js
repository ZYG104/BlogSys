function register(){

    var username = $(':input[name = "username"]').val().replace(/[ ]/g,"");
    var nickname = $(':input[name = "nickname"]').val().replace(/[ ]/g,"");
    var password = $(':input[name = "password"]').val().replace(/[ ]/g,"");
    var email = $(':input[name = "email"]').val().replace(/[ ]/g,"");

    var verifyUsername;

    if (username == ""){
        $("#username_error").innerText = "用户名不能为空";
    }else if (verifyUsername == false){
        $("#username_error").innerText = "用户名已存在";
    }

    if (nickname == ""){
        return false;
    }

    if (password == ""){
        return false;
    }

    if (email == ""){
        return false;
    }

    $.ajax({
        url:'http://localhost:8080/checkUsername',
        async:false,
        data:{
            "username" : username
        },
        type:"post",
        success : function (data) {
            if (data.success()){
                verifyUsername = true;
            }else {
                verifyUsername = false;
            }
        }

    });
}