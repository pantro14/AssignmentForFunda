package com.funda.davidpardo.fundaassignment.taskassignment;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.funda.davidpardo.fundaassignment.R;
import com.funda.davidpardo.fundaassignment.makelaarlist.MakelaarListFragment;

import java.util.ArrayList;
import java.util.List;

public class TasksAssignmentActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewTaskAdapter sectionTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_assignment);

        sectionTaskAdapter = new ViewTaskAdapter(getSupportFragmentManager());

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPager = (ViewPager) findViewById(R.id.container);

        MakelaarListFragment fragmentTaskOne = new MakelaarListFragment();
        Bundle bundleOne = new Bundle();
        bundleOne.putInt("task", 1);
        fragmentTaskOne.setArguments(bundleOne);

        MakelaarListFragment fragmentTaskTwo = new MakelaarListFragment();
        Bundle bundleTwo = new Bundle();
        bundleTwo.putInt("task", 2);
        fragmentTaskTwo.setArguments(bundleTwo);


        sectionTaskAdapter.addFragment(fragmentTaskOne, getResources().getString(R.string.tab1));
        sectionTaskAdapter.addFragment(fragmentTaskTwo, getResources().getString(R.string.tab2));
        viewPager.setAdapter(sectionTaskAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    public class ViewTaskAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewTaskAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
