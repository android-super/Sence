package com.sence.utils;

import com.luck.picture.lib.entity.LocalMedia;
import com.sence.bean.request.tag.RTagInfo;
import com.sence.bean.request.tag.RTagInfoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwy on 2019/4/16.
 * package_name is com.sence.utils
 * 描述:SenceGit
 */
public class TagUtils {
    public static int width;
    public static int height;
    public static List<LocalMedia> localMedia = new ArrayList<>();
    public static ArrayList<RTagInfo> tagInfos = new ArrayList<>();

    public static void clear() {
        width = 0;
        height = 0;
        localMedia.clear();
        tagInfos.clear();
    }
}
