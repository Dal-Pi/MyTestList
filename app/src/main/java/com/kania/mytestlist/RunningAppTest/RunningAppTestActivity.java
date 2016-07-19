package com.kania.mytestlist.RunningAppTest;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kania.mytestlist.R;

import java.util.List;

public class RunningAppTestActivity extends AppCompatActivity {

    public final int MAX_APPS = 10;

    private Button mBtnGetApps;
    private TextView mTextTasks;
    private TextView mTextProcesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_app_test);

        mBtnGetApps = (Button)findViewById(R.id.running_app_btn_getapp);
        mBtnGetApps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTextTasks.setText(getRunningTasks(MAX_APPS));
                mTextProcesses.setText(getRunningProcesses(MAX_APPS));
            }
        });

        mTextTasks = (TextView)findViewById(R.id.running_app_text_list_task);
        mTextProcesses = (TextView)findViewById(R.id.running_app_text_list_process);
    }

    private String getRunningTasks(int max) {
        String ret = "";

        ActivityManager activityManager =
                (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = activityManager.getRunningTasks(max);

        for (ActivityManager.RunningTaskInfo ti : taskList) {
            ret += ti.topActivity.getPackageName() + "\n";
        }

        return ret;
    }

    private String getRunningProcesses(int max) {
        String ret = "";

        ActivityManager activityManager =
                (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processList =
                activityManager.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo pi : processList) {
            ret += pi.processName + "\n";
        }

        return ret;
    }
}
