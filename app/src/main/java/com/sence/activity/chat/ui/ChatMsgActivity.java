package com.sence.activity.chat.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.sence.R;
import com.sence.activity.MyInfoActivity;
import com.sence.activity.chat.adapter.CommonFragmentPagerAdapter;
import com.sence.activity.chat.bean.ChatSocketBean;
import com.sence.activity.chat.bean.FullImageInfo;
import com.sence.activity.chat.util.GlobalOnItemClickManagerUtils;
import com.sence.activity.chat.util.Utils;
import com.sence.activity.chat.widght.EmotionPrivateInputDetector;
import com.sence.activity.chat.widght.NoScrollViewPager;
import com.sence.base.BaseActivity;
import com.sence.bean.request.chat.RChatListBean;
import com.sence.bean.request.chat.RChatListIdBean;
import com.sence.bean.request.chat.RSendImgFileBean;
import com.sence.bean.request.chat.RSendPrivateImgMessageBean;
import com.sence.bean.request.chat.RSendPrivateMessageBean;
import com.sence.bean.request.chat.RUtoBean;
import com.sence.bean.response.PChatPrivateMessageBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.BitmapUtils;
import com.sence.utils.DateUtils;
import com.sence.utils.GlideUtils;
import com.sence.utils.JsonParseUtil;
import com.sence.utils.LoginStatus;
import com.sence.utils.SocketUtils;
import com.sence.utils.StatusBarUtil;
import com.sence.view.PubTitle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ChatMsgActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.emotion_send)
    ImageView emotionSend;
    @BindView(R.id.chat_function_photo)
    ImageView chatFunctionPhoto;
    @BindView(R.id.chat_function_photograph)
    ImageView chatFunctionPhotograph;
    @BindView(R.id.emotion_button)
    ImageView emotionButton;
    @BindView(R.id.jianpan_button)
    ImageView jianpanButton;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.emotion_layout)
    RelativeLayout emotionLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.pub_title)
    PubTitle pubTitle;

    private EmotionPrivateInputDetector mDetector;

    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private CommonFragmentPagerAdapter adapter;

    private File imgFile;
    private String imgPath;

    private int type;
    private String uid;
    private int page = 1;

    private ReAdapter reAdapter;
    private List<PChatPrivateMessageBean> dataList = new ArrayList<>();

    private long lastVisibleTime;//第一条消息的时间
    private boolean isFirst = true; //是否为第一个  第一个也要显示时间

    private String u_to;//聊天用戶id
    private String chat_id;//聊天id(从列表传递过来)
    private String u_avatar;//聊天用户头像
    private String name;//聊天室名称

    public void initView() {
        StatusBarUtil.setLightMode(this);
        EventBus.getDefault().register(this);

        u_to = getIntent().getStringExtra("u_to");
        chat_id = getIntent().getStringExtra("chat_id");
        u_avatar = getIntent().getStringExtra("u_avatar");
        name = getIntent().getStringExtra("name");
        uid = LoginStatus.getUid();
        pubTitle.setTitleText(name);
        initWidget();
        showMessage();
        SocketUtils.getInstance().setOnGetSocketResult(new SocketUtils.OnGetSocketResult() {
            @Override
            public void putSocketResult(String str) {
                ChatSocketBean socketBean = JsonParseUtil.parseString(str, ChatSocketBean.class);
                if (socketBean.getType().equals("chat")) {
                    PChatPrivateMessageBean bean = new PChatPrivateMessageBean();
                    bean.setContent(socketBean.getData().getContent());
                    bean.setType(socketBean.getData().getType());
                    bean.setAdd_time(socketBean.getData().getAdd_time());
                    bean.setUid(socketBean.getData().getUid());
                    bean.setImg(socketBean.getData().getImg());
                    int currentTime = (int) (System.currentTimeMillis() / 1000);
                    if ((currentTime - lastVisibleTime) / 60 > 10) {
                        lastVisibleTime = currentTime;
                        bean.setTimeVisible(true);
                    } else {
                        bean.setTimeVisible(false);
                    }
                    reAdapter.addData(bean);
                    recyclerView.scrollToPosition(reAdapter.getData().size() - 1);
                    readMsgData();
                }
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        reAdapter = new ReAdapter(R.layout.rv_chat_msg_item);
        recyclerView.setAdapter(reAdapter);
        reAdapter.setUpFetchEnable(true);
        reAdapter.setStartUpFetchPosition(2);

        reAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                startUpFetch();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        jianpanButton.setVisibility(View.GONE);
                        emotionButton.setVisibility(View.VISIBLE);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        jianpanButton.setVisibility(View.GONE);
                        emotionButton.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        reAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter ba, View view, int position) {
                switch (view.getId()) {
                    case R.id.head_left://左边头像
                        Intent intent = new Intent(ChatMsgActivity.this, MyInfoActivity.class);
                        intent.putExtra("uid", reAdapter.getData().get(position).getTo_uid());
                        startActivity(intent);
                        break;
                    case R.id.head_right://右边头像 自己
                        intent = new Intent(ChatMsgActivity.this, MyInfoActivity.class);
                        intent.putExtra("uid", reAdapter.getData().get(position).getUid());
                        startActivity(intent);
                        break;
                    case R.id.left_image://左边大图片
                    case R.id.right_image://右边大图片
                        if (reAdapter.getData().get(position).getType() == 2) {
                            if (!reAdapter.getData().get(position).getImg().contains("/Public")) {
                                imgPath = reAdapter.getData().get(position).getContent();
                            } else {
                                imgPath = Urls.base_url + reAdapter.getData().get(position).getImg();
                            }
                            int location[] = new int[2];
                            view.getLocationOnScreen(location);
                            FullImageInfo fullImageInfo = new FullImageInfo();
                            fullImageInfo.setLocationX(location[0]);
                            fullImageInfo.setLocationY(location[1]);
                            fullImageInfo.setWidth(view.getWidth());
                            fullImageInfo.setHeight(view.getHeight());
                            fullImageInfo.setImageUrl(imgPath);
                            EventBus.getDefault().postSticky(fullImageInfo);
                            startActivity(new Intent(ChatMsgActivity.this, FullImageActivity.class));
                            overridePendingTransition(0, 0);
                        }
                        break;
                }
            }
        });

        chatFunctionPhoto.setOnClickListener(this);
        chatFunctionPhotograph.setOnClickListener(this);
    }

    @Override
    public int onActLayout() {
        return R.layout.activity_chat_msg;
    }

    private void initWidget() {
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mDetector = EmotionPrivateInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .bindToContent(recyclerView)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToJPButton(jianpanButton)
                .bindToSendButton(emotionSend);

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);
    }

    private void startUpFetch() {
        page++;
        showMessage();
        /**
         * set fetching on when start network request.
         */
        reAdapter.setUpFetching(true);
        /**
         * get data from internet.
         */
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dataList.size() < 10) {
                    reAdapter.setUpFetchEnable(false);
                }
