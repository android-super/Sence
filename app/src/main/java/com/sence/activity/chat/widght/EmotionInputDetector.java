package com.sence.activity.chat.widght;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.sence.bean.response.PChatMessageBean;
import org.greenrobot.eventbus.EventBus;

/**
 * 作者：Rance on 2016/12/13 15:19
 * 邮箱：rance935@163.com
 * 输入框管理类
 */
public class EmotionInputDetector {
    private static final String SHARE_PREFERENCE_NAME = "com.dss886.emotioninputdetector";
    private static final String SHARE_PREFERENCE_TAG = "soft_input_height";

    private Activity mActivity;
    private InputMethodManager mInputManager;
    private SharedPreferences sp;
    private View mEmotionLayout;
    private EditText mEditText;
    private View mContentView;
    private View mSendButton;
    private View mJPButton;
    private View mEmotionButton;
    private ViewPager mViewPager;
    //    private View mAddButton;
    private Boolean isShowEmotion = false;
    private Boolean isShowAdd = false;


    private EmotionInputDetector() {
    }

    public static EmotionInputDetector with(Activity activity) {
        EmotionInputDetector emotionInputDetector = new EmotionInputDetector();
        emotionInputDetector.mActivity = activity;
        emotionInputDetector.mInputManager =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        emotionInputDetector.sp = activity.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return emotionInputDetector;
    }

    public EmotionInputDetector bindToContent(View contentView) {
        mContentView = contentView;
        return this;
    }

    /**
     * 输入框
     *
     * @param editText
     * @return
     */
    public EmotionInputDetector bindToEditText(EditText editText) {
        mEditText = editText;
        mEditText.requestFocus();
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && mEmotionLayout.isShown()) {
                    lockContentHeight();
                    hideEmotionLayout(true);
                    mJPButton.setVisibility(View.GONE);
                    mEmotionButton.setVisibility(View.VISIBLE);

                    mEditText.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            unlockContentHeightDelayed();
                        }
                    }, 200L);
                }
                return false;
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = s.toString().trim();
                if (trim.length() > 0) {
//                    mAddButton.setVisibility(View.GONE);
                    mSendButton.setVisibility(View.VISIBLE);
                } else {
//                    mAddButton.setVisibility(View.VISIBLE);
                    mSendButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return this;
    }

    /**
     * 表情按钮
     *
     * @param emotionButton
     * @return
     */
    public EmotionInputDetector bindToEmotionButton(View emotionButton) {
        mEmotionButton = emotionButton;
        emotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJPButton.setVisibility(View.VISIBLE);
                mEmotionButton.setVisibility(View.GONE);
                if (mEmotionLayout.isShown()) {
                    if (isShowAdd) {
                        mViewPager.setCurrentItem(0);
                        isShowEmotion = true;
                        isShowAdd = false;
                    } else {
                        lockContentHeight();
                        hideEmotionLayout(true);
                        isShowEmotion = false;
                        unlockContentHeightDelayed();
                    }
                } else {
                    if (isSoftInputShown()) {
                        lockContentHeight();
                        showEmotionLayout();
                        unlockContentHeightDelayed();
                    } else {
                        showEmotionLayout();
                    }

                    isShowEmotion = true;
                }

            }
        });
        return this;
    }

    /**
     * 发送按钮
     *
     * @param sendButton
     * @return
     */
    public EmotionInputDetector bindToSendButton(View sendButton) {
        mSendButton = sendButton;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSendButton.setVisibility(View.GONE);
                PChatMessageBean.MessageBean bean = new PChatMessageBean.MessageBean();
                bean.setContent(mEditText.getText().toString());
                bean.setType(1);
                EventBus.getDefault().post(bean);
                mEditText.setText("");
            }
        });
        return this;
    }

    /**
     * 键盘按钮
     *
     * @param jianpanButton
     * @return
     */
    public EmotionInputDetector bindToJPButton(final ImageView jianpanButton) {
        mJPButton = jianpanButton;
        jianpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmotionButton.setVisibility(View.VISIBLE);
                jianpanButton.setVisibility(View.GONE);
                mEmotionLayout.setVisibility(View.GONE);
                showSoftInput();
            }
        });
        return this;
    }


    public EmotionInputDetector setEmotionView(View emotionView) {
        mEmotionLayout = emotionView;
        return this;
    }

    public EmotionInputDetector setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        return this;
    }

    public boolean interceptBackPress() {
        if (mEmotionLayout.isShown()) {
            hideEmotionLayout(false);
            return true;
        }
        return false;
    }

    private void showEmotionLayout() {
        int softInputHeight = getSupportSoftInputHeight();
        if (softInputHeight == 0) {
            softInputHeight = sp.getInt(SHARE_PREFERENCE_TAG, 787);
        }
        hideSoftInput();
//        mEmotionLayout.getLayoutParams().height = Utils.dp2px(this.mActivity,168);
        mEmotionLayout.setVisibility(View.VISIBLE);
    }

    public void hideEmotionLayout(boolean showSoftInput) {
        if (mEmotionLayout.isShown()) {
            mEmotionLayout.setVisibility(View.GONE);
            if (showSoftInput) {
                showSoftInput();
            }
        }
    }

    private void lockContentHeight() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mContentView.getLayoutParams();
        params.height = mContentView.getHeight();
//        params.weight = 0.0F;
    }

    private void unlockContentHeightDelayed() {
//        mEditText.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ((LinearLayout.LayoutParams) mContentView.getLayoutParams()).weight = 1.0F;
//            }
//        }, 200L);
    }

    private void showSoftInput() {
        mEditText.requestFocus();
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                mInputManager.showSoftInput(mEditText, 0);
            }
        });
    }

    public void hideSoftInput() {
        mInputManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    private boolean isSoftInputShown() {
        return getSupportSoftInputHeight() != 0;
    }

    private int getSupportSoftInputHeight() {
        Rect r = new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int screenHeight = mActivity.getWindow().getDecorView().getRootView().getHeight();
        int softInputHeight = screenHeight - r.bottom;

        if (Build.VERSION.SDK_INT >= 20) {
            // When SDK Level >= 20 (Android L), the softInputHeight will contain the height of softButtonsBar (if has)
            softInputHeight = softInputHeight - getSoftButtonsBarHeight();
        }
        if (softInputHeight < 0) {
            Log.w("EmotionInputDetector", "Warning: value of softInputHeight is below zero!");
        }
        if (softInputHeight > 0) {
            sp.edit().putInt(SHARE_PREFERENCE_TAG, softInputHeight).apply();
        }
        return softInputHeight;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }


}
