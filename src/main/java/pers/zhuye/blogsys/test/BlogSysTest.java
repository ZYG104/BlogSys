package pers.zhuye.blogsys.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.CharacterEncodingFilter;
import pers.zhuye.blogsys.controller.UserController;

import java.net.URL;

public class BlogSysTest {

    @Autowired
    private UserController userController;

    @Test
    public void get() {
        URL url = CharacterEncodingFilter.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println("path:" + url.getPath() + "  name:" + url.getFile());
    }

}
