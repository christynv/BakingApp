package com.example.bakingapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class DescriptionDetail extends AppCompatActivity {

    SimpleExoPlayer exoPlayer;
    PlayerView playerView;
    TextView descriptionTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_detail);

        playerView = findViewById(R.id.playerView);

        
    }
}
