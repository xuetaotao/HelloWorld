package com.example.xuetaotao.helloworld.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.base.BaseTitleActivity;
import com.example.xuetaotao.helloworld.demo.activitylife.ActivityLifeCycleTest;
import com.example.xuetaotao.helloworld.demo.fragment.SimpleFragmentActivity;
import com.example.xuetaotao.helloworld.demo.listview.ListViewActivity;
import com.example.xuetaotao.helloworld.demo.listview.SimpleListViewActivity;
import com.example.xuetaotao.helloworld.demo.recyclerview.RecyclerViewActivity;
import com.example.xuetaotao.helloworld.demo.wechat.WeChatActivity;
import com.example.xuetaotao.helloworld.utils.ActivityUtils;

/**
 * 该部分，主要用于练习《第一行代码》中的例子
 */
public class FirstCodeActivity extends BaseTitleActivity implements View.OnClickListener{

    private Button btnImplicitIntent4;

    public static void newInstance(Context context){
        Intent intent = new Intent(context, FirstCodeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("FirstCodeActivity", "~~~~~~~~FirstCodeActivity~~~~~~~~~" + this.toString() + "TaskId：" + getTaskId());
    }

    @Override
    public int getTitleText() {
        return R.string.first_code;
    }

    @Override
    public int getResourcesId() {
        return R.layout.activity_first_code;
    }

    @Override
    public void initView() {

        /**
         * 2.2.4  在活动中使用Toast
         */
        Button btnToast = (Button) findViewById(R.id.btn_toast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstCodeActivity.this, "You Click Button", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 2.2.6  销毁一个活动
         */
        Button btnFinish = (Button) findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.e("FirstCodeActivity", "-----FirstCodeActivity  finished-----");
            }
        });

        /**
         *2.3.2  使用隐式Intent启动一个Activity
         */
        Button btnImplicitIntent = (Button) findViewById(R.id.btn_implicit_intent);
        btnImplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.intent.ACTIONSTART");
                intent.addCategory("com.example.intent.CUSTOM_CATEGORY");
                startActivity(intent);
            }
        });

        /**
         *2.3.3  隐式Intent启动其他程序
         */
        Button btnImplicitIntent1 = (Button) findViewById(R.id.btn_implicit_intent1);
        btnImplicitIntent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);     //这里不需要在AndroidManifest中添加声明
