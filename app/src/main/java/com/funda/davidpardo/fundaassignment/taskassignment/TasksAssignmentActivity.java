package com.funda.davidpardo.fundaassignment.taskassignment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.funda.davidpardo.fundaassignment.R;
import com.funda.davidpardo.fundaassignment.makelaarlist.MakelaarListFragment;

import java.util.ArrayList;
import java.util.List;

public class TasksAssignmentActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewTaskAdapter sectionTaskAdapter;
    private final String TASK = "task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_assignment);
        loadActivityUI();
    }

    /**
     * Method that loads the UI components for the principal activity
     */
    private void loadActivityUI() {
        sectionTaskAdapter = new ViewTaskAdapter(getSupportFragmentManager());
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.container);

        sectionTaskAdapter.addFragment(createFragment(getResources().getInteger(R.integer.taskOne)),
                getResources().getString(R.string.tab2));
        sectionTaskAdapter.addFragment(createFragment(getResources().getInteger(R.integer.taskTwo)),
                getResources().getString(R.string.tab2));
        viewPager.setAdapter(sectionTaskAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Method that manage the creation of the fragments per task
     */
    private MakelaarListFragment createFragment(int taskType){
        MakelaarListFragment fragmentTask = new MakelaarListFragment();
        Bundle bundleOne = new Bundle();
        bundleOne.putInt(TASK, taskType);
        fragmentTask.setArguments(bundleOne);
        return fragmentTask;
    }

    /**
     * This class is in charge of the Swipe Fragment Behavior
     */
    public class ViewTaskAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewTaskAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
