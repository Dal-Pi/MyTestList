package com.kania.mytestlist.MultiWindowTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.kania.mytestlist.R;

public class MultiWindowTestActivity extends AppCompatActivity implements View.OnClickListener,
        MultiWindowDialogWrapper.Callback {

    private TextView mTextNum;
    private Button mBtnDialogFragment;

    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_window_test);

        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        //end templet

        mTextNum = (TextView)findViewById(R.id.dialog_fragment_text_num);
        mBtnDialogFragment = (Button)findViewById(R.id.dialog_fragment_btn_dialog_fragment);
        mBtnDialogFragment.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSelect(int num) {
        setNum(num);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.dialog_fragment_btn_dialog_fragment) {
            MultiWindowDialogWrapper dialog = MultiWindowDialogWrapper.newInstance(this, this);
            dialog.show(getFragmentManager());
        }
    }

    private void setNum(int num) {
        this.num = num;
        mTextNum.setText("" + num);
    }
}