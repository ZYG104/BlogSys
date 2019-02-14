package pers.zhuye.blogsys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.zhuye.blogsys.entities.UserInfoEntity;
import pers.zhuye.blogsys.service.ArticleService;
import pers.zhuye.blogsys.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    /**
     * 管理员登录方法
     * @param jsonUserInfo
     * @return
     */
    @RequestMapping(value = "/adminLogin",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String adminLogin(@RequestBody String jsonUserInfo){

        JSONObject jsonObject = JSONObject.parseObject(jsonUserInfo);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        boolean loginSuccess = false;

        if (userService.verifyAdmin(username)){
            if (userService.userLogin(username,password)){
                return JSON.toJSONString("success");
            } else {
                return JSON.toJSONString("falid");
            }
        } else {
            return JSON.toJSONString("falid");
        }

    }


    /**
     * 获取管理员信息方法
     * @param jsonUserInfo
     * @return
     */
    @RequestMapping(value = "/adminInfo",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String findAdminInfo(@RequestBody String jsonUserInfo){

        JSONObject jsonObject = JSONObject.parseObject(jsonUserInfo);

        String username = jsonObject.getString("username");

        System.out.println("admin-----------" + username);

        List<UserInfoEntity> list = userService.findUserInfoByUsername(username);

        return JSON.toJSONString(list);

    }

    /**
     * 通过Id删除文章
     * @param jsonUserInfo
     * @return
     */
    @RequestMapping(value = "/deleteArticleById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String deleteArticleById(@RequestBody String jsonUserInfo){

        JSONObject jsonObject = JSONObject.parseObject(jsonUserInfo);

        long id = jsonObject.getLong("id");

        boolean deleteSuccess = articleService.deleteArticleById(id);

        if (deleteSuccess){
            return JSON.toJSONString("success");
        } else {
            return JSON.toJSONString("faild");
        }

    }
}
