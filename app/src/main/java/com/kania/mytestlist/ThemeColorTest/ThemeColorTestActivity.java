package com.kania.mytestlist.ThemeColorTest;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.kania.mytestlist.R;

public class ThemeColorTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_color_test);

        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        //end templet

        findViewById(R.id.theme_color_btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = MyDialogFragment.newInstance();
                dialog.show(getSupportFragmentManager(), MyDialogFragment.class.getCanonicalName());
            }
        });

        CheckBox cb = (CheckBox)findViewById(R.id.theme_color_cb_test1);

    }
}
