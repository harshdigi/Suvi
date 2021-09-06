package com.kalvifunlearning.suvi.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalvifunlearning.suvi.Adapters.Calender.CalenderAdapter;
import com.kalvifunlearning.suvi.Classes.ClassesAdapter;
import com.kalvifunlearning.suvi.R;

import org.jetbrains.annotations.NotNull;

public class CalenderFragment extends Fragment {
    private String accountType;
    private SharedPreferences mSahredPref;
    private RecyclerView recyclerView;
    private CalenderAdapter calenderAdapter;
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_calender, container, false);

        mSahredPref = getActivity().getSharedPreferences("userDetails", MODE_PRIVATE);
        accountType = mSahredPref.getString("accountType","Teacher");
        recyclerView = root.findViewById(R.id.scheduleRecyclerView);
        if(accountType.equalsIgnoreCase("Teacher")){
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            calenderAdapter = new CalenderAdapter(getContext());
            recyclerView.setAdapter(calenderAdapter);
            recyclerView.setLayoutManager(layoutManager);
        }
        return root;
    }
   }