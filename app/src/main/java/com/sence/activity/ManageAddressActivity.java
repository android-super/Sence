package com.sence.activity;

import android.os.Bundle;
import android.view.View;

import com.sence.R;
import com.sence.adapter.ManageAddressAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ManageAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ManageAddressAdapter manageAddressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageaddress);
        recyclerView = findViewById(R.id.rlv_address_manageaddress);
        findViewById(R.id.iv_back_manageaddress).setOnClickListener(this);
        manageAddressAdapter = new ManageAddressAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(manageAddressAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_manageaddress:
                finish();
                break;
        }
    }
}
