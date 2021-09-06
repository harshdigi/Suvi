package com.kalvifunlearning.suvi.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kalvifunlearning.suvi.Fragments.CalenderFragment;
import com.kalvifunlearning.suvi.Fragments.ProfileFragment;
import com.kalvifunlearning.suvi.Fragments.StudentExploreFragment;
import com.kalvifunlearning.suvi.Fragments.StudentHomeFragment;
import com.kalvifunlearning.suvi.Fragments.StudentQuizFragment;
import com.kalvifunlearning.suvi.Fragments.TeacherAddFragment;
import com.kalvifunlearning.suvi.Fragments.TeacherHomeFragment;
import com.kalvifunlearning.suvi.Fragments.TeacherClassesFragment;

public class MainAdapter extends FragmentStateAdapter {
    private int totalTab;
    private String accountType;
    public MainAdapter(@NonNull FragmentActivity fragmentActivity,int totalTab, String accountType ) {
        super(fragmentActivity);
        this.totalTab= totalTab;
        this.accountType= accountType;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(accountType.equalsIgnoreCase("Teacher")){
        switch (position){
            case 0:
                return  new TeacherHomeFragment();
            case 1:
                return  new TeacherAddFragment();
            case 2:
                return  new TeacherClassesFragment();
            case 3:
                return  new CalenderFragment();
            case 4:
                return  new ProfileFragment();
            default:
                return new TeacherHomeFragment();
        }
        }
        else if (accountType.equalsIgnoreCase("Student")){
            switch (position){
                case 0:
                    return  new StudentHomeFragment();
                case 1:
                    return  new StudentExploreFragment();
                case 2:
                    return  new StudentQuizFragment();
                case 3:
                    return  new CalenderFragment();
                case 4:
                    return  new ProfileFragment();
                default:
                    return new TeacherHomeFragment();
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return totalTab;
    }
}
