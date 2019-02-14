package pers.zhuye.blogsys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.zhuye.blogsys.entities.FileInfoEntity;
import pers.zhuye.blogsys.repository.FileInfoDao;
import pers.zhuye.blogsys.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoDao fileInfoDao;

    /**
     * 通过文件的MD5值查找文件的名称
     * @return
     */
    @Override
    public String getFileNameByMd5(String md5) {
        return fileInfoDao.getFileNameByMd5(md5);
    }

    /**
     * 添加文件信息数据
     * @param fileInfoEntity
     * @return
     */
    @Override
    public boolean addFileInfo(FileInfoEntity fileInfoEntity) {
        return fileInfoDao.addFileInfo(fileInfoEntity);
    }
}
