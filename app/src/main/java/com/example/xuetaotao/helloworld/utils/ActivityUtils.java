package com.example.xuetaotao.helloworld.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {

    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    /**
     * isFinishing() 可用来判断Activity是否处于活跃状态（false）还是等待回收状态（true）。
     */
    public static void finishAll(){
        for (Activity activity:activities){
            if (! activity.isFinishing()){
                activity.finish();
            }
        }
        //杀掉当前程序的进程(加上后Activity 连 onDestroy 都不会执行，直接被杀死)
//        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
