package com.example.trab1progmov;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.trab1progmov.ui.dashboard.DashboardFragment;
import com.example.trab1progmov.ui.home.MusicPlayer;

public class MusicPlayerFragment extends Fragment implements View.OnClickListener, MusicPlayerListener {

    private TextView musicName;
    private MusicPlayer musicPlayer;
    private ProgressBar progressBar;
    private ImageButton playButton;
    private boolean isPlaying;
    public isOnShow isonshow = isOnShow.IS_NOT_ON_SHOW;
    private Handler handler = new Handler();
    private GestureDetector gestureDetector;
    private ScrollView scrollView;
    private boolean isExpanded = false;
    private boolean isTransitionStarted = false;

    public static MusicPlayerFragment newInstance(Music currentChosenMusic, Context context) {
        MusicPlayerFragment fragment = new MusicPlayerFragment();
        Bundle args = new Bundle();
        args.putInt("songId", currentChosenMusic.getSongId());
        args.putString("songName", currentChosenMusic.getName());
        fragment.musicPlayer = MusicPlayer.getInstance(context);
        fragment.musicPlayer.setCurrentMusic(currentChosenMusic);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("MusicPlayerFragment", "onCreateView() called");
        musicPlayer = MusicPlayer.getInstance((Context) getArguments().getSerializable("context"));
        View view = inflater.inflate(R.layout.fragment_music_player, container, false);

        playButton = view.findViewById(R.id.button_play_pause);
        playButton.setOnClickListener(this);

        progressBar = view.findViewById(R.id.seek_bar_progress);

        musicName = view.findViewById(R.id.text_view_song_name);
        if (musicPlayer.getMusic() != null) {
            musicName.setText(musicPlayer.getMusic().getName());
        }

        gestureDetector = new GestureDetector(requireContext(), new MyGestureListener());

        scrollView = view.findViewById(R.id.lyricsScrollView);

        view.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (musicPlayer != null) {
            musicPlayer.release();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_play_pause) {
            if (musicPlayer.isPlaying()) {
                pauseMusic();
            } else {
                playMusic();
            }
        }
    }

    public void playMusic() {
        musicPlayer.play();
    }

    private void pauseMusic() {
        musicPlayer.pause();
    }

    public void updateProgressBar() {
        Log.d("atualiza", "atualizando");
        int mediaDuration = musicPlayer.getMediaDuration();
        progressBar.setMax(mediaDuration);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("atualiza", "atualizand1o");
                if (musicPlayer != null && musicPlayer.isPlaying()) {
                    int currentMediaPosition = musicPlayer.getCurrentMediaPosition();
                    progressBar.setProgress(currentMediaPosition);
                    handler.postDelayed(this, 1000);
                }
            }
        }, 0);
    }

    public void updateMusicInformation(Music music) {
        if (musicName != null) {
            musicName.setText(music.getName());
            playButton.setImageResource(R.drawable.video_pause_btn);
            updateProgressBar();
        }
    }

    public boolean isInShow() {
        return this.isonshow == isOnShow.IS_ON_SHOW;
    }

    public void setIsInShow() {
        this.isonshow = isOnShow.IS_ON_SHOW;
    }

    @Override
    public void onMusicStarted(Music music) {
        isPlaying = true;
        playButton.setImageResource(R.drawable.video_pause_btn);
        updateProgressBar();
        if (!musicName.getText().toString().equals(musicPlayer.getCurrentMusic().getName())) {
            musicName.setText(musicPlayer.getCurrentMusic().getName());
        }
    }

    @Override
    public void onMusicPaused() {
        playButton.setImageResource(R.drawable.video_play_btn);
        isPlaying = false;
    }

    @Override
    public void onMusicStopped() {
        playButton.setImageResource(R.drawable.video_play_btn);
        isPlaying = false;
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (e2.getY() < e1.getY()) {
                expandFragment();
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    private void expandFragment() {
        if (!isExpanded) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(getActivity().findViewById(R.id.musicPlayerFragmentContainer), "translationY", -300f);
            animator.setDuration(500);
            animator.addUpdateListener(animation -> {
                float translationY = (float) animation.getAnimatedValue();
                if (translationY < -150f && !isTransitionStarted) {
                    startTransition();
                    isTransitionStarted = true;
                }
            });
            animator.start();
            isExpanded = true;
        }
    }

    private void startTransition() {
        DashboardFragment dashboardFragment = new DashboardFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, dashboardFragment)
                .addToBackStack(null)
                .commit();
    }
}

enum isOnShow {
    IS_ON_SHOW,
    IS_NOT_ON_SHOW
}
