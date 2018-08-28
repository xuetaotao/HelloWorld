package com.example.xuetaotao.helloworld.utils;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AMapUtils {

    private static LatLng latLng;

    //获取显示地图的对象
    private static AMap aMap;
    private static AMapLocationClient aMapLocationClient = null;

    //获取地址详情
    public interface MyLocationResult{

        void locationResult(AMapLocation aMapLocation);
        void locationError(AMapLocation aMapLocation);
    }

    public static void initMap(MapView mapView){

        //初始化 AMap 对象
        aMap = mapView.getMap();

        //实现定位蓝点 默认是蓝点 也可以自定义图片
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.radiusFillColor(android.R.color.transparent);
        myLocationStyle.strokeColor(android.R.color.transparent);
        aMap.setMyLocationStyle(myLocationStyle);

        //设置AMap的UI界面
        UiSettings settings = aMap.getUiSettings();
        //是否显示定位按钮
        settings.setMyLocationButtonEnabled(true);
        //是否显示地图默认的缩放按钮
        settings.setZoomControlsEnabled(false);

        //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        //设置为true表示启动显示定位蓝点(这里会进行初次定位)，false表示隐藏定位蓝点并不进行定位，默认是false
        aMap.setMyLocationEnabled(true);
    }

    //初始化定位
    public static void initLocation(Context context){

        aMapLocationClient = new AMapLocationClient(context);
        initClientOption();
    }

    //配置参数并启动定位
    private static void initClientOption(){
        AMapLocationClientOption option = new AMapLocationClientOption();
        //选择定位模式
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置单次定位
        option.setOnceLocation(false);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms
        option.setInterval(2000);
        //设置是否返回地址信息（默认返回地址信息）
        option.setNeedAddress(true);
        //设置是否返回地址信息（默认返回地址信息）
        option.setMockEnable(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        option.setWifiScan(true);
        //给定位客户端对象设置定位参数
        aMapLocationClient.setLocationOption(option);
        //启动定位
        aMapLocationClient.startLocation();
    }

    //声明定位回调监听器,获取定位结果
    public static void getCurrentLocation(final MyLocationResult result){

        aMapLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null && result != null) {
                    if (aMapLocation.getErrorCode() == 0){
                        //定位成功回调信息，设置相关消息
                        aMapLocation.getLocationType(); //获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                        aMapLocation.getLatitude(); //获取纬度
                        aMapLocation.getLongitude();    //获取经度
                        aMapLocation.getAccuracy(); //获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);    //定位时间
                        aMapLocation.getAddress();  //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        aMapLocation.getCountry();  //国家信息
                        aMapLocation.getProvince(); //省信息
                        aMapLocation.getCity(); //城市信息
                        aMapLocation.getDistrict(); //城区信息
                        aMapLocation.getStreet();   //街道信息
                        aMapLocation.getStreetNum();    //街道门牌号信息
                        aMapLocation.getCityCode(); //城市编码
                        aMapLocation.getAdCode();   //地区编码
                        latLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());

                        //获取定位信息
                        StringBuilder buffer = new StringBuilder();
                        buffer.append( aMapLocation.getProvince() + "" + aMapLocation.getCity() + ""  + aMapLocation.getDistrict() + "" + aMapLocation.getStreet());
//                        Log.e("AMapUtils","location buffer:" + buffer);
                        result.locationResult(aMapLocation);
                    }
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                    result.locationError(aMapLocation);
                }
            }
        });
    }

    //将地图移动到定位点(只自定义刷新按钮时调用)
    public static void moveLocation(){

        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
    }

    //停止定位
    public static void stopLocation(){

        //停止定位后，本地定位服务并不会被销毁
        aMapLocationClient.stopLocation();
        //销毁定位客户端，同时销毁本地定位服务
        aMapLocationClient.onDestroy();
    }
}
