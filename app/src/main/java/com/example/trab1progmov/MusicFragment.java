package com.example.trab1progmov;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends Fragment {

    private static final String ARG_SONG_NAME = "songName";
    private String mSongName;

    public MusicFragment() {
        // Required empty public constructor
    }

    public static MusicFragment newInstance(Music music) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        args.putParcelable("music", (Parcelable) music);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Music music = getArguments().getParcelable("music");
            if (music != null) {
                mSongName = music.getName();
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        TextView textView = view.findViewById(R.id.text_view);
        ImageView imageView = view.findViewById(R.id.image_view);

        if (mSongName != null) {
            textView.setText(mSongName);
        }

        Music music = getArguments().getParcelable("music");

        if (music != null) {
            int imageResource = music.getPhotoId();

            if (imageResource != 0) {
                imageView.setImageResource(imageResource);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }

        return view;
    }
}
