package com.kalvifunlearning.suvi.Adapters.Explore;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.kalvifunlearning.suvi.Activities.VideoActivity;
import com.kalvifunlearning.suvi.Models.Explore.ChildItemModel;
import com.kalvifunlearning.suvi.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItemModel> ChildItemList;
    private Context context;
    ChildItemAdapter(List<ChildItemModel> childItemList,Context context)
    {
        this.ChildItemList = childItemList;
        this.context =context;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.explore_child_item, viewGroup, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position)
    {
        ChildItemModel childItem = ChildItemList.get(position);
        childViewHolder.ChildItemTitle.setText(childItem.getVideoName());
        String videoId =getVideoId(childItem.getVideoLink());
        String imageUrl ="https://img.youtube.com/vi/"+videoId+"/0.jpg";
        Glide.with(context)
                .load(imageUrl)
                .into(childViewHolder.ChildItemImage);
        childViewHolder.parentView.setBackgroundDrawable(context.getDrawable(R.drawable.student_bg));
        childViewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("VideoInformation", (Parcelable) childItem);
                intent.putExtra("videoId",videoId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return ChildItemList.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView ChildItemTitle;
        ImageView ChildItemImage;
        MaterialCardView parentView;

        ChildViewHolder(View itemView)
        {
            super(itemView);
            ChildItemTitle = itemView.findViewById(R.id.child_item_title);
            ChildItemImage= itemView.findViewById(R.id.img_child_item);
            parentView = itemView.findViewById(R.id.explore_child_item);

        }
    }
    public static String getVideoId(@NonNull String videoUrl) {

        String videoId = "";
        String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(videoUrl);
        if(matcher.find()){
            videoId = matcher.group(1);
        }
        return videoId;
    }
}
