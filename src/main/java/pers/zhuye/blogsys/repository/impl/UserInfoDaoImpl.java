package pers.zhuye.blogsys.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.zhuye.blogsys.entities.UserInfoEntity;
import pers.zhuye.blogsys.repository.UserInfoDao;

import java.util.List;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    //获取与当前线程绑定的 Session.
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加新用户
     * @param userInfoEntity 用户信息实体类
     */
    public void addUser(UserInfoEntity userInfoEntity) {
        getSession().save(userInfoEntity);
    }

    /**
     * 更新用户信息
     * @param userInfoEntity 用户信息实体类
     */
    public boolean updateUser(UserInfoEntity userInfoEntity) {
        try {
            String hql = "UPDATE UserInfoEntity u SET u.nickname = :nickname,u.userEmail = :userEmail,u.userPhone = :userPhone WHERE u.username = :username";

            Query query = getSession().createQuery(hql);
            query.setParameter("nickname",userInfoEntity.getNickname());
            query.setParameter("userEmail",userInfoEntity.getUserEmail());
            query.setParameter("userPhone",userInfoEntity.getUserPhone());
            query.setParameter("username",userInfoEntity.getUsername());

            query.executeUpdate();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 用户登录验证
     * @param username 用户名
     * @param password 密码
     * @return 验证成功返回true else 返回 false
     */
    public boolean userLogin(String username, String password) {
        String hql = "SELECT u.password FROM UserInfoEntity u WHERE u.username = :username";
        String truePassword = (String) getSession().createQuery(hql).setParameter("username",username).uniqueResult();

        //输入密码与数据库中密码对比
        if (truePassword.equals(password.replace(" ",""))){
            return true;
        } else {
            return false;
        }

    }

    /**
     * 查找用户名
     * @param username 用户名
     * @return
     */
    public long findUserByUsername(String username) {
        String hql = "SELECT count(userInfo.username) FROM UserInfoEntity userInfo WHERE userInfo.username = :username";

        Long count = (Long) getSession().createQuery(hql).setParameter("username",username).uniqueResult();

        System.out.println("count --------"+count);

        return count;
    }

    /**
     * 通过用户名查找该用户所有信息
     * @param username 用户名
     * @return List
     */
    @Override
    public List<UserInfoEntity> findUserInfoByUsername(String username) {
        String hql = "FROM UserInfoEntity userInfo WHERE userInfo.username = :username";

        List<UserInfoEntity> list =
                getSession().createQuery(hql).setParameter("username",username).list();

        for (UserInfoEntity userInfoEntity : list){
            System.out.println(userInfoEntity);
            System.out.println("HHHHHHH---"+userInfoEntity.getNickname());
        }

        System.out.println("dao----------"+list);
        return list;
    }

    /**
     * 管理员身份验证
     * @param username
     * @return
     */
    @Override
    public long verifyAdmin(String username) {
        String hql = "SELECT u.userPermission FROM UserInfoEntity u WHERE u.username = :username";

        Byte permission = (Byte) getSession().createQuery(hql).setParameter("username",username).uniqueResult();

        return permission;
    }

    /**
     * 查找所有普通用户
     * @return
     */
    @Override
    public List<UserInfoEntity> findAllOrdinaryUser() {

        String hql = "FROM UserInfoEntity userInfo WHERE userInfo.userPermission = :userPermission";

        byte userPermission = 0;

        List<UserInfoEntity> list =
                getSession().createQuery(hql).
                        setParameter("userPermission", userPermission).list();

        return list;

    }

    @Override
    public List<UserInfoEntity> showAllOrdinaryUser(int start, int max) {

        String hql = "FROM UserInfoEntity userInfo WHERE userInfo.userPermission = :userPermission";

        byte userPermission = 0;

        List<UserInfoEntity> list =
                getSession().createQuery(hql).
                        setParameter("userPermission", userPermission)
                        .setFirstResult(start).setMaxResults(max).list();

        return list;
    }


}
