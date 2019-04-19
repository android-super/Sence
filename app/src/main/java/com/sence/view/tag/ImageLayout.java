package com.sence.view.tag;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sence.R;
import com.sence.bean.request.tag.RTagInfoItem;
import com.sence.utils.Arith;
import com.sence.utils.GlideUtils;
import com.sence.view.PictureTagView;

import java.util.ArrayList;

public class ImageLayout extends FrameLayout implements View.OnClickListener {
    ArrayList<RTagInfoItem> points;
    FrameLayout layouPoints;
    ImageView imgBg;
    Context mContext;

    private int tag_width;//标签内部距离
    private int tag_height;//标签内部高
    private double tag_scale;//标签缩放比
    private double tag_gap;//标签差值

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mContext = context;
        View imgPointLayout = inflate(context, R.layout.tag_imgview_point, this);
        imgBg = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setImgBg(final int width, final int height, String imgUrl) {
        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;
        imgBg.setLayoutParams(lp);

        GlideUtils.getInstance().loadNormal(imgUrl, imgBg, true);
        Glide.with(this).asBitmap()
                .load(imgUrl)
                //强制Glide返回一个Bitmap对象
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target,
                                                boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        tag_width = resource.getWidth();
                        tag_height = resource.getHeight();
                        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
                        lp1.width = width;
                        lp1.height = tag_height;
                        tag_gap = Arith.sub(width, tag_width) / 2;
                        tag_scale = Arith.div(tag_width, width, 4);
                        layouPoints.setLayoutParams(lp1);
                        addItem(tag_width, tag_height, tag_gap);
                        return false;
                    }
                }).into(imgBg);

    }

    public void setPoints(ArrayList<RTagInfoItem> points) {
        this.points = points;
    }

    private void addItem(int width, int height, double tag_gap) {
        layouPoints.removeAllViews();
        for (int i = 0; i < points.size(); i++) {
            View view = null;
            double width_scale = points.get(i).getWidth_scale();
            double height_scale = points.get(i).getHeight_scale();
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            if (width_scale >= 0.52) {
                params.leftMargin = (int) (width_scale * width) + (int) tag_gap;
                view = new PictureTagView(getContext(), PictureTagView.Direction.Right);
            } else if (width_scale <= 0.48) {
                params.leftMargin = (int) ((width_scale * width) * tag_scale) + (int) tag_gap;
                view = new PictureTagView(getContext(), PictureTagView.Direction.Left);
            } else {
                params.leftMargin = (int) (width_scale * width) + (int) tag_gap;
                view = new PictureTagView(getContext(), PictureTagView.Direction.Right);
            }
            ((PictureTagView) view).setContent(points.get(i).getContent());
            params.topMargin = (int) (height * height_scale);
            //上下位置在视图内
            if (params.topMargin < 0) {
                params.topMargin = 0;
            } else if ((params.topMargin + PictureTagView.getViewHeight()) > height) {
                params.topMargin = height - PictureTagView.getViewHeight();
            }
            view.setTag(i);
            view.setOnClickListener(this);
            layouPoints.addView(view, params);
        }
    }


    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        ToastUtils.showShort("pos : " + pos);
    }
}