//                reAdapter.addData(0, dataList);
                /**
                 * set fetching off when network request ends.
                 */
                reAdapter.setUpFetching(false);
            }
        }, 300);
    }

    //添加聊天记录
    private void sendChatMessage(boolean isImgMsg) {
        HttpManager httpManager;
        if (isImgMsg) {
            httpManager =
                    HttpManager.getInstance().PlayNetCode(HttpCode.CHAT_PRIVATE_SEND,
                            new RSendPrivateImgMessageBean(u_to,
                                    LoginStatus.getUid()), new RSendImgFileBean(imgFile));
        } else {
            httpManager =
                    HttpManager.getInstance().PlayNetCode(HttpCode.CHAT_PRIVATE_SEND, new RSendPrivateMessageBean(u_to,
                            LoginStatus.getUid(), editText.getText().toString()));
        }
        httpManager.request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {

            }
        });
    }

    //聊天记录
    private void showMessage() {
        HttpManager httpManager;
        if (TextUtils.isEmpty(chat_id)) {
            httpManager = HttpManager.getInstance().PlayNetCode(HttpCode.CHAT_PRIVATE_LIST,
                    new RChatListBean(LoginStatus.getUid(),
                            u_to,
                            page + ""));
        } else {
            httpManager = HttpManager.getInstance().PlayNetCode(HttpCode.CHAT_PRIVATE_LIST,
                    new RChatListIdBean(LoginStatus.getUid(),
                            u_to,
                            page + "", chat_id));
        }
        httpManager.request(new ApiCallBack<List<PChatPrivateMessageBean>>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(List<PChatPrivateMessageBean> o, String msg) {
                if (o == null || o.size() == 0) {
                    return;
                }
                if(page==1){
                    dataList.clear();
                }
                Collections.reverse(o);
                dataList.addAll(0,o);
                reAdapter.setNewData(dataList);
                if (page == 1) {

                    recyclerView.scrollToPosition(reAdapter.getData().size() - 1);
                }

                if (page == 1) {
                    lastVisibleTime = reAdapter.getData().get(reAdapter.getData().size() - 1).getAdd_time();
                }
                for (int i = 0; i < reAdapter.getData().size(); i++) {
                    if (Math.abs((lastVisibleTime - reAdapter.getData().get(i).getAdd_time()) / 60) > 10) {
                        isFirst = false;
                        if (i > 1) {
                            lastVisibleTime = reAdapter.getData().get(i - 1).getAdd_time();
                            reAdapter.getData().get(i - 1).setTimeVisible(true);
                        } else {
                            lastVisibleTime = reAdapter.getData().get(i).getAdd_time();
                            reAdapter.getData().get(i).setTimeVisible(true);
                        }

                    } else {
                        reAdapter.getData().get(i).setTimeVisible(false);
                    }
                    if (Math.abs((lastVisibleTime - reAdapter.getData().get(i).getAdd_time()) / 60) < 10 &&
                            isFirst) {
                        reAdapter.getData().get(0).setTimeVisible(true);
                    }
                }

            }
        });
    }

    private void readMsgData() {
        HttpManager.getInstance().PlayNetCode(HttpCode.CHAT_PRIVATE_READ, new RUtoBean(LoginStatus.getUid(), u_to)).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {

            }

            @Override
            public void onSuccess(Object o, String msg) {

            }
        });
    }

    /**
     * EventBus
     *
     * @param messageInfo
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final PChatPrivateMessageBean messageInfo) {
        messageInfo.setUid(uid);
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        messageInfo.setAdd_time(currentTime);
        if ((currentTime - lastVisibleTime) / 60 > 10) {
            lastVisibleTime = currentTime;
            messageInfo.setTimeVisible(true);
        } else {
            messageInfo.setTimeVisible(false);
        }
        reAdapter.addData(messageInfo);
        recyclerView.scrollToPosition(reAdapter.getData().size() - 1);
        type = messageInfo.getType();

        if (type == 1) {
            sendChatMessage(false);
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendChatMessage(true);
                }
            }).start();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_function_photo://选择照片
                PictureSelector.create(ChatMsgActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .compress(true)
                        .imageFormat(PictureMimeType.PNG)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.chat_function_photograph://拍照
                PictureSelector.create(ChatMsgActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .compress(true)
                        .imageFormat(PictureMimeType.PNG)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
        }
    }


    /**
     * 拍照
     */


    @Override
    public void onActivityResult(int req, int res, Intent data) {
        if (res == RESULT_OK) {
            switch (req) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (selectList.get(0).isCompressed()) {
                        Bitmap bitmap = BitmapUtils.getSmallBitmap(selectList.get(0).getCompressPath());
                        imgFile = BitmapUtils.Bitmap2File(bitmap, getPackageName(), 100);
                        PChatPrivateMessageBean bean = new PChatPrivateMessageBean();
                        bean.setImg(selectList.get(0).getCompressPath());
                        bean.setType(2);
                        EventBus.getDefault().post(bean);
                    }
                    break;
            }
        }
    }

    /**
     * ReAdapter
     */
    class ReAdapter extends BaseQuickAdapter<PChatPrivateMessageBean, BaseViewHolder> {

        public ReAdapter(@LayoutRes int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder holder, PChatPrivateMessageBean dataBean) {
            ImageView left_image = holder.getView(R.id.left_image);
            ImageView right_image = holder.getView(R.id.right_image);
            TextView right_msg = holder.getView(R.id.right_msg);
            TextView left_msg = holder.getView(R.id.left_msg);
            TextView chat_time = holder.getView(R.id.chat_time);
            TextView right_name = holder.getView(R.id.right_name);
            TextView left_name = holder.getView(R.id.left_name);
            LinearLayout left_layout = holder.getView(R.id.left_layout);
            TextView join_tribe = holder.getView(R.id.join_tribe);//邀请 自己看见的底下的灰条
            Context mContext = holder.itemView.getContext();

            //判断时间日期显示不显示
            boolean timeVisible = dataBean.isTimeVisible();
            if (timeVisible) {
                chat_time.setText(DateUtils.chatToFormatDate(dataBean.getAdd_time()));
                chat_time.setVisibility(View.VISIBLE);
            } else {
                chat_time.setVisibility(View.GONE);
            }

            //判断是否是自己   是自己
            if (dataBean.getUid().equals(uid)) {
                left_image.setVisibility(View.GONE);
                //判断消息类型   1普通文字  2图片 3部落邀请
                if (dataBean.getType() == 2) {//2图片
                    holder.getView(R.id.head_right).setVisibility(View.VISIBLE);
                    right_image.setVisibility(View.VISIBLE);
                    holder.getView(R.id.right_layout).setVisibility(View.GONE);

                    if (!dataBean.getImg().contains("/Public")) {
                        Log.e("TAG", dataBean.getImg() + "=========true");
                        GlideUtils.getInstance().loadNormal(dataBean.getImg(), right_image, true);
                    } else {
                        Log.e("TAG", dataBean.getImg() + "=========false");
                        GlideUtils.getInstance().loadNormal(dataBean.getImg(), right_image);
                    }

                } else if (dataBean.getType() == 1) {//1普通文字
                    right_image.setVisibility(View.GONE);
                    holder.getView(R.id.head_right).setVisibility(View.VISIBLE);
                    holder.getView(R.id.right_layout).setVisibility(View.VISIBLE);
                    right_msg.setText(Utils.getEmotionContent(holder.itemView.getContext(), right_msg,
                            dataBean.getContent()));
                    right_name.setText(dataBean.getNick_name());
                }
                holder.getView(R.id.left_name).setVisibility(View.GONE);
                left_layout.setVisibility(View.GONE);
                holder.getView(R.id.head_left).setVisibility(View.GONE);

                String avatar = LoginStatus.getAvatar();
                if (avatar != null) {
                    GlideUtils.getInstance().loadHead(avatar, (ImageView) holder.getView(R.id.head_right));
                }

            } else {//是别人
                if (dataBean.getType() == 2) {//2图片
                    left_image.setVisibility(View.VISIBLE);
                    left_layout.setVisibility(View.GONE);
                    GlideUtils.getInstance().loadNormal(dataBean.getImg(), left_image);
                } else if (dataBean.getType() == 1) {//1普通文字
                    left_image.setVisibility(View.GONE);
                    left_layout.setVisibility(View.VISIBLE);
                    left_msg.setText(Utils.getEmotionContent(holder.itemView.getContext(), left_msg,
                            dataBean.getContent()));
                    left_name.setText(dataBean.getNick_name());
                }
                holder.getView(R.id.right_name).setVisibility(View.GONE);
                right_image.setVisibility(View.GONE);
                holder.getView(R.id.head_left).setVisibility(View.VISIBLE);
                holder.getView(R.id.right_layout).setVisibility(View.GONE);
                holder.getView(R.id.head_right).setVisibility(View.GONE);
                GlideUtils.getInstance().loadHead(u_avatar, (ImageView) holder.getView(R.id.head_left));
            }

            holder.addOnClickListener(R.id.left_image);
            holder.addOnClickListener(R.id.right_image);
            holder.addOnClickListener(R.id.chat_agree);
            holder.addOnClickListener(R.id.chat_look);
            holder.addOnClickListener(R.id.head_left);
            holder.addOnClickListener(R.id.head_right);
        }
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataList.clear();
        EventBus.getDefault().removeStickyEvent(this);
        EventBus.getDefault().unregister(this);
        PictureFileUtils.deleteCacheDirFile(ChatMsgActivity.this);
    }

}
