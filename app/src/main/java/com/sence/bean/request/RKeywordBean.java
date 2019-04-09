package com.sence.bean.request;

import com.sence.bean.base.BaseRequestBean;

/**
 * Created by zwy on 2019/4/9.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RKeywordBean extends BaseRequestBean {
    public RKeywordBean(String keyword) {
        this.keyword = keyword;
    }

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
