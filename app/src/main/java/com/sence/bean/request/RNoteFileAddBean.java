package com.sence.bean.request;

import com.sence.bean.base.BaseFileRequestBean;
import com.sence.bean.base.BaseImageRequestBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwy on 2019/4/13.
 * package_name is com.sence.bean.request
 * 描述:SenceGit
 */
public class RNoteFileAddBean extends BaseFileRequestBean {
    public File getVideo() {
        return video;
    }

    public void setVideo(File video) {
        this.video = video;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    private File video;
    private boolean is_video;

    public RNoteFileAddBean(File[] files, boolean is_video) {
        this.files = files;
        this.is_video = is_video;
    }

    private File[] files;

    public RNoteFileAddBean(File video, boolean is_video) {
        this.video = video;
        this.is_video = is_video;
    }


    @Override
    public Map getImgMap() {
        Map<String, File> map = new HashMap<>();
        if (is_video) {
            map.put("video_url", getVideo());
        } else {
            for (int i = 0; i < files.length; i++) {
                map.put("img_url_" + (i + 1), files[i]);
            }
        }
        return map;
    }
}
