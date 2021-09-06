package com.kalvifunlearning.suvi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.kalvifunlearning.suvi.Adapters.MainAdapter;
import com.kalvifunlearning.suvi.R;
import com.kalvifunlearning.suvi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private SharedPreferences mSahredPref;
    private ChipNavigationBar chipNavigationBar;
    private MainAdapter adapter;
    private int prevmenu;
    private String accountType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        chipNavigationBar = findViewById(R.id.bottom_nav);
        mSahredPref = getSharedPreferences("userDetails", MODE_PRIVATE);
        accountType = mSahredPref.getString("accountType","Teacher");
        adapter = new MainAdapter(this,5,accountType);
        viewPager2 = binding.mainContainer;
        viewPager2.setUserInputEnabled(false);
        if(accountType.equalsIgnoreCase("Teacher")){
            chipNavigationBar.setMenuResource(R.menu.bottom_nav_menu_teacher);
            chipNavigationBar.setItemSelected(R.id.home_teacher,true);
            prevmenu = R.id.home_teacher;
            viewPager2.setAdapter(adapter);
            viewPager2.setOffscreenPageLimit(5);
            viewPager2.setCurrentItem(0);
//            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    super.onPageSelected(position);
//                    switch (position) {
//                        case 0:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.home_teacher, true);
//                            prevmenu = R.id.home_teacher;
//                            break;
//                        case 1:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.add, true);
//                            prevmenu = R.id.add;
//                            break;
//                        case 2:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.student, true);
//                            prevmenu = R.id.student;
//                            break;
//                        case 3:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.calender_teacher, true);
//                            prevmenu = R.id.calender_teacher;
//                            break;
//                        case 4:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.profile_teacher, true);
//                            prevmenu = R.id.profile_teacher;
//                            break;
//                        default:
//                            return;
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                    super.onPageScrollStateChanged(state);
//                }
//            });
        }
        else if(accountType.equalsIgnoreCase("Student")){
            chipNavigationBar.setMenuResource(R.menu.bottom_nav_menu_student);
            chipNavigationBar.setItemSelected(R.id.home_student,true);
            prevmenu = R.id.home_student;
            viewPager2.setAdapter(adapter);
            viewPager2.setCurrentItem(0);
//            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    super.onPageSelected(position);
//                    switch (position) {
//                        case 0:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.home_student, true);
//                            prevmenu = R.id.home_student;
//                            break;
//                        case 1:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.explore, true);
//                            prevmenu = R.id.explore;
//                            break;
//                        case 2:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.quiz, true);
//                            prevmenu = R.id.quiz;
//                            break;
//                        case 3:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.calender_student, true);
//                            prevmenu = R.id.calender_student;
//                            break;
//                        case 4:
//                            chipNavigationBar.setItemSelected(prevmenu, false);
//                            chipNavigationBar.setItemSelected(R.id.profile_student, true);
//                            prevmenu = R.id.profile_student;
//                            break;
//                        default:
//                            return;
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                    super.onPageScrollStateChanged(state);
//                }
//            });
        }

        viewPager2.setOffscreenPageLimit(5);
        bottommenu();


    }

    private void bottommenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if(accountType.equalsIgnoreCase("Teacher")){
                switch (i) {
                    case R.id.home_teacher:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.add:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.classes:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.calender_teacher:
                        viewPager2.setCurrentItem(3);
                        break;
                    case R.id.profile_teacher:
                        viewPager2.setCurrentItem(4);
                        break;
                }
                }
                else if (accountType.equalsIgnoreCase("Student")){
                    switch (i) {
                        case R.id.home_student:
                            viewPager2.setCurrentItem(0);
                            break;
                        case R.id.explore:
                            viewPager2.setCurrentItem(1);
                            break;
                        case R.id.quiz:
                            viewPager2.setCurrentItem(2);
                            break;
                        case R.id.calender_student:
                            viewPager2.setCurrentItem(3);
                            break;
                        case R.id.profile_student:
                            viewPager2.setCurrentItem(4);
                            break;
                    }
                }
            }
        });
    }
}