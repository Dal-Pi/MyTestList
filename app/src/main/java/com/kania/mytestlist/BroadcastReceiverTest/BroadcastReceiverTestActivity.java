package com.kania.mytestlist.BroadcastReceiverTest;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kania.mytestlist.R;

public class BroadcastReceiverTestActivity extends AppCompatActivity {

    public static final String TAG = "BRTest";

    private TextView mTextLogView;
    private String mTextLogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver_test);

        getSupportActionBar().setTitle(this.getClass().getSimpleName());

        mTextLogView = (TextView)findViewById(R.id.broadcast_receiver_text_log);
        mTextLogText = "";

        Intent initIntent = getIntent();
        handleBroadcastIntent(initIntent);
    }

    private void handleBroadcastIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        int fromBr = intent.getIntExtra(Constants.KEY_FROM_BR, -1);
        if (fromBr == -1) {
            return;
        }
        String action = intent.getAction();
        addActionLog(action);

        //TODO handle each BR here
        if (Constants.ACTION_CHECK_PERMISSIONS.equalsIgnoreCase(action)) {
            int requestCode = intent.getIntExtra(Constants.EXTRA_REQUEST_CODE, -1);
            if (requestCode == Constants.REQUEST_CODE_ALL_PERMISSION) {
                checkPermission();
            }
        }
    }

    private void addActionLog(String actionString) {
        mTextLogText += actionString + "\n";
        if (mTextLogView != null) {
            mTextLogView.setText(mTextLogText);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent()");
        super.onNewIntent(intent);
        handleBroadcastIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void checkPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            sendReply(true);
        }
    }

    private void sendReply(boolean isGranted) {
        Intent replyIntent = new Intent(Constants.ACTION_AVAIL_PERMISSION);
        replyIntent.putExtra(Constants.EXTRA_REQUEST_CODE, Constants.REQUEST_CODE_ALL_PERMISSION);
        replyIntent.putExtra(Constants.EXTRA_REQUEST_RESULT,
                isGranted ? Constants.PERMISSION_AVAIL : Constants.PERMISSION_NOT_AVAIL);
        sendBroadcast(replyIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendReply(true);
            } else {
                sendReply(false);
            }
        }
    }
}
