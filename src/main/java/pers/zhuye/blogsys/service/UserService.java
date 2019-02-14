package pers.zhuye.blogsys.service;

import pers.zhuye.blogsys.entities.UserInfoEntity;

import java.util.List;

public interface UserService {

    /**
     * 用户注册接口
     * @param username 用户名(验证数据库中用户名是否存在)
     * @param userInfoEntity 用户信息实体类
     */
    boolean userRegister(String username, UserInfoEntity userInfoEntity);

    /**
     * 用户信息更新
     * @param userInfoEntity 用户实体类
     */
    boolean userUpdate(UserInfoEntity userInfoEntity);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     */
    boolean userLogin(String username, String password);

    /**
     * 验证用户名
     * @param username 用户名
     * @return
     */
    boolean verifyUsername(String username);

    /**
     * 通过用户名查找该用户所有信息并返回
     * @param username 用户名
     * @return List
     */
    List<UserInfoEntity> findUserInfoByUsername(String username);

    /**
     * 验证用户是否为管理员
     * @param username 用户名
     * @return
     */
    boolean verifyAdmin(String username);

    /**
     * 查找所有普通用户
     * @return
     */
    List<UserInfoEntity> findAllOrdinaryUser();

    /**
     * 显示所有普通用户
     * @return
     */
    List<UserInfoEntity> showAllOrdinaryUser(int start,int max);
}
