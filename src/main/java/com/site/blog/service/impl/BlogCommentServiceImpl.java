package com.site.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.blog.dao.BlogCommentMapper;
import com.site.blog.entity.BlogComment;
import com.site.blog.service.BlogCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author: 南街
 * @since 2019-09-04
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

}
