package com.site.blog.controller.common;

import com.site.blog.service.BlogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/gallery/photos")
    @ResponseBody
    public List<String> getPhotoList() {
        List<String> photos = new ArrayList<>();
        String photoDir = "src/main/resources/static/gallery/photos";
        File[] photoFiles = new File(photoDir).listFiles();
        for (File photoFile : photoFiles) {
            String photoName = photoFile.getName();
            if (photoFile.isFile() && photoName.matches(".*\\.(jpe?g|png|gif)$")) {
                photos.add(photoName);
            }
        }
        return photos;
    }
}
