package com.kania.mytestlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kania.mytestlist.BroadcastReceiverTest.BroadcastReceiverTestActivity;
import com.kania.mytestlist.DialogFragmentTest.DialogFragmentTestActivity;
import com.kania.mytestlist.HandlerTest.HandlerTestActivity;
import com.kania.mytestlist.MultiWindowTest.MultiWindowTestActivity;
import com.kania.mytestlist.RunningAppTest.RunningAppTestActivity;
import com.kania.mytestlist.Tempet.TempletActivity;
import com.kania.mytestlist.ThemeColorTest.ThemeColorTestActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String APP_TAG = "MyTestList";

    private ArrayList<String> mTestItemName;
    private HashMap<String, Object> mActivityClassMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTestList();

        ListView lvTestList = (ListView)findViewById(R.id.main_list);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTestItemName);
        lvTestList.setAdapter(adapter);
        lvTestList.setOnItemClickListener(this);
    }

    private void initTestList() {
        mTestItemName = new ArrayList<>();
        mActivityClassMap = new HashMap<>();

        //TODO Make item here
        setTestItem("6. Handler Test", HandlerTestActivity.class);
        setTestItem("5. MultiWindow Test", MultiWindowTestActivity.class);
        setTestItem("4. BroadcastReceiver Test", BroadcastReceiverTestActivity.class);
        setTestItem("3. Running App Test", RunningAppTestActivity.class);
        setTestItem("2. Theme Color Test", ThemeColorTestActivity.class);
        setTestItem("1. Dialog Fargment Test", DialogFragmentTestActivity.class);

        //templet
        setTestItem("Templet", TempletActivity.class);
    }

    private void setTestItem(String name, Object activityClass) {
        mTestItemName.add(name);
        mActivityClassMap.put(name, activityClass);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String itemName = mTestItemName.get(position);
        if (itemName != null && !"".equalsIgnoreCase(itemName)) {
            Intent testItemIntent = new Intent(this, (Class<?>)mActivityClassMap.get(itemName));
            startActivity(testItemIntent);
        } else {
            Toast.makeText(this, "Invalid item name", Toast.LENGTH_SHORT).show();
        }
    }
}
