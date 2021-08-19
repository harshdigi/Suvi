package com.kalvifunlearning.suvi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.kalvifunlearning.suvi.R;

public class OnboardingAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public OnboardingAdapter(Context context) {
        this.context = context;
    }
    //    Images Array
    int images[] ={
            R.drawable.ic_teaching,
            R.drawable.ic_quiz,
            R.drawable.ic_track_progress,
            R.drawable.ic_video_lecture,
    };
    //    Title Array
    int titles[]={
            R.string.slide_title_one,
            R.string.slide_title_two,
            R.string.slide_title_three,
            R.string.slide_title_four,
    };
    //    Decription Array
    int descriptions []={
            R.string.slide_desc_one,
            R.string.slide_desc_two,
            R.string.slide_desc_three,
            R.string.slide_desc_four,
    };

    @Override
    public int getCount() {
        return images.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);

//        Hooks
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView title = view.findViewById(R.id.slider_title);
        TextView description = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        title.setText(titles[position]);
        description.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}

