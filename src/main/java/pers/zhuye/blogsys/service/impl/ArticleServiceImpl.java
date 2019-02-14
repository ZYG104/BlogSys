package pers.zhuye.blogsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zhuye.blogsys.entities.ArticleInfoEntity;
import pers.zhuye.blogsys.repository.ArticleInfoDao;
import pers.zhuye.blogsys.service.ArticleService;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleInfoDao articleInfoDao;

    /**
     * 添加文章
     * @param articleInfoEntity
     */
    @Override
    public boolean addArticle(ArticleInfoEntity articleInfoEntity) {
        try {
            articleInfoDao.addArticle(articleInfoEntity);
            System.out.println("------------");
            return true;
        } catch (Exception e){
            System.out.println(e);
            System.out.println("++++++++++++++");
            return false;
        }
    }

    /**
     * 查找所有已发表的文章
     * @return
     */
    @Override
    public List<ArticleInfoEntity> showAllPublishArticle(int start,int max) {
        List<ArticleInfoEntity> list = articleInfoDao.showAllArticle((byte) 1,start,max);
        return list;
    }

    /**
     * 查找所有未发表的文章
     * @return
     */
    @Override
    public List<ArticleInfoEntity> showAllUnPublishArticle(int start,int max) {
        List<ArticleInfoEntity> list = articleInfoDao.showAllArticle((byte) 0,start,max);
        return list;
    }

    /**
     * 通过文章ID查找文章信息
     * @param id
     * @return
     */
    @Override
    public List<ArticleInfoEntity> findArticleInfoByID(long id) {

        List<ArticleInfoEntity> list = articleInfoDao.findArticleInfoByID(id);

        return list;
    }

    /**
     * 统计已发表的文章总数
     * @return
     */
    @Override
    public long countPublishPages() {

        long num = articleInfoDao.countPageNum((byte) 1);

        return num;
    }

    /**
     * 统计未发表的文章总数
     * @return
     */
    @Override
    public long countUnPublishPages() {

        long num = articleInfoDao.countPageNum((byte) 0);

        return num;
    }

    /**
     * 通过Id删除文章
     * @param id 文章id
     * @return
     */
    @Override
    public boolean deleteArticleById(long id) {

        return articleInfoDao.deleteArticleById(id);
    }

    @Override
    public List<ArticleInfoEntity> findArticleInfoByAuthorName(String username, byte whetherPublish, int start, int max) {
        return articleInfoDao.findArticleInfoByAuthorName(username,whetherPublish,start,max);
    }


}
