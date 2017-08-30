package com.example.luxiaoju.tablayouttest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        List dataList  = getDataList();
        adapter = new MyPagerAdapter(MainActivity.this,dataList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
    public List<String> getDataList() {
        List<String> dataList = new ArrayList<>();
        for (int i =0 ;i <5;i++) {
            dataList.add("tab_" + i);
        }
        return dataList;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        Context context;
        List<String> dataList;

        public MyPagerAdapter(Context context,List<String> dataList) {
            super(getSupportFragmentManager());
            this.context = context;
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList ==null ? 0 :dataList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentImpl.newInstance(dataList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return dataList.get(position);
        }

    }

    public static class FragmentImpl extends Fragment {

        private String mItemData;

        public static FragmentImpl newInstance(String itemData) {
            Bundle args = new Bundle();
            args.putString("item_data", itemData);
            FragmentImpl fragment = new FragmentImpl();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            mItemData = getArguments().getString("item_data");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setText(mItemData + " fragment impl");
            return textView;
        }
    }

}
