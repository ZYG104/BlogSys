package pers.zhuye.blogsys.service;

import pers.zhuye.blogsys.entities.FileInfoEntity;

public interface FileService {

    /**
     * 通过MD5值查找文件名字
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
