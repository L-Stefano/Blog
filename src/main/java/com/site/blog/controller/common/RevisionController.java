package com.site.blog.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.blog.entity.RevisionInfo;
import com.site.blog.service.BlogConfigService;
import com.site.blog.service.RevisionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RevisionController
{

    @Autowired
    ResourcePatternResolver resourcePatternResolver;
    @Autowired
    BlogConfigService blogConfigService;
    @Autowired
    RevisionInfoService revisionInfoService;

    /**
     * 更新历史；版本记录
     * 前端进行流加载
     *
     * @param request
     * @return
     */
    @GetMapping({"/revisions"})
    public String historicalRevisions(HttpServletRequest request) throws IOException
    {
        //获取所有版本记录
        QueryWrapper<RevisionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByDesc(RevisionInfo::getRevisionDatetime);
        List<RevisionInfo> revisionInfoList = revisionInfoService.list(queryWrapper);

        //每页显示数
        int pageSingleSize = 4;

        request.setAttribute("revisionInfoList",revisionInfoList);//所有版本记录传给前端
        request.setAttribute("pageSingleSize",pageSingleSize);//每页显示数
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        request.setAttribute("pageName", "更新历史");

		List<String> headerImgs = new ArrayList<>();
		try {
			Resource[] resources = resourcePatternResolver.getResources("classpath:/static/blog/amaze/images/headers/*.{jpg,jpeg,png,gif}");
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					headerImgs.add(resource.getFilename());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        int headerImgIndex = (int) (Math.random() * headerImgs.size());
        request.setAttribute("headerImg", headerImgs.get(headerImgIndex));

        return "revisions/revisions.html";
    }
}
