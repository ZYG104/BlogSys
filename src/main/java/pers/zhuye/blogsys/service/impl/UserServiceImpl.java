package pers.zhuye.blogsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zhuye.blogsys.entities.UserInfoEntity;
import pers.zhuye.blogsys.repository.UserInfoDao;
import pers.zhuye.blogsys.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 用户注册
     * @param username 用户名(验证数据库中用户名是否存在)
     * @param userInfoEntity 用户信息实体类
     */
    public boolean userRegister(String username, UserInfoEntity userInfoEntity) {
        long hadUsername = userInfoDao.findUserByUsername(username);

        System.out.println("hadUsername = "+ hadUsername);
        //如果用户不存在，新建用户
        if(hadUsername == 0){
            userInfoDao.addUser(userInfoEntity);
            return true;
        }else {
            System.out.println(username + "--该用户已注册");
            return false;
        }
    }

    /**
     * 更新用户信息
     * @param userInfoEntity 用户实体类
     * @return 更新信息成功返回true 否则返回false
     */
    public boolean userUpdate(UserInfoEntity userInfoEntity) {
        return userInfoDao.updateUser(userInfoEntity);
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 验证成功返回true else 返回 false
     */
    public boolean userLogin(String username, String password) {
        boolean isUser = userInfoDao.userLogin(username,password);
        if(isUser){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证用户名
     * @param username 用户名
     * @return 如果用户名已存在 返回 false 否则返回 true
     */
    public boolean verifyUsername(String username) {
        long hadUsername = userInfoDao.findUserByUsername(username);

        if (hadUsername == 1){
            return false;
        }

        return true;
    }

    /**
     * 通过用户名查找该用户所有信息并返回
     * @param username 用户名
     * @return List
     */
    public List<UserInfoEntity> findUserInfoByUsername(String username) {

        List list = userInfoDao.findUserInfoByUsername(username);

        return list;
    }

    /**
     * 验证用户是否为管理员
     * @param username 用户名
     * @return
     */
    public boolean verifyAdmin(String username) {

        long permission = userInfoDao.verifyAdmin(username);

        if (permission == 1){
            System.out.println("111111111");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查找所有普通用户
     * @return
     */
    public List<UserInfoEntity> findAllOrdinaryUser(){
        return userInfoDao.findAllOrdinaryUser();
    }

    /**
     * 显示所有普通用户
     * @param start
     * @param max
     * @return
     */
    @Override
    public List<UserInfoEntity> showAllOrdinaryUser(int start, int max) {
        return userInfoDao.showAllOrdinaryUser(start,max);
    }
}
