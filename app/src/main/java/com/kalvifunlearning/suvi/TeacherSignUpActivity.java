package com.kalvifunlearning.suvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kalvifunlearning.suvi.databinding.ActivityTeacherSignUpBinding;

public class TeacherSignUpActivity extends AppCompatActivity {

    ActivityTeacherSignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSignUpActivity.this, LoginActivity.class));
            }
        });

    }
}