package com.sence.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.luck.picture.lib.entity.LocalMedia;
import com.sence.R;
import com.sence.adapter.pager.ViewTagPagerAdapter;
import com.sence.base.BaseActivity;
import com.sence.fragment.tag.TagFragment;

import java.util.List;

/**
 * 添加标签
 */
public class AddTagActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tag_back)
    ImageView tagBack;
    @BindView(R.id.tag_title)
    TextView tagTitle;
    @BindView(R.id.tag_next)
    TextView tagNext;

    private ViewTagPagerAdapter adapter;
    private List<LocalMedia> localMedia;

    @Override
    public int onActLayout() {
        return R.layout.activity_add_tag;
    }

    @Override
    public void initView() {
        tagBack.setOnClickListener(this);
        tagNext.setOnClickListener(this);
        adapter = new ViewTagPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        localMedia = this.getIntent().getParcelableArrayListExtra("data");
        for (int i = 0; i < localMedia.size(); i++) {
            int position = i + 1;
            adapter.addFragment(TagFragment.newInstance(localMedia.get(i).getCompressPath()),
                    position + "");
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tagTitle.setText("添加标签(" + position + "/" + localMedia.size() + ")");
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
                Intent intent = new Intent(AddTagActivity.this, CommitTagActivity.class);
                startActivity(intent);
                break;
        }
    }
}
