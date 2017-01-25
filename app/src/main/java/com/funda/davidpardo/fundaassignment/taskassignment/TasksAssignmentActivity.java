package com.funda.davidpardo.fundaassignment.taskassignment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.funda.davidpardo.fundaassignment.R;
import com.funda.davidpardo.fundaassignment.makelaarlist.MakelaarList;

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
        sectionTaskAdapter.addFragment(new MakelaarList(), getResources().getString(R.string.tab1));
        sectionTaskAdapter.addFragment(new MakelaarList(), getResources().getString(R.string.tab2));
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
