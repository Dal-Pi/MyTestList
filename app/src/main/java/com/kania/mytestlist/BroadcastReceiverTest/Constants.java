package com.kania.mytestlist.BroadcastReceiverTest;

/**
 * Created by user on 2016-07-21.
 */
public class Constants {

    public static final String ACTION_CHECK_PERMISSIONS =
            "com.lge.exchange.action.CHECK_PERMISSIONS";
    public static final String ACTION_AVAIL_PERMISSION =
            "com.lge.email.action.CHECK_EAS_PERMISSIONS";

    public static final String EXTRA_REQUEST_CODE = "REQUEST_CODE";
    public static final int REQUEST_CODE_ALL_PERMISSION = 3;
    public static final String EXTRA_REQUEST_RESULT = "REQUEST_RESULT";
    public static final int PERMISSION_NOT_AVAIL = 0;
    public static final int PERMISSION_AVAIL = 1;

    public static final String KEY_FROM_BR = "from_br";
    public static final int FROM_BR = 1;
}
