package com.sence.bean.request.tag;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.luck.picture.lib.entity.LocalMedia;

/**
 * Created by zwy on 2019/4/16.
 * package_name is com.sence.bean.request.tag
 * 描述:SenceGit
 */
public class RTagMultiItem implements MultiItemEntity {
    public static final int NORMAL = 1;
    public static final int LAST = 2;

    private int type;

    public RTagMultiItem(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public LocalMedia getLocalMedia() {
        return localMedia;
    }

    public void setLocalMedia(LocalMedia localMedia) {
        this.localMedia = localMedia;
    }

    private LocalMedia localMedia;

    @Override
    public int getItemType() {
        return type;
    }
}
