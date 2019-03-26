package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sence.R;
import com.sence.bean.request.RAddressDeleteBean;
import com.sence.bean.response.PManageAddressBean;
import com.sence.net.HttpCode;
import com.sence.net.HttpManager;
import com.sence.net.manager.ApiCallBack;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.ViewHolder> {
    private Context context;
    private List<PManageAddressBean> list = new ArrayList<>();

    public ManageAddressAdapter(Context context){
        this.context = context;
    }
    public void setList(List<PManageAddressBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ManageAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_manageaddress,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ManageAddressAdapter.ViewHolder holder, final int position) {
//        holder.name.setText(list.get(position).getUsername());
//        holder.address.setText(list.get(position).getArea());
//        holder.phone.setText(list.get(position).getPhone());
        holder.mEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance().PlayNetCode(HttpCode.ADDRESS_DELETE, new RAddressDeleteBean("1",list.get(position).getId())).request(new ApiCallBack<String>() {
                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void Message(int code, String message) {
                        if(code==1){
                            listener.delete(position);
                        }
                    }

                    @Override
                    public void onSuccess(String o, String msg) {
                        Logger.e("msg==========" + msg);
                    }
                });
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

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_name_manageaddress);
            mPhone = itemView.findViewById(R.id.tv_phone_manageaddress);
            mAddress = itemView.findViewById(R.id.tv_address_manageaddress);
            mEditor = itemView.findViewById(R.id.ll_editor_manageaddress);
            mDelete = itemView.findViewById(R.id.ll_delete_manageaddress);
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
