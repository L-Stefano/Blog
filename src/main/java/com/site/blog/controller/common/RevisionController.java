package com.site.blog.controller.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.blog.entity.RevisionInfo;
import com.site.blog.service.BlogConfigService;
import com.site.blog.service.RevisionInfoService;
import com.site.blog.service.impl.BlogConfigServiceImpl;
import com.site.blog.service.impl.RevisionInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RevisionController
{

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
    public String historicalRevisions(HttpServletRequest request)
    {
        //获取所有版本记录
        QueryWrapper<RevisionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .orderByDesc(RevisionInfo::getRevisionDatetime);
        List<RevisionInfo> revisionInfoList = revisionInfoService.list(queryWrapper);

        //每页显示数
        int pageSingleSize = 2;

        request.setAttribute("revisionInfoList",revisionInfoList);//所有版本记录传给前端
        request.setAttribute("pageSingleSize",pageSingleSize);//每页显示数
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        request.setAttribute("pageName", "更新历史");
        return "revisions/revisions.html";
    }
}
