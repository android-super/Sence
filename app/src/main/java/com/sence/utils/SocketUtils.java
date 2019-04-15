package com.sence.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.blankj.utilcode.util.PhoneUtils;
import com.orhanobut.logger.Logger;
import com.sence.activity.chat.bean.ChatSocketBean;
import com.sence.bean.request.RBindClientID;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.Urls;
import com.sence.net.manager.ApiCallBack;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/6/13.
 */

public class SocketUtils {

    // Socket变量
    private Socket socket;
    // 线程池
    // 为了方便展示,此处直接采用线程池进行线程管理,而没有一个个开线程
    private ExecutorService mThreadPool;
    /**
     * 接收服务器消息 变量
     */
    // 输入流对象
    private InputStream is;
    // 接收服务器发送过来的消息
    private String response;

    private String client_id;

    public static SocketUtils instance = null;


    private OnGetSocketResult onGetSocketResult;
    private OnMainSocketResult onMainSocketResult;
    private OnBaseSocketResult onBaseSocketResult;

    private OnMainOnlySocketResult onMainOnlySocketResult;

    public interface OnMainOnlySocketResult {
        void setSocketStatus(String msg);
    }

    public void setOnMainOnlySocketResult(OnMainOnlySocketResult onMainOnlySocketResult) {
        this.onMainOnlySocketResult = onMainOnlySocketResult;
    }

    public String getClient_id() {
        return client_id;
    }

    public interface OnGetSocketResult {
        void putSocketResult(String str);
    }

    public interface OnMainSocketResult {
        void putSocketResult(String str);
    }

    public interface OnBaseSocketResult {
        void putSocketResult(String str);
    }

    public void setOnGetSocketResult(OnGetSocketResult onGetSocketResult) {
        this.onGetSocketResult = onGetSocketResult;
    }

    public void setOnMainSocketResult(OnMainSocketResult onMainSocketResult) {
        this.onMainSocketResult = onMainSocketResult;
    }

    public void setOnBaseSocketResult(OnBaseSocketResult onBaseSocketResult) {
        this.onBaseSocketResult = onBaseSocketResult;
    }


    public static SocketUtils getInstance() {
        if (instance == null) {
            synchronized (SocketUtils.class) {
                instance = new SocketUtils();
            }
        }
        return instance;
    }

    public boolean isConnected() {
        if (socket != null) {
            try {
                socket.sendUrgentData(0);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
                return true;
            } catch (Exception se) {
                return false;
            }
        }
        return false;
    }

    public void startSocket() {
        // 初始化线程池
        mThreadPool = Executors.newCachedThreadPool();

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建Socket对象 & 指定服务端的IP 及 端口号
                    InetAddress addr = InetAddress.getByName(Urls.IP_);
                    socket = new Socket(addr, Urls.POST_);
                    socket.setTcpNoDelay(true);

                    // 步骤1：创建输入流对象InputStream
                    is = socket.getInputStream();
                    int len = 0;
                    byte[] buffer = new byte[1024];
                    while ((len = is.read(buffer)) != -1) {
                        String result = new String(buffer, 0, len);
                        response = result;
                        // 步骤4:通知主线程,将接收的消息显示到界面
                        Message msg = Message.obtain();
                        msg.what = 0;
                        mMainHandler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 主 变量
     */
    // 主线程Handler
    // 用于将从服务器获取的消息显示出来
    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Logger.e("onResponse===" + response);
                    String[] content = response.split("\\*");
                    if (response == null || response.length() == 0 || response.contains("ping")) {
                        return;
                    }
                    List<String> results = new ArrayList<>();
                    for (int i = 0; i < content.length; i++) {
                        response = content[i];
                        results.add(response);
                    }
                    for (int i = 0; i < results.size(); i++) {
                        ChatSocketBean socketBean = JsonParseUtil.parseString(results.get(i), ChatSocketBean.class);
                        if ("init".equals(socketBean.getType())) {
                            client_id = socketBean.getClient_id();
                            request();
                        }
                        if (onBaseSocketResult != null) {
                            onBaseSocketResult.putSocketResult(results.get(i));
                        }
                        if (onMainSocketResult != null) {
                            onMainSocketResult.putSocketResult(results.get(i));
                        }
                        if (onGetSocketResult != null) {
                            onGetSocketResult.putSocketResult(results.get(i));
                        }
                    }
                    break;
            }
        }
    };

    public void stopSocket() {
        try {
            if (is != null && socket != null) {
                is.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void request() {
        Log.e("TAG",LoginStatus.getUid());
        HttpManager.getInstance().PlayNetCode(HttpCode.BIND_CLIENT_ID, new RBindClientID(LoginStatus.getUid(),
                client_id, PhoneUtils.getIMEI())).request(new ApiCallBack() {
            @Override
            public void onFinish() {

            }

            @Override
            public void Message(int code, String message) {
                // code 1 下线  2 正常 0 参数原因  3被禁用
            }

            @Override
            public void onSuccess(Object o, String msg) {

            }
        });
    }


//    public void removeGroup(String group) {
//        if (!LoginStatus.isLogin(MyApp.getInstance())) {
//            return;
//        }
//        RequestParams params = new RequestParams(Urls.REMOVE_CLIENT);
//        Map<String, String> paramsMap = new HashMap<String, String>();
//        paramsMap.put("group", group);
//        paramsMap.put("client_id", client_id);
//        params.addBodyParameter("group", group);
//        params.addBodyParameter("client_id", client_id);
//        params.addBodyParameter("time", StringUtil.getTime());
//        String sign = SPCommonUtils.signParameter(paramsMap, StringUtil.getTime(), "ycld");
//        params.addBodyParameter("sign", sign);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                LoggerUtil.e("TAG", ex.toString());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//                LoggerUtil.e("TAG", "onFinished");
//            }
//        });
//    }

//    public void removeGetSocket(String group) {
//        onGetSocketResult = null;
//        if (!TextUtils.isEmpty(group)) {
//            //移出分组
//            removeGroup(group);
//        }
//    }

    public void removeGetSocket() {
        onGetSocketResult = null;
    }

    public void removeBaseSocket() {
        onBaseSocketResult = null;
    }
}
