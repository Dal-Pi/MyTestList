package com.kania.mytestlist.DialogFragmentTest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.kania.mytestlist.MainActivity;

/**
 * Created by user on 2016-07-13.
 */
public class MyDialogFragment extends DialogFragment {
    public static final String KEY_NUM_ARG = "num_arg";
    public static final String KEY_NUM_SAVE = "num_save";

    public interface Callback {
        void onSelect(int num);
    }

    private int num_arg;
    private int num_save;

    private Callback mCallback;


    public static MyDialogFragment newInstance(int arg, Callback callback) {
        MyDialogFragment dialog = new MyDialogFragment();
        dialog.setCallback(callback);
        Bundle args = new Bundle();
        args.putInt(KEY_NUM_ARG, arg);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(MainActivity.APP_TAG, "MyDialogFragment.onCreate()");

        if (savedInstanceState != null) {
            Log.d(MainActivity.APP_TAG, "restore state");
            num_save = savedInstanceState.getInt(KEY_NUM_SAVE, -1);
        } else {
            Log.d(MainActivity.APP_TAG, "normal creation state");
            num_save = 0;
        }
        Bundle args = getArguments();
        num_arg = args.getInt(KEY_NUM_ARG, -1);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Test Alert Dialog in Fragment")
                .setMessage("arg = " + num_arg + "\nsave = " + num_save)
                .setPositiveButton("positive button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        num_arg++;
                        mCallback.onSelect(num_arg);
                    }
                })
                .setNegativeButton("negative button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        num_arg--;
                        mCallback.onSelect(num_arg);
                    }
                })
                .setNeutralButton("neutral button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onSelect(num_arg);
                    }
                });
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCallback == null) {
            dismiss();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUM_SAVE, 8);
    }

    private void setCallback(Callback callback) {
        mCallback = callback;
    }
}
