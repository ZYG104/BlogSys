package pers.zhuye.blogsys.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.zhuye.blogsys.entities.ArticleInfoEntity;
import pers.zhuye.blogsys.repository.ArticleInfoDao;

import java.util.List;

@Repository
public class ArticleInfoDaoImpl implements ArticleInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    //获取与当前线程绑定的线程
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加文章
     * @param articleInfoEntity 文章实体类
     */
    @Override
    public void addArticle(ArticleInfoEntity articleInfoEntity) {
        getSession().save(articleInfoEntity);
    }

    /**
     * 查看文章已发表或未发表的所有文章
     * @param whetherPublish 是否发表
     * @return
     */
    @Override
    public List<ArticleInfoEntity> showAllArticle(byte whetherPublish,int start,int max) {
        String hql = "FROM ArticleInfoEntity a WHERE a.whetherPublish = :whetherPublish ORDER BY a.dateOfPublication DESC";

        Query query = getSession().createQuery(hql);

        query.setParameter("whetherPublish",whetherPublish);
        //设置分页查询的初始位置
        query.setFirstResult(start);
        //设置分页查询的最大查询数
        query.setMaxResults(max);

        List<ArticleInfoEntity> list = query.list();

        return list;
    }

    /**
     * 查找文章总数(发表或未发表)
     * @param whetherPublish 是否发表
     * @return
     */
    public long countPageNum(byte whetherPublish){
        String hql = "SELECT COUNT(a.articleTitle) FROM ArticleInfoEntity a WHERE a.whetherPublish = :whetherPublish";

        Long count = (Long) getSession().createQuery(hql).setParameter("whetherPublish",whetherPublish).uniqueResult();

        System.out.println("count --------" + count);

        return count;
    }

    /**
     * 通过文章ID查找文章信息
     * @param id
     * @return
     */
    @Override
    public List<ArticleInfoEntity> findArticleInfoByID(long id) {

        String hql = "FROM ArticleInfoEntity a WHERE a.id = :id";

        List<ArticleInfoEntity> list
                = getSession().createQuery(hql).setParameter("id",id).list();

        return list;
    }

    /**
     * 通过Id删除文章
     * @param id 文章Id
     * @return 成功返回true ，有异常返回false
     */
    @Override
    public boolean deleteArticleById(long id) {

        try {

            String hql = "DELETE ArticleInfoEntity a WHERE a.id = :id";

            Query query = getSession().createQuery(hql);

            query.setParameter("id", id);

            query.executeUpdate();

            return true;
        } catch (Exception e){
            return false;
        }

    }

    /**
     * 通过作者用户名查找文章信息
     * @param whetherPublish
     * @return
     */
    @Override
    public List<ArticleInfoEntity> findArticleInfoByAuthorName(String username, byte whetherPublish, int start, int max) {

        String hql = "FROM ArticleInfoEntity a WHERE a.whetherPublish = :whetherPublish AND a.authorUsername = :authorUsername ORDER BY a.dateOfPublication DESC";

        Query query = getSession().createQuery(hql);

        query.setParameter("whetherPublish",whetherPublish);

        query.setParameter("authorUsername",username);
        //设置分页查询的初始位置
        query.setFirstResult(start);
        //设置分页查询的最大查询数
        query.setMaxResults(max);

        List<ArticleInfoEntity> list = query.list();

        return list;
    }


}
