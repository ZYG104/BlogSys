package pers.zhuye.blogsys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.zhuye.blogsys.entities.UserInfoEntity;
import pers.zhuye.blogsys.service.UserService;
import pers.zhuye.blogsys.utils.RandomSaltUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;


@Controller
@SessionAttributes("User")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    //测试工程
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }


    /**
     * 验证用户名控制器
     * @param username 用户名
     * @return
     */
    @RequestMapping(value = "/checkUsername",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody String checkUsername(@RequestBody String username){

        boolean is = userService.verifyUsername(username);

        System.out.println(username);

        Map<String,Object> map = new HashMap<String, Object>();

        if (is){
            map.put("result","success");
        } else {
            map.put("result","used");
        }

        System.out.println("-----------------------------------" + JSON.toJSONString(map));
        return JSON.toJSONString(map);
    }

//    @RequestMapping(value = "/registerUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
//    public @ResponseBody String registerUser(@ModelAttribute UserInfoEntity userInfoEntity){
//
//        userInfoEntity.setSalt(RandomSaltUtil.getRandomString(32));
//        userInfoEntity.setUserPermission((byte) 0);
//
//        String emailRegExp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
//
//        if (userInfoEntity.getUserEmail().matches(emailRegExp)){
//            return JSON.toJSONString("fa");
//        }
//
//        boolean isRegister = userService.userRegister(userInfoEntity.getUsername(),userInfoEntity);
//
//        if (isRegister){
//            return JSON.toJSONString("success");
//        } else {
//            return JSON.toJSONString("false");
//        }
//    }

    /**
     * 用户注册
     * @param jsonUserInfo
     * @param session
     * @return
     */
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody String registerUser(@RequestBody String jsonUserInfo, HttpSession session){

        JSONObject jsonObject = JSONObject.parseObject(jsonUserInfo);


        String username = jsonObject.getString("username");
        String nickname = jsonObject.getString("nickname");
        String password = jsonObject.getString("password");
        String userEmail = jsonObject.getString("userEmail");

        Date date = new Date();
        Timestamp publishTime = new Timestamp(date.getTime());

        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userInfoEntity.setUsername(username);
        userInfoEntity.setNickname(nickname);
        userInfoEntity.setPassword(password);
        userInfoEntity.setUserEmail(userEmail);
        userInfoEntity.setSalt(RandomSaltUtil.getRandomString(32));
        userInfoEntity.setUserPermission((byte) 0);
        userInfoEntity.setUserProfilePictureUrl("moren1.jpg");
        userInfoEntity.setRegisteredDate(publishTime);

        boolean isRegister = userService.userRegister(username,userInfoEntity);

        System.out.println(username + nickname + password + userEmail);

        if (isRegister) {
            session.setAttribute("username", username);
            session.setAttribute("user_profile_picture_url",userInfoEntity.getUserProfilePictureUrl());
            System.out.println("GET SESSION" + session.getId());
            System.out.println("GET ATTRIBUTE" + session.getAttribute("username"));

            return JSON.toJSONString("success");
        }else {
            return JSON.toJSONString("falid");
        }
    }

    /**
     * 用户登录方法
     * @param jsonUserInfo
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody String userLogin(@RequestBody String jsonUserInfo,
                                          HttpServletRequest request, HttpSession session){

        JSONObject jsonObject = JSONObject.parseObject(jsonUserInfo);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        System.out.println("login------"+username+password);

        boolean loginSuccess = userService.userLogin(username,password);

        System.out.println(loginSuccess);
        if (loginSuccess){
            List<UserInfoEntity> list = userService.findUserInfoByUsername(username);

            for (UserInfoEntity userInfoEntity:list){
                session.setAttribute("username",userInfoEntity.getUsername());
                session.setAttribute("nickname",userInfoEntity.getNickname());
                session.setAttribute("user_profile_picture_url",userInfoEntity.getUserProfilePictureUrl());
            }

            System.out.println(list);
            System.out.println("session_username = "+session.getAttribute("username"));
            System.out.println("GET SESSION" + session.getId());
//            request.getRequestDispatcher();

            return JSON.toJSONString("success");
        } else {
            return JSON.toJSONString("falid");
        }
    }

    /**
     * 用户登出
     */
    @RequestMapping(value = "/userLogout",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String logout(HttpSession session){
        session.invalidate();

        return JSON.toJSONString("success");
    }

    /**
     * 更新用户信息
     * @param jsonUserInfo
     * @return
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody String updateUser(@RequestBody String jsonUserInfo){

        JSONObject jsonObject = JSONObject.parseObject(jsonUserInfo);

        String username = jsonObject.getString("username");
        String nickname = jsonObject.getString("nickname");
        String userEmail = jsonObject.getString("userEmail");
        String userPhone = jsonObject.getString("userPhone");


        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userInfoEntity.setUsername(username);
        userInfoEntity.setNickname(nickname);
        userInfoEntity.setUserEmail(userEmail);
        userInfoEntity.setUserPhone(userPhone);

        boolean isUpdate = userService.userUpdate(userInfoEntity);

        if (isUpdate){
            return JSON.toJSONString("success");
        } else {
            return JSON.toJSONString("falid");
        }

    }

    /**
     * 显示所有普通用户
     * @param jsonPageInfo 查找当前页的json数据
     * @return 往前端返回查询到的json数据 list
     */
    @RequestMapping(value = "/showAllOrdinaryUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String showAllOrdinaryUser(@RequestBody String jsonPageInfo){

        JSONObject jsonObject = JSONObject.parseObject(jsonPageInfo);

        System.out.println(jsonPageInfo);

        int start = jsonObject.getInteger("start");
        int max = jsonObject.getInteger("max");

        System.out.println("start+max" + start+max);

        List<UserInfoEntity> list = userService.showAllOrdinaryUser(start,max);

        System.out.println(list);

        return JSON.toJSONString(list);
    }

    /**
     * 统计普通用户总数
     * @return
     */
    @RequestMapping(value = "/countOrdinaryUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String countPublishPage(){

        int num = userService.findAllOrdinaryUser().size();

        System.out.println("useruuuuuuuuuuuuuuuuu"+num);

        return JSON.toJSONString(num);
    }


}
