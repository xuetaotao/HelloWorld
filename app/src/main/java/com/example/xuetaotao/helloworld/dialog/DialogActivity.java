package com.example.xuetaotao.helloworld.dialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.base.BaseTitleActivity;

import java.util.Calendar;

import static android.app.ProgressDialog.STYLE_HORIZONTAL;
import static android.app.ProgressDialog.STYLE_SPINNER;


public class DialogActivity extends BaseTitleActivity implements View.OnClickListener {

    private int choice = -1;

    public static void newInstance(Context context){

        Intent intent = new Intent(context, DialogActivity.class);
//        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button btnAlertdialog = (Button) findViewById(R.id.btn_alertdialog);
        btnAlertdialog.setOnClickListener(this);
        Button btnAlertdialog2 = (Button) findViewById(R.id.btn_alertdialog2);
        btnAlertdialog2.setOnClickListener(this);
        Button btnSingleDialog = (Button) findViewById(R.id.btn_single_dialog);
        btnSingleDialog.setOnClickListener(this);
        Button btnMultiDialog = (Button) findViewById(R.id.btn_multi_dialog);
        btnMultiDialog.setOnClickListener(this);
        Button btnProgressDialog = (Button) findViewById(R.id.btn_progress_dialog);
        btnProgressDialog.setOnClickListener(this);
        Button btnProgressDialog2 = (Button) findViewById(R.id.btn_progress_dialog2);
        btnProgressDialog2.setOnClickListener(this);
        Button btnEditDialog = (Button) findViewById(R.id.btn_edit_dialog);
        btnEditDialog.setOnClickListener(this);
        Button btnCustomDialog = (Button) findViewById(R.id.btn_custom_dialog);
        btnCustomDialog.setOnClickListener(this);
        Button btnDateDialog = (Button) findViewById(R.id.btn_date_dialog);
        btnDateDialog.setOnClickListener(this);
        Button btnTimeDialog = (Button) findViewById(R.id.btn_time_dialog);
        btnTimeDialog.setOnClickListener(this);
        Button btnDateTimeDialog = (Button) findViewById(R.id.btn_date_time_dialog);
        btnDateTimeDialog.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            /**
             * 普通的 AlertDialog
             */
            case R.id.btn_alertdialog:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setTitle("普通 AlertDialog");
                dialog.setMessage("Dialog对话框之：\n AlertDialog");
                dialog.setCancelable(false);    //设置是否可以通过点击对话框外区域或者返回按键关闭对话框
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });

                //3个按钮
