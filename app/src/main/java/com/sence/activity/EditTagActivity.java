package com.sence.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.luck.picture.lib.entity.LocalMedia;
import com.sence.R;
import com.sence.adapter.pager.ViewTagPagerAdapter;
import com.sence.base.BaseActivity;
import com.sence.bean.request.tag.RTagInfo;
import com.sence.bean.request.tag.RTagInfoItem;
import com.sence.fragment.tag.TagFragment;
import com.sence.utils.JsonParseUtil;
import com.sence.utils.StatusBarUtil;
import com.sence.utils.TagUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 编辑标签
 */
public class EditTagActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tag_back)
    ImageView tagBack;
    @BindView(R.id.tag_title)
    TextView tagTitle;
    @BindView(R.id.tag_next)
    TextView tagNext;

    private ViewTagPagerAdapter adapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_add_tag;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        tagBack.setOnClickListener(this);
        tagNext.setOnClickListener(this);
        adapter = new ViewTagPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < TagUtils.tagInfos.size(); i++) {
            adapter.addFragment(TagFragment.newInstance(TagUtils.localMedia.get(i).getCompressPath(), i, false),
                    i + "");
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(TagUtils.tagInfos.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tagTitle.setText("添加标签(" + (position + 1) + "/" + TagUtils.tagInfos.size() + ")");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tag_back:
                finish();
                break;
            case R.id.tag_next:
                Intent intent = new Intent(EditTagActivity.this, CommitTagActivity.class);
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) TagUtils.localMedia);
                intent.putExtra("is_video", false);
                intent.putExtra("width", TagUtils.width);
                intent.putExtra("height", TagUtils.height);
                intent.putExtra("tagInfo", JsonParseUtil.toJson(TagUtils.tagInfos));
                startActivity(intent);
                break;
        }
    }
}
