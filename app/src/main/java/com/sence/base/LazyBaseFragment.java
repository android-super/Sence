package com.sence.base;

import android.view.View;

import androidx.fragment.app.Fragment;

public abstract class LazyBaseFragment  extends Fragment {
    public View view;

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;


    /**
     * 初始化view对象，这里在Fragment中的onCreateView方法中进行实现，返回一个View对象
     */
    public abstract View initView();


    /**
     * 初始化数据
     */
    public abstract void initData();


    //先于oncreatview执行的方法
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    /**
     * 延迟加载
     */
    protected abstract void lazyLoad();


}
