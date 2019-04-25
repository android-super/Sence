package com.sence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.sence.R;
import com.sence.utils.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
/**
 * 地图
 */

public class MapActivity extends AppCompatActivity {


    private MapView mvMap;
    private AMap aMap;
    private String longitude,latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        longitude = intent.getStringExtra("longitude");
        latitude = intent.getStringExtra("latitude");

        mvMap = findViewById(R.id.mv_map);
//        在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mvMap.onCreate(savedInstanceState);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mvMap.getMap();
        }
        if(!TextUtils.isEmpty(longitude)){
            LatLng latLng = new LatLng(Double.parseDouble(longitude),Double.parseDouble(latitude));
            final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).snippet(""));
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mvMap.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mvMap.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mvMap.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mvMap.onSaveInstanceState(outState);
    }


}
