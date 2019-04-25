package com.sence.bean.request;

import com.sence.bean.base.BaseFileRequestBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RImageListBean extends BaseFileRequestBean {
    public RImageListBean(File[] files) {
        this.files = files;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    private File[] files;
    @Override
    public Map getImgMap() {
        Map<String,File> map = new HashMap<>();
        if(map.size()>0){
            for (int i = 0 ;i <getFiles().length; i++){
                map.put("imgs["+i +"]",files[i]);
            }
        }
        return map;
    }
}
