<%@ page import="pers.zhuye.blogsys.entities.UserInfoEntity" %><%--
  Created by IntelliJ IDEA.
  User: YE
  Date: 2018/11/12
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #write_article_title{
        width: 80%;
        height: 50px;
        margin: 0 auto;
        font-size: 24px;
    }
    #write_article_p{
        font-size: 32px;
        text-align: center;
    }
    #write_article_classification{
        width: 80%;
        height: 50px;
        font-size: 24px;
    }
    #write_article_textarea{
        width: 100%;
        height: 80%;
        resize: none;
        font-size: 20px;
    }
    .write_article_p{
        font-size: 24px;
    }
    .write_article_button{
        width: 100px;
        height: 50px;
        margin: 10px 10px 0 0;
        font-size: 16px;
    }
</style>

<script src="BlogSys/js/jquery-3.3.1.js"></script>
<script src="BlogSys/tinymce/js/tinymce/jquery.tinymce.min.js"></script>
<script src="BlogSys/js/addArticle.js"></script>
<div id="write_article">
    <p id="write_article_p">编写文章</p>
    <div>
        <form id="article_img_uploads" enctype="multipart/form-data">
            <img id="article_img" style="width: 200px;height: 200px" src="">
            <p>选择封面</p>
            <input type="file" id="article_file" />
            <input type="button" value="上传" id="upload_article"/>
        </form>
    </div>
    <p class="write_article_p">文章标题 : </p><input id="write_article_title" type="text">
    <p class="write_article_p">选择分类 : </p><input id="write_article_classification" type="text">
    <p class="write_article_p">编写正文 : </p><textarea id="write_article_textarea"></textarea>
    <button class="write_article_button" id="save" onclick="addArticle(0)">保存</button>
    <button class="write_article_button" id="publish" onclick="addArticle(1)">发表文章</button>
</div>
