package com.site.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.blog.constants.HttpStatusEnum;
import com.site.blog.entity.BlogInfo;
import com.site.blog.entity.BlogTagRelation;
import com.site.blog.entity.RevisionInfo;
import com.site.blog.pojo.dto.AjaxPutPage;
import com.site.blog.pojo.dto.AjaxResultPage;
import com.site.blog.pojo.dto.Result;
import com.site.blog.service.RevisionInfoService;
import com.site.blog.util.DateUtils;
import com.site.blog.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class RevisionManagementController
{
    @Autowired
    RevisionInfoService revisionInfoService;
    @GetMapping("/v1/revisionAddition")
    public String gotoRevisionAddition()
    {
        return "adminLayui/revisionAddition";
    }
    @GetMapping("/v1/revisionManagement")
    public String gotoRevisionManagement()
    {
        return "adminLayui/revisionManagement";
    }

    @PostMapping (value = "/v1/revisionAddition/addition")
    @ResponseBody
    public Result<String> additionRevision(@RequestParam("revisionDatetime")String revisionDatetime, @RequestParam("revisionContent") String revisionContent,@RequestParam(value = "revisionId",required = false) Long revisionId) throws ParseException
    {

        String pattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        if(!Pattern.matches(pattern, revisionDatetime))
        {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(revisionDatetime);
        RevisionInfo revisionInfo=new RevisionInfo()
                .setRevisionDatetime(date)
                .setRevisionContent(revisionContent);
        if(revisionId!=null)
            revisionInfo.setRevisionId(revisionId);

        boolean flag = revisionInfoService.saveOrUpdate(revisionInfo);
        if (flag) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/v1/revisionManagement/list")
    @ResponseBody
    public AjaxResultPage<RevisionInfo> getContractList(AjaxPutPage<RevisionInfo> ajaxPutPage, RevisionInfo condition) {
        QueryWrapper<RevisionInfo> queryWrapper = new QueryWrapper<>(condition);
        queryWrapper.lambda().orderByDesc(RevisionInfo::getRevisionDatetime);
        Page<RevisionInfo> page = ajaxPutPage.putPageToPage();
        revisionInfoService.page(page, queryWrapper);
        AjaxResultPage<RevisionInfo> result = new AjaxResultPage<>();
        result.setData(page.getRecords());
        result.setCount(page.getTotal());
        return result;
    }

    @ResponseBody
    @PostMapping("/v1/revisionManagement/clear")
    public Result<String> clearRevision(@RequestParam Long revisionId) {
        if (revisionInfoService.removeById(revisionId)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/v1/revisionManagement/edit")
    public String gotoBlogEdit(@RequestParam(required = false) Long revisionId, Model model) {
        if (revisionId != null) {
            RevisionInfo revisionInfo = revisionInfoService.getById(revisionId);
            model.addAttribute("revisionInfo", revisionInfo);
        }
        return "adminLayui/revisionAddition";
    }
}
