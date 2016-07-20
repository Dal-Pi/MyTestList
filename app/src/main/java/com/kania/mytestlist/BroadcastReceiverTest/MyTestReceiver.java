package com.kania.mytestlist.BroadcastReceiverTest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MyTestReceiver extends BroadcastReceiver {

    public MyTestReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        String action = intent.getAction();
        Log.d(BroadcastReceiverTestActivity.TAG, "onReceive()! action = " + action);
        Toast.makeText(context, "received! action = " + action, Toast.LENGTH_SHORT).show();

        Intent activityIntent = new Intent(context, BroadcastReceiverTestActivity.class);
        activityIntent.setAction(action);

        if (Constants.ACTION_CHECK_PERMISSIONS.equalsIgnoreCase(action)) {
            activityIntent.putExtra(Constants.EXTRA_REQUEST_CODE,
                    intent.getIntExtra(Constants.EXTRA_REQUEST_CODE, -1));
        }

        activityIntent.putExtra(Constants.KEY_FROM_BR, Constants.FROM_BR);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
    }
}