package pers.zhuye.blogsys.repository;

import pers.zhuye.blogsys.entities.ArticleInfoEntity;

import java.util.List;

public interface ArticleInfoDao {

    /**
     * 添加文章
     * @param articleInfoEntity 文章实体类
     */
    void addArticle(ArticleInfoEntity articleInfoEntity);

    /**
     * 显示所有文章
     * @param whetherPublish 是否发表
     * @return
     */
    List<ArticleInfoEntity> showAllArticle(byte whetherPublish,int start,int max);

    /**
     * 查找文章总数(发表或未发表)
     * @param whetherPublish 是否发表
     * @return
     */
    long countPageNum(byte whetherPublish);

    /**
     * 通过 ID 查找文章信息
     * @param id
     * @return
     */
    List<ArticleInfoEntity> findArticleInfoByID(long id);

    /**
     * 通过ID 删除文章信息
     * @param id
     * @return
     */
    boolean deleteArticleById(long id);

    /**
     * 通过作者用户名查找文章
     * @return
     */
    List<ArticleInfoEntity> findArticleInfoByAuthorName(String username,byte whetherPublish,int start,int max);
}
