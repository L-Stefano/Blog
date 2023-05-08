package com.site.blog.constants;


/**
 * @description: 文章隐藏状态
 * @author: Stefano
 * @create: 2023-5-8 14:46:35
 **/
public enum HiddenStatusEnum {


    /**
     * 隐藏
     */
    DELETED(1, "隐藏"),
    /**
     * 显示/未隐藏
     */
    NO_DELETED(0, "显示");

    private final int status;
    private final String note;

    HiddenStatusEnum(int status, String note) {
        this.status = status;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public int getStatus() {
        return status;
    }
}
