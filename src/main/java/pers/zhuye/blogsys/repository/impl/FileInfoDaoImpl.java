package pers.zhuye.blogsys.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.zhuye.blogsys.entities.FileInfoEntity;
import pers.zhuye.blogsys.repository.FileInfoDao;

@Repository
public class FileInfoDaoImpl implements FileInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    //获取与当前线程绑定的线程
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 根据文件的Md5值查找文件的名称
     * @param md5
     * @return
     */
    @Override
    public String getFileNameByMd5(String md5) {
        String hql = "SELECT f.fileName FROM FileInfoEntity f WHERE f.fileMd5 = :md5";

        String fileName = (String) getSession().createQuery(hql).setParameter("md5",md5).uniqueResult();

        System.out.println("fileName----------------fff" + fileName);

        return fileName;

    }

    /**
     * 添加文件信息
     * @param fileInfoEntity
     * @return
     */
    @Override
    public boolean addFileInfo(FileInfoEntity fileInfoEntity) {

        try {
            getSession().save(fileInfoEntity);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
