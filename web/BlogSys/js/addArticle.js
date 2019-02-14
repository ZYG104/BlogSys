
$(document).ready(function () {
    $("body").keydown(function (event) {
        if (event.keyCode==13){
        }
    });
    $("#upload_article").click(function () {
        ajaxFileUploads();
    });

});

//图片名字
var imgFileName = "";
var flag = 1;


function addArticle(whetherPublish) {

    var articleTitle = $("#write_article_title").val();
    var articleClassification = $("#write_article_classification").val();
    var articleContent = $("#write_article_textarea").val();
    var articleImgUrl = imgFileName;
    var article = {}

    alert(articleImgUrl);

    if (imgFileName == "") {
        articleImgUrl = "article_moren1.jpg";
    }


    article.articleTitle = articleTitle;
    article.articleClassification = articleClassification;
    article.articleContent = articleContent;
    article.whetherPublish = whetherPublish;
    article.articleImgUrl = articleImgUrl;

    alert(whetherPublish);

    var articleValid = false;

    if (articleTitle == "") {
        alert("文章标题不能为空");
        articleTitle = false;
    } else {
        articleTitle = true;
    }

    if (articleClassification == "") {
        alert("文章分类不能为空");
        articleValid = false;
    } else {
        articleValid = true;
    }

    if (articleContent == "") {
        alert("文章内容不能为空");
        articleValid = false;
    } else {
        articleValid = true;
    }



    if (articleValid = true){
        if (flag) {
            $.ajax({
                type: "POST",
                url: "article/addArticle.do",
                data: JSON.stringify(article),
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                success: function (data) {
                    if (data == "success") {
                        alert("成功");
                        window.location.href = "main.jsp";
                        return true;
                    } else {
                        $("#username_error").text("失败");
                        return false;
                    }
                },
                error: function (data) {
                    alert("保存失败,请联系管理员");
                    return false;
                }
            });
        }
    }


}


function ajaxFileUploads() {
    var formData = new FormData();

    formData.append('file',$("#article_file")[0].files[0]);
    // formData.append('img',$("#article_img").attr("src"));

    if (flag) {
        $.ajax({
            type: "post",
            url: "file/addFileInfo",
            async: false,
            contentType: false,
            processData: false,
            data: formData,
            dataType: 'json',
            beforeSend: function () {
                flag = 0
            },
            success: function (data) {
                $("#article_img").attr("src", "/res/" + data);
                flag = 1;
                imgFileName = data;
            },
            error: function (data) {
                alert("请检查上传的图片是否正确！");
                flag = 1;
            }

        });
    }
}

function getFileName(value) {
    var fileName = value.lastIndexOf("\\");
    return value.substring(value + 1);
}