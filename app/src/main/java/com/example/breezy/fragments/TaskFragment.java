package com.example.breezy.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.breezy.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskFragment extends Fragment {

    @BindView(R.id.media_player) PlayerView media_player;
    @BindView(R.id.playing_song_name) TextView playing_song_name;

    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private List<String> mediaTitle;

    public TaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_task, container, false);

        ButterKnife.bind(this, root);

        mediaTitle = Arrays.asList("Deep Meditation", "Earthly Music", "Relaxing Music", "Stars", "The Light", "Yoga", "Yoga Tune");
        playing_song_name.setText(mediaTitle.get(0));

        return root;
    }

    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(getContext()).build();
        media_player.setPlayer(player);

        MediaItem mediaItem1 = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.deep_meditation));
        MediaItem mediaItem2 = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.earth));
        MediaItem mediaItem3 = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.relaxing));
        MediaItem mediaItem4 = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.stars));
        MediaItem mediaItem5 = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.the_light));
        MediaItem mediaItem6 = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.yoga));
        MediaItem mediaItem7 = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.yoga_tune));

        player.setMediaItem(mediaItem1);
        player.addMediaItem(mediaItem2);
        player.addMediaItem(mediaItem3);
        player.addMediaItem(mediaItem4);
        player.addMediaItem(mediaItem5);
        player.addMediaItem(mediaItem6);
        player.addMediaItem(mediaItem7);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare();

        player.addListener(new Player.EventListener() {
            @Override
            public void onPositionDiscontinuity(int reason) {
                int latestWindowIndex = player.getCurrentWindowIndex();
                playing_song_name.setText(mediaTitle.get(latestWindowIndex));
            }
        });
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        media_player.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }
}
