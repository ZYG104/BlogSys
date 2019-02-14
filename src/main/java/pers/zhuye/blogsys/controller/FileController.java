package pers.zhuye.blogsys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.zhuye.blogsys.entities.FileInfoEntity;
import pers.zhuye.blogsys.service.FileService;
import pers.zhuye.blogsys.utils.MD5Utils;
import pers.zhuye.blogsys.utils.RandomSaltUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件信息添加
     * @param file
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/addFileInfo")
    public @ResponseBody
    String addFileInfo(@RequestParam(value = "file", required = false) MultipartFile file,
                      HttpServletRequest request, HttpSession session)
                        throws IllegalStateException,IOException {

        String fileMd5 = MD5Utils.getMD5(file);

        String fileName = file.getOriginalFilename();

        //处理后的图片名称
        String fileFinallyName = "";

        //查找文件的MD5值，如果有，将数据库中的文件名字赋值给文件名
        //如果数据库中无此文件的MD5值，先将文件转存到指定目录下，再往数据库中添加该文件信息
        if (fileService.getFileNameByMd5(fileMd5) != null){
            fileFinallyName =  fileService.getFileNameByMd5(fileMd5);
        } else {
            //转存文件
            //文件上传路径
            String basePath = "D:\\TomcatRes\\";

            //获取文件名前缀加上5位随机数
            String fileNameNew = fileName.substring(0,fileName.indexOf("."))
                    + RandomSaltUtil.getRandomImgUrl(5);
            System.out.println(fileNameNew);

            //截取文件后缀
            String suffix = fileName.substring(fileName.indexOf("."));
            System.out.println(suffix);

            //处理后的图片名称
            fileFinallyName = fileNameNew + suffix;

            //最终路径为指定磁盘路径+文件名
            String path = basePath + fileFinallyName;

            //前端接收的文件转存至指定位置
            file.transferTo(new File(path));

            //往数据库中添加文件信息
            FileInfoEntity fileInfoEntity = new FileInfoEntity();

            fileInfoEntity.setFileName(fileFinallyName);
            fileInfoEntity.setFileMd5(fileMd5);

            fileService.addFileInfo(fileInfoEntity);

        }

            return JSON.toJSONString(fileFinallyName);

    }
}
