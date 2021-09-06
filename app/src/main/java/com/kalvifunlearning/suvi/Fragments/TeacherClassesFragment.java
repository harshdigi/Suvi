package com.kalvifunlearning.suvi.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalvifunlearning.suvi.Adapters.Explore.ParentItemAdapter;
import com.kalvifunlearning.suvi.Classes.ClassesAdapter;
import com.kalvifunlearning.suvi.R;

import org.jetbrains.annotations.NotNull;


public class TeacherClassesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ClassesAdapter classesAdapter ;
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_teacher_classes, container, false);
        recyclerView =root.findViewById(R.id.classesRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        classesAdapter = new ClassesAdapter(getContext());
        recyclerView.setAdapter(classesAdapter);
        recyclerView.setLayoutManager(layoutManager);
        return root;
    }
}