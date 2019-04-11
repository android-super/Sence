package com.sence.bean.base;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zwy on 2019/4/11.
 * package_name is com.sence.bean.base
 * 描述:SenceGit
 */
public abstract class BaseFileRequestBean {
    public abstract Map getImgMap();

    public List<MultipartBody.Part> getRequestImg() {
        List<MultipartBody.Part> partList = new ArrayList<>();
        Map<String, File> map = getImgMap();
        for (String key : map.keySet()) {
            if (null != map.get(key)) {
                RequestBody head = RequestBody.create(MediaType.parse("image/png"), map.get(key));
                MultipartBody.Part part = MultipartBody.Part.createFormData(key, map.get(key).getName(), head);
                partList.add(part);
            }
        }
        return partList;
    }
}
