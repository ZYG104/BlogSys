package pers.zhuye.blogsys.service;

import pers.zhuye.blogsys.entities.ArticleInfoEntity;

import java.util.List;

public interface ArticleService {

    /**
     * 添加文章
     * @param articleInfoEntity
     */
    boolean addArticle(ArticleInfoEntity articleInfoEntity);


    /**
     * 查找所有已发表文章
     * @return
     */
    List<ArticleInfoEntity> showAllPublishArticle(int start,int max);

    /**
     * 查找所有未发表文章
     * @return
     */
    List<ArticleInfoEntity> showAllUnPublishArticle(int start,int max);

    /**
     * 通过文章ID查找文章信息
     * @param id
     * @return
     */
    List<ArticleInfoEntity> findArticleInfoByID(long id);

    /**
     * 统计已发表的文章总数
     * @return
     */
    long countPublishPages();

    /**
     * 统计未发表的文章总数
     * @return
     */
    long countUnPublishPages();

    /**
     * 通过id删除文章
     * @param id 文章id
     * @return
     */
    boolean deleteArticleById(long id);

    /**
     * 通过作者用户名查找文章
     * @return
     */
    List<ArticleInfoEntity> findArticleInfoByAuthorName(String username,byte whetherPublish,int start,int max);

}
