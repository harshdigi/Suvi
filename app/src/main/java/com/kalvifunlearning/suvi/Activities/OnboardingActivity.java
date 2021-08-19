package com.kalvifunlearning.suvi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kalvifunlearning.suvi.Adapters.OnboardingAdapter;
import com.kalvifunlearning.suvi.R;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private OnboardingAdapter onboardingAdapter;
    private int numberOfSlides =4;
    private TextView[] dots;
    private Button letsGetStarted ;
    private FloatingActionButton nextBtn;
    private Animation animation;
    private int currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        //Variables
        viewPager = findViewById(R.id.onboarding_viewpager);
        dotsLayout = findViewById(R.id.dots);
        onboardingAdapter = new OnboardingAdapter(this);
        letsGetStarted  = findViewById(R.id.get_started_btn);
        nextBtn = findViewById(R.id.next_btn);

        addDots(0);
        //setting up let_get_started Button
        letsGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnboardingActivity.this, TypeSelectionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //setting up viewPager
        viewPager.setAdapter(onboardingAdapter);
        viewPager.addOnPageChangeListener(changeListener);
    }
    //function to display dots
    private void addDots(int position){
        dots = new TextView[numberOfSlides];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.yellow_kalvi));
        }

    }

    // function for viewPager
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;
            animation = AnimationUtils.loadAnimation(OnboardingActivity.this,R.anim.btn_anim);

            if(position<3){
                letsGetStarted.setVisibility(View.INVISIBLE);
                if(nextBtn.getVisibility() == View.INVISIBLE){
                    nextBtn.setVisibility(View.VISIBLE);
                    nextBtn.setAnimation(animation);
                }

            }
            else if(position==3){
                nextBtn.setVisibility(View.INVISIBLE);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //function for Skip Button
    public void skip(View view){
        Intent intent = new Intent(OnboardingActivity.this, TypeSelectionActivity.class);
        startActivity(intent);
        finish();
    }
    //function for Next Button
    public  void  next(View views){
        viewPager.setCurrentItem(currentPos+1);
    }

}