package com.kania.mytestlist.ThemeColorTest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

/**
 * Created by user on 2016-07-13.
 */
public class MyDialogFragment extends DialogFragment {

    private AlertDialog mDialog;
    public static MyDialogFragment newInstance() {
        MyDialogFragment dialog = new MyDialogFragment();
//        Bundle args = new Bundle();
//        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Test Alert Dialog")
                .setPositiveButton("positive button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("negative button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("neutral button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        mDialog = builder.create();
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnPositive = mDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnPositive.setTextColor(Color.parseColor("#5CD1E5"));
                Button btnNegative = mDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNegative.setTextColor(Color.parseColor("#FFB2D9"));
            }
        });

        return mDialog;
    }
}
