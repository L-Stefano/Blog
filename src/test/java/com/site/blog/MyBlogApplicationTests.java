package com.site.blog;

import com.site.blog.service.RevisionInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {

	@Autowired
	RevisionInfoService revisionInfoService;
	@Autowired
	ResourcePatternResolver resourcePatternResolver;
	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void contextLoads() throws IOException
	{

	}

}
