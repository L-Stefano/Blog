package com.site.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.blog.controller.common.GalleryController;
import com.site.blog.entity.RevisionInfo;
import com.site.blog.service.RevisionInfoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {

	@Autowired
	RevisionInfoService revisionInfoService;

	@Test
	public void contextLoads() {

	}

}
