package com.kania.mytestlist.HandlerTest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kania.mytestlist.R;

import java.lang.ref.WeakReference;

public class HandlerTestActivity extends AppCompatActivity {

    private TextView mTextCount;
    private Button mBtnStart;

    private int mCount;

    //bad example
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (mCount <= 10) {
//                mTextCount.setText("" + mCount);
//                mCount++;
//                mHandler.sendEmptyMessageDelayed(0, 1000);
//            } else {
//                mTextCount.setText("end");
//            }
//            super.handleMessage(msg);
//        }
//    };

    MyWeakReferenceHandler mWeakReferenceHandler = new MyWeakReferenceHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        mTextCount = (TextView)findViewById(R.id.handler_textCount);
        mBtnStart = (Button)findViewById(R.id.handler_btnStart);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    private void start() {
        mCount = 0;
        mWeakReferenceHandler.sendEmptyMessage(0);
//        mHandler.sendEmptyMessage(0);
    }

    private void handleMessage() {
        if (mCount <= 10) {
            mTextCount.setText("" + mCount);
            mCount++;
            mWeakReferenceHandler.sendEmptyMessageDelayed(0, 1000);
        } else {
            mTextCount.setText("end");
        }
    }

    public class MyWeakReferenceHandler extends Handler {

        private final WeakReference<HandlerTestActivity> mHandlerActivity;

        public MyWeakReferenceHandler(HandlerTestActivity activity) {
            mHandlerActivity = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mHandlerActivity.get().handleMessage();
        }
    }
}