//                intent.setData(Uri.parse("http://www.taobao.com"));
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        /**
         *2.3.3  隐式Intent启动自身活动响应http数据请求(未实现，有问题) TODO
         */
        Button btnImplicitIntent2 = (Button) findViewById(R.id.btn_implicit_intent2);
        btnImplicitIntent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("http://www.sina.com"));
                startActivity(intent);
            }
        });

        /**
         *2.3.4  向下一个活动传递数据
         */
        Button btnImplicitIntent3 = (Button) findViewById(R.id.btn_implicit_intent3);
        btnImplicitIntent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "This is some datas deliverd from last Activity";
                Intent intent = new Intent(FirstCodeActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });

        /**
         *2.3.5  返回数据给上一个活动
         */
        btnImplicitIntent4 = (Button) findViewById(R.id.btn_implicit_intent4);
        btnImplicitIntent4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstCodeActivity.this, ThirdActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        /**
         *2.4.4  体验活动的生命周期
         */
        Button btnActivityLife = (Button) findViewById(R.id.btn_activity_life);
        btnActivityLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLifeCycleTest.newsInstance(FirstCodeActivity.this);
            }
        });

        /**
         * 2.4.5  活动被回收了怎么办
         * 看不到效果，可以通过log看到onSaveInstanceState调用的，是在onStop前面的阶段，但是再进入应用程序是不会调用onCreate。
         * 即便再back一次，销毁当前活动，重新执行 onCreate ，但是还是无法进入 if(savedInstanceState != null)中
         */
        Button btnActivityRecycle = (Button) findViewById(R.id.btn_activity_recycle);
        btnActivityRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLifeCycleTest.newsInstance(FirstCodeActivity.this);
            }
        });

        /**
         *2.5.1  standard活动启动模式
         * 系统不会在乎这个活动是否已经在返回栈中存在，每次启动都会创建一个该活动的新实例
         * 每点击一次按钮，就会创建出一个新的 FirstCodeActivity 实例，则多需要点击一次 Back 键才能返回
         */
        Button btnActivityMode = (Button) findViewById(R.id.btn_activity_mode);
        btnActivityMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstCodeActivity.this, FirstCodeActivity.class);
                startActivity(intent);
            }
        });

        /**
         *2.6.2  随时随地退出程序
         */
        Button btnActivityFinish = (Button) findViewById(R.id.btn_activity_finish);
        btnActivityFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishAll();
            }
        });

        /**
         *3.2.3  EditText && ImageView
         */
        Button btnEditText = (Button) findViewById(R.id.btn_edit_text);
        final ImageView ivTest = (ImageView) findViewById(R.id.iv_test);
        final EditText etTest = (EditText) findViewById(R.id.et_test);
        btnEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etTest.getText().toString();
                Toast.makeText(FirstCodeActivity.this, data, Toast.LENGTH_SHORT).show();
                ivTest.setImageResource(R.mipmap.jyfr_icon_mpossh3x);
            }
        });

        /**
         * 3.2.5  ProgressBar
         */
        Button btnProgressbar = (Button) findViewById(R.id.btn_progressbar);
        final ProgressBar progressbar = (ProgressBar) findViewById(R.id.progressbar);
        btnProgressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressbar.getVisibility() == View.VISIBLE ){
                    progressbar.setVisibility(View.GONE);
                } else {
                    progressbar.setVisibility(View.VISIBLE);
                }
            }
        });

        /**
         *3.2.5  ProgressBar_horizon
         */
        Button btnProgressbarHorizon = (Button) findViewById(R.id.btn_progressbar_horizon);
        final ProgressBar progressbarHorizon = (ProgressBar) findViewById(R.id.progressbar_horizon);
        btnProgressbarHorizon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = progressbarHorizon.getProgress();
                progress += 10;
                progressbarHorizon.setProgress(progress);
            }
        });

        /**
         *3.5.1  ListView的简单用法
         */
        Button btnListview = (Button) findViewById(R.id.btn_listview);
        btnListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleListViewActivity.newsInstance(FirstCodeActivity.this);
            }
        });

        /**
         * 3.5.2  定制ListView的界面
         */
        Button btnCustomListview = (Button) findViewById(R.id.btn_custom_listview);
        btnCustomListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewActivity.newsInstance(FirstCodeActivity.this);
            }
        });

        /**
         *3.6  更强大的滚动控件RecyclerView
         */
        Button btnRecyclerview = (Button) findViewById(R.id.btn_recyclerview);
        btnRecyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewActivity.newInstance(FirstCodeActivity.this);
            }
        });

        /**
         * 3.7 编写界面的最佳实践
         */
        Button btnWechat = (Button) findViewById(R.id.btn_wechat);
        btnWechat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WeChatActivity.newInstance(FirstCodeActivity.this);
            }
        });

        /**
         * 4.2.1 碎片的简单用法
         */
        Button btnSimpleFragment = (Button) findViewById(R.id.btn_simple_fragment);
        btnSimpleFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SimpleFragmentActivity.newInstance(FirstCodeActivity.this);
            }
        });
    }

    //没有super调用父类的方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){

            case 1:
                if (resultCode == RESULT_OK){
                    String dataReturn = data.getStringExtra("data_return");
                    btnImplicitIntent4.setText(dataReturn);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            default:
                break;
        }
    }


    /**
     * 2.2.5  在活动中使用Menu(未实现)
     * 需要基于ActionBar来实现
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.add_item:
//                Toast.makeText(FirstCodeActivity.this, "Add", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.remove_item:
//                Toast.makeText(FirstCodeActivity.this, "Remove", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("FirstCodeActivity", "-----FirstCodeActivity  onDestroy-----");
    }
}
