package com.kalvifunlearning.suvi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kalvifunlearning.suvi.R;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences onBoardingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
        boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if(isFirstTime)
                { SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null){
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent1 = new Intent(SplashActivity.this, TypeSelectionActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                }
            }
        },4000);
    }

}