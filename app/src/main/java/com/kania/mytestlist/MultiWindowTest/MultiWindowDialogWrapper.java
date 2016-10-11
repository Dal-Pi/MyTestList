package com.kania.mytestlist.MultiWindowTest;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

/**
 * Created by Seonghan Kim on 2016-10-11.
 */

public class MultiWindowDialogWrapper implements MultiWindowDialogFragment.Callback {
    public interface Callback {
        void onSelect(int num);
    }

    private Activity mActivity;

    private MultiWindowDialogFragment mDialog;

    private Callback mCallback;

    public static MultiWindowDialogWrapper newInstance(Activity activity, MultiWindowDialogWrapper.Callback callback) {
        MultiWindowDialogWrapper dialogWrapper = new MultiWindowDialogWrapper();
        dialogWrapper.setActivity(activity);
        dialogWrapper.setCallback(callback);
        return dialogWrapper;
    }

    public void show(FragmentManager fragmentManager) {
        mDialog = getDialog();
        mDialog.show(fragmentManager, MultiWindowDialogFragment.class.getCanonicalName());
    }

    private MultiWindowDialogFragment getDialog() {
        MultiWindowDialogFragment dialog;
        if (mActivity.isInMultiWindowMode()) {
            dialog = MultiWindowDialogFragment.newInstance("Multi-Window Dialog", this);
        } else {
            dialog = MultiWindowDialogFragment.newInstance("Normal Dialog", this);
        }
        return dialog;
    }

    private void setActivity(Activity activity) {
        mActivity = activity;
    }
    private void setCallback(MultiWindowDialogWrapper.Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onSelect(int num) {
        mCallback.onSelect(num);
    }

    @Override
    public void onMultiWindowChanged() {
        mDialog.dismiss();
        show(mActivity.getFragmentManager());
    }
}
