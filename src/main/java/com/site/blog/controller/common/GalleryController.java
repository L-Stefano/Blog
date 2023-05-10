package com.site.blog.controller.common;

import com.site.blog.service.BlogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GalleryController
{
    @Autowired
    ResourcePatternResolver resourcePatternResolver;
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    BlogConfigService blogConfigService;
    @GetMapping("/gallery")
    public String gotoGallery(HttpServletRequest request) throws IOException
    {

        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        request.setAttribute("pageName", "作品集");

        //随机选择一个头图，传递给前端
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

        return "gallery/gallery";
    }

    @GetMapping("/gallery/photos")
    @ResponseBody
    public List<String> getPhotoList() throws IOException
    {
        List<String> photos = new ArrayList<>();
        Resource[] resources = resourcePatternResolver.getResources("classpath:/static/gallery/photos/*.{jpg,jpeg,png,gif}");
        for (Resource resource : resources) {
            String filename = resource.getFilename();
            if (filename != null) {
                photos.add(filename);
            }
        }
        return photos;
    }
}