//                dialog.setNeutralButton("等待", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(DialogActivity.this, "等待", Toast.LENGTH_SHORT).show();
//                    }
//                });
                dialog.show();
                break;
            /**
             * 列表的 AlertDialog
             */
            case R.id.btn_alertdialog2:
                final String[] items = { "列表1", "列表2", "列表3" };
                AlertDialog.Builder listDialog = new AlertDialog.Builder(this);
                listDialog.setIcon(R.mipmap.ic_launcher_round);
                listDialog.setTitle("列表 AlertDialog");
                listDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //which下标从0开始, 代表当前选择的Item下标
                        Toast.makeText(DialogActivity.this, "你点击了：" + items[which], Toast.LENGTH_SHORT).show();
                    }
                });
                listDialog.show();
                break;
            /**
             * 单选 AlertDialog
             * 备注：匿名类可以访问外部类的所有成员，包裹该匿名类的方法中的所有final类型的局部变量。
             *      此处的 choice 变量若定为局部变量，则必须声明为final类型，但是这样就不能完成匿名类内部赋值，故声明为外部属性
             */
            case R.id.btn_single_dialog:
                final String[] item = { "单选1", "单选2", "单选3" };
                AlertDialog.Builder singleDialog = new AlertDialog.Builder(this);
                singleDialog.setIcon(R.mipmap.ic_launcher_round);
                singleDialog.setTitle("单选 AlertDialog");
                singleDialog.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choice = which;
                    }
                });
                singleDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(DialogActivity.this, "你选择了：" + item[choice], Toast.LENGTH_SHORT).show();
                    }
                });
               singleDialog.show();
                break;
            /**
             * 多选 AlertDialog
             * 备注：Boolean 是boolean 的实例化对象类，和Integer对应int一样
             */
            case R.id.btn_multi_dialog:
                final String[] multiItem = {"多选1", "多选2", "多选3"};
                final boolean[] checkedItem = {false, false, false};   //基础不牢固,boolean写错成Boolean搞了半天
                AlertDialog.Builder multiDialog = new AlertDialog.Builder(this);
                multiDialog.setIcon(R.mipmap.ic_launcher_round);
                multiDialog.setTitle("多选 AlertDialog");
                multiDialog.setMultiChoiceItems(multiItem, checkedItem, new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                       checkedItem[which] = isChecked;
                    }
                });
                multiDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder str = new StringBuilder();
                        for (int i=0; i<checkedItem.length; i++ ){
                            if (checkedItem[i]){
                                str.append(multiItem[i]);
                            }
                        }
                        Toast.makeText(DialogActivity.this, "你选择了：" + str , Toast.LENGTH_SHORT).show();
                    }
                });
                multiDialog.show();
                break;
            /**
             * 等待 ProgressDialog
             * 可以用于下载等事件完成后，主动调用函数关闭该Dialog
             */
            case R.id.btn_progress_dialog:
                ProgressDialog waitDialog = new ProgressDialog(this);
                waitDialog.setIcon(R.mipmap.ic_launcher_round);
                waitDialog.setTitle("等待 ProgressDialog");
                waitDialog.setMessage("请稍等");
                waitDialog.setProgressStyle(STYLE_SPINNER);
                waitDialog.setCancelable(true);
                waitDialog.show();
                break;
            /**
             * 进度条 ProgressDialog
             * 可以在子线程中直接调用setProgress方法更新UI，这个方法内已经处理了子线程里调用的情况了
             */
            case R.id.btn_progress_dialog2:
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setIcon(R.mipmap.ic_launcher_round);
                progressDialog.setTitle("进度条 ProgressDialog");
                progressDialog.setMessage("正在加载，请稍后……");
                progressDialog.setIndeterminate(false); //设置是否进入模糊状态,可以设置为true看看
                progressDialog.setMax(80);
                progressDialog.setProgressStyle(STYLE_HORIZONTAL);
                progressDialog.show();
                progressDialog.setProgress(0); //设置进度值, 在显示对话后,才可以设置
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = 0;
                        while (progress < 80){
                            try {
                                Thread.sleep(100);
                                progress++;
                                progressDialog.setProgress(progress);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        progressDialog.dismiss();
                    }
                }).start();
                break;
            /**
             * 编辑 AlertDialog
             * @setView 方法装入了一个EditText
             * 该方法其实就是替换标题和下方按钮中间的那部分布局
             */
            case R.id.btn_edit_dialog:
                final EditText editText = new EditText(this);
                AlertDialog.Builder editDialog = new AlertDialog.Builder(this);
                editDialog.setIcon(R.mipmap.ic_launcher_round);
                editDialog.setTitle("编辑 AlertDialog").setView(editText);
                editDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "输入的内容为：" + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                editDialog.show();
                break;
            /**
             * 自定义 AlertDialog
             * 添加的自定义布局实际上修改的也只是标题和下方按钮中间的那部分布局，标题和按钮都还用Android原生的
             */
            case R.id.btn_custom_dialog:
                AlertDialog.Builder customDialog = new AlertDialog.Builder(this);
                customDialog.setIcon(R.mipmap.ic_launcher_round);
                //填充设置好的自定义布局
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom, null);
                Button growth = (Button) dialogView.findViewById(R.id.btn_growth);
                growth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogActivity.this, "好好学习，努力沉淀", Toast.LENGTH_SHORT).show();
                    }
                });
                customDialog.setTitle("自定义 AlertDialog");
                customDialog.setView(dialogView);
                customDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //该方法会返回一个AlertDialog对象，可以用来调用dismiss方法
                //AlertDialog alertDialog = new AlertDialog.Builder(this).create();或者通过该方式创建AlertDialog
                customDialog.show();
                break;
            /**
             * 日期 DatePickerDialog
             */
            case R.id.btn_date_dialog:
                //获取系统的当前日期
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(DialogActivity.this, year + "年" + (month+1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                    }
                }, year, month, dayOfMonth);
                dateDialog.show();
                break;
            /**
             * 时间 TimePickerDialog
             */
            case R.id.btn_time_dialog:
                //获取系统当前时间
                Calendar calendar1 = Calendar.getInstance();
                int hour = calendar1.get(Calendar.HOUR_OF_DAY);
                int minute = calendar1.get(Calendar.MINUTE);

                TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(DialogActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                    }
                }, hour, minute, true);
                timeDialog.show();
                break;
            /**
             * 日期时间对话框
             * 如果把时间对话框部分放在DatePickerDialog中会出现，timePickerDialog显示两次的情况
             */
            case R.id.btn_date_time_dialog:
                Calendar calendar2 = Calendar.getInstance();
                int year2 = calendar2.get(Calendar.YEAR);
                int month2 = calendar2.get(Calendar.MONTH);
                int dayOfMonth2 = calendar2.get(Calendar.DAY_OF_MONTH);
                int hour2 = calendar2.get(Calendar.HOUR_OF_DAY);
                int minute2 = calendar2.get(Calendar.MINUTE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(DialogActivity.this, year + "年" + (month+1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                    }
                }, year2, month2, dayOfMonth2);
                TimePickerDialog timePickerDialog = new TimePickerDialog(DialogActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(DialogActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                        Log.e("DialogActivity", hourOfDay + ":" + minute);
                    }
                }, hour2, minute2, true);
                timePickerDialog.show();
                datePickerDialog.show();
                break;
            default:
                break;
        }
    }
}
