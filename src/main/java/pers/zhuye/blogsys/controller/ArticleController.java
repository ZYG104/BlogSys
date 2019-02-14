package pers.zhuye.blogsys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.zhuye.blogsys.entities.ArticleInfoEntity;
import pers.zhuye.blogsys.service.ArticleService;
import pers.zhuye.blogsys.utils.RandomSaltUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;


@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章方法
     * @param jsonArticleInfo
     * @param session
     * @return
     */
    @RequestMapping(value = "/addArticle",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String addArticle(@RequestBody String jsonArticleInfo,
                      HttpServletRequest request, HttpSession session){

        JSONObject jsonObject = JSONObject.parseObject(jsonArticleInfo);

        System.out.println("GET SESSION" + session.getId());

        String articleTitle = jsonObject.getString("articleTitle");
        String articleClassification = jsonObject.getString("articleClassification");
        String articleContent = jsonObject.getString("articleContent");
        byte whetherPublish = Byte.parseByte(jsonObject.getString("whetherPublish"));
        String pictureUrl = jsonObject.getString("articleImgUrl");


        Date date = new Date();
        Timestamp publishTime = new Timestamp(date.getTime());
        String authorUsername = (String) session.getAttribute("username");

        System.out.println(authorUsername);

        ArticleInfoEntity articleInfoEntity = new ArticleInfoEntity();

        articleInfoEntity.setAuthorUsername(authorUsername);
        articleInfoEntity.setArticleTitle(articleTitle);
        articleInfoEntity.setArticleClassification(articleClassification);
        articleInfoEntity.setArticleContent(articleContent);
        articleInfoEntity.setPictureUrl(pictureUrl);
        articleInfoEntity.setWhetherPublish(whetherPublish);
        articleInfoEntity.setDateOfPublication(publishTime);

        boolean isSave = articleService.addArticle(articleInfoEntity);

        System.out.println(isSave);
        if (isSave) {
            return JSON.toJSONString("success");
        } else {
            return JSON.toJSONString("faild");
        }

    }

    /**
     * 显示所有发布的文章
     * @param jsonPageInfo 查找当前页的json数据
     * @return 往前端返回查询到的json数据 list
     */
    @RequestMapping(value = "/showAllPublishArticle",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String showAllPublishArticle(@RequestBody String jsonPageInfo){

        JSONObject jsonObject = JSONObject.parseObject(jsonPageInfo);

        System.out.println(jsonPageInfo);

        int start = jsonObject.getInteger("start");
        int max = jsonObject.getInteger("max");

        System.out.println("start+max" + start+max);

        List<ArticleInfoEntity> list = articleService.showAllPublishArticle(start,max);


        System.out.println(list);

        return JSON.toJSONString(list);
    }


    /**
     * 统计发布的文章总数
     * @return
     */
    @RequestMapping(value = "/countPublishPage",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String countPublishPage(){

        Long num = articleService.countPublishPages();

        return JSON.toJSONString(num);
    }

    /**
     * 通过文章ID查找文章信息
     * @param articleID 携带ID信息的json数据
     * @return 往前端返回查询到的json数据 list
     */
    @RequestMapping(value = "/findArticleInfoByID",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String findArticleInfoByID(@RequestBody String articleID){

        JSONObject jsonObject = JSONObject.parseObject(articleID);

        System.out.println(articleID);

        long id = jsonObject.getInteger("id");

        System.out.println("id-----------" + id);

        List<ArticleInfoEntity> list = articleService.findArticleInfoByID(id);

        System.out.println(list);

        return JSON.toJSONString(list);
    }

    /**
     * 通过文章作者用户名查找文章信息
     * @return 往前端返回查询到的json数据 list
     */
    @RequestMapping(value = "/findArticleInfoByAuthorUsername",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String findArticleInfoByAuthorUsername(@RequestBody String authorInfo){

        JSONObject jsonObject = JSONObject.parseObject(authorInfo);

        System.out.println(authorInfo);

        String authorUsername = jsonObject.getString("authorUsername");

        byte whetherPublish = jsonObject.getByte("whetherPublish");

        int start = jsonObject.getInteger("start");
        int max = jsonObject.getInteger("max");

        List<ArticleInfoEntity> list =
                articleService.findArticleInfoByAuthorName(authorUsername,whetherPublish,start,max);


        System.out.println(list);

        return JSON.toJSONString(list);
    }



}
