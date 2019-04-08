package com.sence.utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.sence.net.Urls;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private String type;
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

//    public void startSocket() {
//        // 初始化线程池
//        mThreadPool = Executors.newCachedThreadPool();
//
//        mThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    // 创建Socket对象 & 指定服务端的IP 及 端口号
//                    InetAddress addr = InetAddress.getByName(Urls.IP_);
//                    socket = new Socket(addr, Urls.POST_);
//                    socket.setTcpNoDelay(true);
//
//                    // 步骤1：创建输入流对象InputStream
//                    is = socket.getInputStream();
//                    int len = 0;
//                    byte[] buffer = new byte[1024];
//                    while ((len = is.read(buffer)) != -1) {
//                        String result = new String(buffer, 0, len);
//                        response = result;
//                        // 步骤4:通知主线程,将接收的消息显示到界面
//                        Message msg = Message.obtain();
//                        msg.what = 0;
//                        mMainHandler.sendMessage(msg);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//    }

    /**
     * 主 变量
     */
    // 主线程Handler
    // 用于将从服务器获取的消息显示出来
//    private Handler mMainHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    LoggerUtil.e("onResponse", response + (onMainSocketResult == null) + "=====" + (onGetSocketResult == null) + "======" + !response.substring(0, 1).equals("{") + "===" + response.split(ConstantUtils.split).length);
//                    String[] content = response.split(ConstantUtils.split);
//                    if (response == null || response.length() == 0 || response.contains("ping") || !response.substring(0, 1).equals("{")) {
//                        return;
//                    }
//                    List<String> results = new ArrayList<>();
//                    for (int i = 0; i < content.length; i++) {
//                        LoggerUtil.e("socket=============" + content[i]);
//                        if (content.length == 1) {
//                            response = content[0];
//                            results.add(response);
//                        } else if (content.length == 2) {
//                            if (i == 0) {
//                                response = content[i] + "}";
//                            } else if (i == 1) {
//                                response = "{" + content[i];
//                            }
//                            results.add(response);
//                        } else {
//                            if (i == 0) {
//                                response = content[i] + "}";
//                            } else if (i == content.length - 1) {
//                                response = "{" + content[i];
//                            } else {
//                                response = "{" + content[i] + "}";
//                            }
//                            results.add(response);
//
//                        }
//                    }
//                    for (int i = 0; i < results.size(); i++) {
//                        LoggerUtil.e("result_socket" + results.get(i));
//                        ChatSocketBean socketBean = JsonParseUtil.parseString(results.get(i), ChatSocketBean.class);
//                        if (socketBean.getType().equals("init")) {
//                            client_id = socketBean.getClient_id();
//                            request(type);
//                        }
//                        if (onBaseSocketResult != null) {
//                            onBaseSocketResult.putSocketResult(results.get(i));
//                        }
//                        if (onMainSocketResult != null) {
//                            LoggerUtil.e("onMainSocketResult", results.get(i) + onMainSocketResult);
//                            onMainSocketResult.putSocketResult(results.get(i));
//                        }
//                        if (onGetSocketResult != null) {
//                            LoggerUtil.e("onGetSocketResult", results.get(i) + onGetSocketResult);
//                            onGetSocketResult.putSocketResult(results.get(i));
//                        }
//                    }
//                    break;
//            }
//        }
//    };

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

//    public void request(String type) {
//        if (!LoginStatus.isLogin(MyApp.getInstance())) {
//            return;
//        }
//        RequestParams params = new RequestParams(Urls.BIND_SOCKET);
//        params.addBodyParameter("uid", SpUtils.getString(MyApp.getInstance(), "uid", ""));
//        params.addBodyParameter("client_id", client_id);
//        params.addBodyParameter("device", SystemUtil.getIMEI(MyApp.getInstance()));
//        if (!TextUtils.isEmpty(type)) {
//            params.addBodyParameter("type", type);
//        }
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                LoggerUtil.e("TAG", "Socket+++" + result.toString());
//                try {
//                    JSONObject obj = new JSONObject(result);
//                    int status = obj.optInt("status");
//                    String msg = obj.optString("msg");
//                    // status 1 下线  2 正常 0 参数原因  3被禁用
//                    if (status == 1) {
//
//                    } else if (status == 3) {//3被禁用
//                        onMainOnlySocketResult.setSocketStatus(msg);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                LoggerUtil.e("TAG+socket+ex.toString()+", ex.toString());
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

//    public void closeSocket() {
//        RequestParams params = new RequestParams(Urls.UNBIND_SOCKET);
//        Map<String, String> paramsMap = new HashMap<String, String>();
//        paramsMap.put("client_id", client_id);
//        params.addBodyParameter("client_id", client_id);
//        params.addBodyParameter("time", StringUtil.getTime());
//        String sign = SPCommonUtils.signParameter(paramsMap, StringUtil.getTime(), "ycld");
//        params.addBodyParameter("sign", sign);
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                LoggerUtil.e("TAG", "Socket+++" + result.toString());
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
