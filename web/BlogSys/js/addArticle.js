
$(document).ready(function () {
    $("body").keydown(function (event) {
        if (event.keyCode==13){
        }
    });
    $("#upload_article").click(function () {
        ajaxFileUploads();
    });

});

//ͼƬ����
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
        alert("���±��ⲻ��Ϊ��");
        articleTitle = false;
    } else {
        articleTitle = true;
    }

    if (articleClassification == "") {
        alert("���·��಻��Ϊ��");
        articleValid = false;
    } else {
        articleValid = true;
    }

    if (articleContent == "") {
        alert("�������ݲ���Ϊ��");
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
                        alert("�ɹ�");
                        window.location.href = "main.jsp";
                        return true;
                    } else {
                        $("#username_error").text("ʧ��");
                        return false;
                    }
                },
                error: function (data) {
                    alert("����ʧ��,����ϵ����Ա");
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
                alert("�����ϴ���ͼƬ�Ƿ���ȷ��");
                flag = 1;
            }

        });
    }
}

function getFileName(value) {
    var fileName = value.lastIndexOf("\\");
    return value.substring(value + 1);
}