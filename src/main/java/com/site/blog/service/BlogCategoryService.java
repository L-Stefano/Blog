package com.site.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.site.blog.entity.BlogCategory;

/**
 * <p>
 * 博客分类 服务类
 * </p>
 *
 * @author: 南街
 * @since 2019-08-30
 */
public interface BlogCategoryService extends IService<BlogCategory> {

    public boolean clearCategory(BlogCategory blogCategory);

}
