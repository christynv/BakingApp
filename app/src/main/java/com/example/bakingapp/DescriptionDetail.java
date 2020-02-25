package com.example.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bakingapp.model.Steps;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

public class DescriptionDetail extends AppCompatActivity {

    private static String TAG = DescriptionDetail.class.getSimpleName();

    public static final String DESCRIPTION_VIDEO = "extra_video";
    public static final String DESCRIPTION_TEXT = "extra_description";

    PlayerView playerView;
    SimpleExoPlayer exoPlayer;
    TextView descriptionTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_detail);

        playerView = findViewById(R.id.playerView);
        descriptionTv = findViewById(R.id.tv_description);

        Intent intent = getIntent();
        if(intent != null) {
            final Bundle steps = intent.getExtras();
            descriptionTv.setText(steps.getString(DESCRIPTION_TEXT, "description"));
            String videoURL = steps.getString(DESCRIPTION_VIDEO, "URL");
            Log.e(TAG, "^^^^DESCR VIDEO URL^^^^^" + videoURL);

            exoPlayer = new SimpleExoPlayer.Builder(this).build();
            playerView.setPlayer(exoPlayer);
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                    Util.getUserAgent(this, "BakingApp"));
            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(videoURL));
            exoPlayer.prepare(videoSource);
        }
    }
}
