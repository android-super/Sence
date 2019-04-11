package com.sence.bean.base;

import com.sence.utils.MapTransformUtils;
import com.sence.utils.SPCommonUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.Map;

/**
 * Created by zwy on 2019/3/20.
 * package_name is com.sence.bean.base
 * 描述:SenceGit
 */
public abstract class BaseImageRequestBean {
    public Map getMap() {
        Map<String, Object> map = MapTransformUtils.objectToMap(this);
        Map<String, RequestBody> mapBody = MapTransformUtils.objectToMapImg(this);
        mapBody.put("sign", RequestBody.create(MediaType.parse("text/plain"),
                SPCommonUtils.signParameter(map)));
        mapBody.put("time", RequestBody.create(MediaType.parse("text/plain"),
                SPCommonUtils.getTime()));
        return mapBody;
    }
}
