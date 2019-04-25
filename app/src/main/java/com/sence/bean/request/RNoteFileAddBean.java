package com.sence.bean.request;

import com.sence.bean.base.BaseFileRequestBean;
import com.sence.bean.base.BaseImageRequestBean;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    private File video;
    private File video_img;
    private boolean is_video;

    public RNoteFileAddBean(List<File> files, boolean is_video) {
        this.files = files;
        this.is_video = is_video;
    }

    private List<File> files;

    public RNoteFileAddBean(File video, File video_img, boolean is_video) {
        this.video = video;
        this.is_video = is_video;
        this.video_img = video_img;
    }


    @Override
    public Map getImgMap() {
        Map<String, File> map = new HashMap<>();
        if (is_video) {
            map.put("video_url", getVideo());
            map.put("img_url", getVideo_img());
        } else {
            for (int i = 0; i < files.size(); i++) {
                map.put("img_url_" + (i + 1), files.get(i));
            }
        }
        return map;
    }

    public File getVideo_img() {
        return video_img;
    }

    public void setVideo_img(File video_img) {
        this.video_img = video_img;
    }
}
