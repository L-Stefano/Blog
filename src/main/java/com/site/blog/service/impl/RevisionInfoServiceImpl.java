package com.site.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.blog.dao.RevisionInfoMapper;
import com.site.blog.entity.RevisionInfo;
import com.site.blog.service.RevisionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevisionInfoServiceImpl extends ServiceImpl<RevisionInfoMapper, RevisionInfo> implements RevisionInfoService
{
    @Autowired
    private RevisionInfoMapper revisionInfoMapper;
}
