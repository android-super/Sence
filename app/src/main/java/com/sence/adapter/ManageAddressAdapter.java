package com.sence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.activity.AddAddressActivity;
import com.sence.activity.ManageAddressActivity;
import com.sence.bean.request.RAddressDeleteBean;
import com.sence.bean.response.PManageAddressBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;
import com.sence.utils.LoginStatus;
import com.sence.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.ViewHolder> {
    private Context context;
    private List<PManageAddressBean> list = new ArrayList<>();
    private View view;
    private String type= "";
    public ManageAddressAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PManageAddressBean> list,String type){
        this.list = list;
        this.type=type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ManageAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.rv_item_manageaddress,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ManageAddressAdapter.ViewHolder holder, final int position) {
        holder.mName.setText(list.get(position).getUsername());
        holder.mAddress.setText(list.get(position).getArea()+list.get(position).getAddress());
        holder.mPhone.setText(list.get(position).getPhone());
        holder.mEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAddressActivity.class);
                intent.putExtra("bean",list.get(position));
                context.startActivity(intent);
            }
        });

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance().PlayNetCode(HttpCode.ADDRESS_DELETE, new RAddressDeleteBean(LoginStatus.getUid(),list.get(position).getId())).request(new ApiCallBack<String>() {
                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void Message(int code, String message) {

                    }

                    @Override
                    public void onSuccess(String o, String msg) {
                        Logger.e("msg==========" + msg);
                        if(msg.equals("删除地址成功")){
                            listener.delete(position);
                        }
                    }
                });
            }
        });
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("shop".equals(type)){
                    SharedPreferencesUtil.getInstance().putString("name_address", list.get(position).getUsername());
                    SharedPreferencesUtil.getInstance().putString("id_address", list.get(position).getId());
                    SharedPreferencesUtil.getInstance().putString("address", list.get(position).getAddress());
                    SharedPreferencesUtil.getInstance().putString("phone_address", list.get(position).getPhone());
                    SharedPreferencesUtil.getInstance().putBoolean("ischeck_address", true);
                    ((ManageAddressActivity)context).finish();
                }else if("shopd".equals(type)){
                    SharedPreferencesUtil.getInstance().putString("name_address", list.get(position).getUsername());
                    SharedPreferencesUtil.getInstance().putString("id_address", list.get(position).getId());
                    SharedPreferencesUtil.getInstance().putString("address", list.get(position).getAddress());
                    SharedPreferencesUtil.getInstance().putString("phone_address", list.get(position).getPhone());
                    SharedPreferencesUtil.getInstance().putBoolean("ischeck_shopaddress", true);
                    ((ManageAddressActivity)context).finish();

                }else if("order".equals(type)){
                    SharedPreferencesUtil.getInstance().putString("name_address", list.get(position).getUsername());
                    SharedPreferencesUtil.getInstance().putString("id_address", list.get(position).getId());
                    SharedPreferencesUtil.getInstance().putString("address", list.get(position).getAddress());
                    SharedPreferencesUtil.getInstance().putString("phone_address", list.get(position).getPhone());
                    SharedPreferencesUtil.getInstance().putBoolean("ischeck_orderaddress", true);
                    ((ManageAddressActivity)context).finish();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mEditor,mDelete;
        private TextView mName,mPhone,mAddress;
        private LinearLayout mLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_name_manageaddress);
            mPhone = itemView.findViewById(R.id.tv_phone_manageaddress);
            mAddress = itemView.findViewById(R.id.tv_address_manageaddress);
            mEditor = itemView.findViewById(R.id.ll_editor_manageaddress);
            mDelete = itemView.findViewById(R.id.ll_delete_manageaddress);
            mLayout = itemView.findViewById(R.id.show_content_view);
        }
    }
    private DeleteAddressListener listener;

    public void result( DeleteAddressListener listener){
        this.listener=listener;
    }

    public interface DeleteAddressListener {
        void delete(int i);
    }

}
