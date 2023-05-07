package com.site.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.blog.entity.RevisionInfo;
import com.site.blog.service.RevisionInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {

	@Autowired
	RevisionInfoService revisionInfoService;
	@Test
	public void contextLoads() {
//		//获取所有版本记录
//		QueryWrapper<RevisionInfo> queryWrapper = new QueryWrapper<>();
//		queryWrapper.lambda()
//				.orderByDesc(RevisionInfo::getRevisionDatetime);
//		System.out.println(revisionInfoService.list(queryWrapper));
	}

}
