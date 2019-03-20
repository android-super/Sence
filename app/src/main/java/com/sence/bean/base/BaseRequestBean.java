package com.sence.bean.base;

import com.sence.utils.MapTransformUtils;
import com.sence.utils.SPCommonUtils;

import java.util.Map;

/**
 * Created by zwy on 2019/3/20.
 * package_name is com.sence.bean.base
 * 描述:SenceGit
 */
public abstract class BaseRequestBean {
    public Map getMap() {
        Map<String, Object> map = MapTransformUtils.objectToMap(this);
        map.put("sign", SPCommonUtils.signParameter(map));
        map.put("time", SPCommonUtils.getTime());
        return map;
    }
}
