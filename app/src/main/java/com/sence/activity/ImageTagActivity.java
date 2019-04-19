package com.sence.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.BindView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.sence.R;
import com.sence.base.BaseActivity;
import com.sence.bean.request.tag.RTagInfo;
import com.sence.bean.request.tag.RTagInfoItem;
import com.sence.utils.JsonParseUtil;
import com.sence.view.tag.ImageLayout;

import java.util.ArrayList;
import java.util.List;

public class ImageTagActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ConvenientBanner viewPager;

    private String tagInfo;

    private List<RTagInfo> tagInfoList;
    private int width;
    private int height;

    @Override
    public int onActLayout() {
        return R.layout.activity_image_tag;
    }

    @Override
    public void initView() {
        width = this.getIntent().getIntExtra("width", 0);
        height = this.getIntent().getIntExtra("height", 0);
        tagInfo = this.getIntent().getStringExtra("tagInfo");
        tagInfoList = JsonParseUtil.parseStringArray(tagInfo);
        viewPager.setCanLoop(false);
        viewPager.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, tagInfoList);
    }

    public class LocalImageHolderView implements Holder<RTagInfo> {
        ImageLayout layoutContent;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.tag_imgview_brower, null);
            layoutContent = (ImageLayout) view.findViewById(R.id.layoutContent);
            return view;
        }

        @Override
        public void UpdateUI(Context context, final int position, RTagInfo data) {
            String imgUrl = data.getUrl();
            ArrayList<RTagInfoItem> pointSimples = data.getTagInfoItems();
            layoutContent.setPoints(pointSimples);
            layoutContent.setImgBg(width, height, imgUrl);
        }
    }
}