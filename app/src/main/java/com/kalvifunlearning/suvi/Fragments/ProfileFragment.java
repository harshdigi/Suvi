package com.kalvifunlearning.suvi.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.kalvifunlearning.suvi.Activities.TypeSelectionActivity;
import com.kalvifunlearning.suvi.R;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment {
    Button logoutBtn;
    private SharedPreferences mSahredPref;
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        logoutBtn = root.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                mSahredPref = getActivity().getSharedPreferences("userDetails", MODE_PRIVATE);
                SharedPreferences.Editor editor = mSahredPref.edit();
                editor.clear();
                editor.putBoolean("firstTime",false);
                editor.commit();
                Intent intent = new Intent(getActivity(), TypeSelectionActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
        return root;
    }
}