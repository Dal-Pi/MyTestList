package com.kania.mytestlist.Tempet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kania.mytestlist.R;

public class TempletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templet);

        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        //end templet
    }
}
