package com.kalvifunlearning.suvi.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kalvifunlearning.suvi.Activities.AddVideoActivity;
import com.kalvifunlearning.suvi.R;

import org.jetbrains.annotations.NotNull;


public class TeacherAddFragment extends Fragment {

   @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_teacher_add, container, false);

       RelativeLayout button = root.findViewById(R.id.addVideoCard);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity(), AddVideoActivity.class);
               startActivity(intent);
           }
       });



        return root;
    }
}