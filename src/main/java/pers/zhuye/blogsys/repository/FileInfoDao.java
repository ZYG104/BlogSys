package pers.zhuye.blogsys.repository;

import pers.zhuye.blogsys.entities.FileInfoEntity;

import javax.swing.text.StyledEditorKit;

public interface FileInfoDao {

    /**
     * 通过文件MD5值查找文件名
     * @return
     */
    String getFileNameByMd5(String md5);

    /**
     * 添加文件信息数据
     * @param fileInfoEntity
     * @return
     */
    boolean addFileInfo(FileInfoEntity fileInfoEntity);
}
