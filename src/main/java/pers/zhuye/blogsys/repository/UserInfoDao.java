package pers.zhuye.blogsys.repository;


import pers.zhuye.blogsys.entities.UserInfoEntity;

import java.util.List;

public interface UserInfoDao {

    /**
     * 添加用户接口
     * @param userInfoEntity 用户信息实体类
     */
    void addUser(UserInfoEntity userInfoEntity);

    /**
     * 更新用户信息接口
     * @param userInfoEntity 用户信息实体类
     */
    boolean updateUser(UserInfoEntity userInfoEntity);

    /**
     * 用户登录接口
     * @param username 用户名
     * @param password 密码
     * @return 验证成功返回true else 返回 false
     */
    boolean userLogin(String username, String password);

    /**
     * 通过用户名查找用户(可用于验证用户是否存在)
     * @param username 用户名
     * @return 返回用户名
     */
    long findUserByUsername(String username);


    /**
     * 通过用户名查找该用户所有信息并返回
     * @param username 用户名
     * @return 用户实体类
     */
    List<UserInfoEntity> findUserInfoByUsername(String username);

    /**
     * 管理员身份验证
     * @param username
     * @return
     */
    long verifyAdmin(String username);

    /**
     * 查找所有普通用户
     * @return 用户List
     */
    List<UserInfoEntity> findAllOrdinaryUser();

    /**
     * 显示所有普通用户
     * @param start
     * @param max
     * @return
     */
    List<UserInfoEntity> showAllOrdinaryUser(int start,int max);

}
