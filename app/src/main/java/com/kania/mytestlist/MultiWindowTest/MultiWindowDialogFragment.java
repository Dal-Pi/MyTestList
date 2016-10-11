package com.kania.mytestlist.MultiWindowTest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.kania.mytestlist.MainActivity;

/**
 * Created by user on 2016-07-13.
 */
public class MultiWindowDialogFragment extends DialogFragment {

    public static final int BTN_POSITIVE = 1;
    public static final int BTN_NEUTRAL = 0;
    public static final int BTN_NEGATIVE = -1;

    public interface Callback {
        void onSelect(int num);
        void onMultiWindowChanged();
    }

    private String mMessage;
    private Callback mCallback;

    private boolean mIsCommitable;
    private boolean mIsNeedRecreateByMultiWindow;

    public static MultiWindowDialogFragment newInstance(String msg, Callback callback) {
        MultiWindowDialogFragment dialog = new MultiWindowDialogFragment();
        dialog.setCallback(callback);
        dialog.setMessage(msg);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(MainActivity.APP_TAG, "onCreate() - " + hashCode());
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(MainActivity.APP_TAG, "onCreateDialog() - " + hashCode());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Dialog")
                .setMessage(mMessage)
                .setPositiveButton("positive button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onSelect(BTN_POSITIVE);
                    }
                })
                .setNegativeButton("negative button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onSelect(BTN_NEUTRAL);
                    }
                })
                .setNeutralButton("neutral button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onSelect(BTN_NEGATIVE);
                    }
                });
        return builder.create();
    }

    @Override
    public void onResume() {
        Log.d(MainActivity.APP_TAG, "onResume() - " + hashCode());
        super.onResume();
        mIsCommitable = true;
        if (mCallback == null) {
            dismiss();
        }

        if (mIsNeedRecreateByMultiWindow) {
            Log.d(MainActivity.APP_TAG, "re-create() by MultiWindow flag - " + hashCode());
            mCallback.onMultiWindowChanged();
            mIsNeedRecreateByMultiWindow = false;
        }
    }

    @Override
    public void onPause() {
        Log.d(MainActivity.APP_TAG, "onPause() - " + hashCode());
        super.onPause();
        mIsCommitable = false;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        Log.d(MainActivity.APP_TAG, "show() - " + hashCode());
        super.show(manager, tag);
    }

    @Override
    public void dismiss() {
        Log.d(MainActivity.APP_TAG, "dismiss() - " + hashCode());
        super.dismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(MainActivity.APP_TAG, "onSaveInstanceState() - " + hashCode());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        Log.d(MainActivity.APP_TAG, "onMultiWindowModeChanged() - " + hashCode());
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        if (mIsCommitable) {
            mCallback.onMultiWindowChanged();
        } else {
            Log.d(MainActivity.APP_TAG, "pending to re-create() " + hashCode());
            mIsNeedRecreateByMultiWindow = true;
        }
    }

    private void setMessage(String msg) {
        mMessage = msg;
    }

    private void setCallback(Callback callback) {
        mCallback = callback;
    }
}
