package com.kania.mytestlist.ListViewTest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kania.mytestlist.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewTestFragment extends ListFragment {

    private List<MyData> mItems;

    public ListViewTestFragment() {
        // Required empty public constructor
    }
    public static ListViewTestFragment newInstance() {
        ListViewTestFragment fragment = new ListViewTestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new ArrayList<>();
        mItems.add(new MyData("first", 1));
        mItems.add(new MyData("second", 2));
        mItems.add(new MyData("third", 3));
        mItems.add(new MyData("fourth", 4));
        mItems.add(new MyData("fidth", 5));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_list_view_test, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyListAdapter adapter = new MyListAdapter(getActivity(), android.R.layout.simple_list_item_2, mItems);
        setListAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    class MyData {
        public String name;
        public int value;

        public MyData(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    class MyListAdapter extends ArrayAdapter<MyData> {

        int mItemResId;
        public MyListAdapter(Context context, int resource, List<MyData> objects) {
            super(context, resource, objects);
            mItemResId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = View.inflate(getContext(), mItemResId, null);
            }
            TextView textTitle = (TextView)view.findViewById(android.R.id.text1);
            TextView textSummary = (TextView)view.findViewById(android.R.id.text2);
            MyData data = getItem(position);
            textTitle.setText(data.name);
            textSummary.setText(data.value + "");

            return view;
        }
    }
}
