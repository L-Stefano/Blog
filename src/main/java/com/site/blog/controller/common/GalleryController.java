package com.site.blog.controller.common;

import com.site.blog.service.BlogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GalleryController
{
    @Autowired
    BlogConfigService blogConfigService;
    @GetMapping("/gallery")
    public String gotoGallery(HttpServletRequest request)
    {

        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        request.setAttribute("pageName", "作品集");
        return "gallery/gallery";
    }
}
