package com.example.xuetaotao.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

//监听定位和定位变化
public class GaoDeMapActivity extends AppCompatActivity implements LocationSource, AMapLocationListener {

    //显示地图需要的变量
    private MapView mapView;    //地图控件
    private AMap aMap;  //地图对象
    private Button button;

    //定位需要的声明
    private OnLocationChangedListener mListener = null; //定位监听器
    private AMapLocationClient mLocationClient = null;  //定位发起端
    private AMapLocationClientOption mLocationOption = null;    //定位参数

    private LatLng latLng;

    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gao_de_map);

        button = (Button) findViewById(R.id.mButton);

//        String sha1 = sHA1(getApplicationContext());
//        Log.d("AmapError", "location Error, ErrCode:" + sha1);
//        Toast.makeText(getApplicationContext(), "SHA1的值为 ：" + sha1, Toast.LENGTH_LONG).show();

        //显示地图
        mapView = (MapView) findViewById(R.id.mMapview);
        //必须要写
        mapView.onCreate(savedInstanceState);
       //获取地图对象
        aMap = mapView.getMap();

        //设置显示定位按钮，并且可以点击
        UiSettings settings = aMap.getUiSettings();
        //设置定位监听
        aMap.setLocationSource(this);
        //TODO 移除高德地图的Logo
        //是否显示定位按钮
        settings.setMyLocationButtonEnabled(false);
        //是否可以触发定位并显示定位层
        aMap.setMyLocationEnabled(true);
        //定位的小图标 默认是蓝点 也可以自定义一团火，其实就是一张图片
        MyLocationStyle myLocationStyle = new MyLocationStyle();
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.ovalcopy3));
        myLocationStyle.radiusFillColor(android.R.color.transparent);
        myLocationStyle.strokeColor(android.R.color.transparent);
        aMap.setMyLocationStyle(myLocationStyle);
        //开始定位
        initLoc();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
            }
        });
    }

    //定位
    private void initLoc(){
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次，默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiScan(true);
//        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置，默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔，单位为毫秒，默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    //停止定位
    @Override
    public void deactivate() {
        mListener = null;
    }

    //定位回调函数
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

        if (aMapLocation != null){

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

                //如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
//                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
//                    aMap.addMarker(getMarkerOptions(aMapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + "" + aMapLocation.getProvince() + "" + aMapLocation.getCity() + "" + aMapLocation.getProvince() + "" + aMapLocation.getDistrict() + "" + aMapLocation.getStreet() + "" + aMapLocation.getStreetNum());
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_SHORT).show();
                    isFirstLoc = false;
                }
            } else {

                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(),"定位失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //自定义一个图钉，并且设置图标，当我们点击图钉时，显示设置的信息
    private MarkerOptions getMarkerOptions(AMapLocation aMapLocation){

        //设置图钉选项
        MarkerOptions options = new MarkerOptions();
        //图标
//        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ovalcopy3));
        //位置
        options.position(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
        StringBuffer buffer = new StringBuffer();
        buffer.append(aMapLocation.getCountry() + aMapLocation.getProvince() + "" + aMapLocation.getCity() + "" + aMapLocation.getDistrict() + "" + aMapLocation.getStreet() + "" + aMapLocation.getStreetNum());
        //标题
        options.title(buffer.toString());
        //子标题
        options.snippet("这里好火");
        //设置多少帧刷新一次图片资源
        options.period(60);
        return options;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}