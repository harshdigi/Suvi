package com.kalvifunlearning.suvi.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.kalvifunlearning.suvi.Models.Explore.ChildItemModel;
import com.kalvifunlearning.suvi.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoActivity extends AppCompatActivity {
    private YouTubePlayerView youTubePlayerView;
    private TextView videoName,category,language,board,description;
    private ChildItemModel childItemModel;
    private  String videoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoName = findViewById((R.id.videoname_value));
        category = findViewById(R.id.category_value);
        language = findViewById(R.id.langauge_value);
        board =findViewById(R.id.board_value);
        description = findViewById(R.id.description_value);
        childItemModel = (ChildItemModel) getIntent().getParcelableExtra("VideoInformation");
        videoId = getIntent().getStringExtra("videoId");
        youTubePlayerView = findViewById(R.id.video_player);
        getLifecycle().addObserver(youTubePlayerView);
        if(childItemModel!=null){
            videoName.setText(childItemModel.getVideoName());
            category.setText(childItemModel.getCategory());
            language.setText(childItemModel.getLanguage());
            board.setText(childItemModel.getBoard());
            description.setText(childItemModel.getDescription());
           if(videoId!=null){
               youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                   @Override
                   public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                       super.onReady(youTubePlayer);
                       youTubePlayer.loadVideo(videoId,0);
                   }
               });
           }
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}