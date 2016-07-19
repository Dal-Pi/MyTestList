package com.kania.mytestlist.DialogFragmentTest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kania.mytestlist.R;

public class DialogFragmentTestActivity extends AppCompatActivity implements View.OnClickListener,
        MyDialogFragment.Callback {

    public final String KEY_NUM = "num";
    private int num;

    private TextView mTextNum;
    private Button mBtnDialogFragment;
    private Button mBtnDialogNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment_test);

        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        //end templet

        mTextNum = (TextView)findViewById(R.id.dialog_fragment_text_num);
        mBtnDialogFragment = (Button)findViewById(R.id.dialog_fragment_btn_dialog_fragment);
        mBtnDialogFragment.setOnClickListener(this);
        mBtnDialogNormal = (Button)findViewById(R.id.dialog_fragment_btn_dialog_normal);
        mBtnDialogNormal.setOnClickListener(this);

        if (savedInstanceState == null) {
            num = 0;
        } else {
            num = savedInstanceState.getInt(KEY_NUM, 0);
            setNum(num);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUM, num);
    }

    @Override
    public void onSelect(int num) {
        setNum(num);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.dialog_fragment_btn_dialog_fragment) {
            DialogFragment dialog = MyDialogFragment.newInstance(num, this);
            dialog.show(getSupportFragmentManager(), MyDialogFragment.class.getCanonicalName());
        } else if (id == R.id.dialog_fragment_btn_dialog_normal) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Test Alert Dialog on Activity")
                    .setMessage("arg = " + num)
                    .setPositiveButton("positive button", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setNum(++num);
                        }
                    })
                    .setNegativeButton("negative button", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setNum(--num);
                        }
                    })
                    .setNeutralButton("neutral button", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
    }

    private void setNum(int num) {
        this.num = num;
        mTextNum.setText("" + num);
    }
}
