package com.example.trab1progmov.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trab1progmov.Music;
import com.example.trab1progmov.MusicPlayerFragment;
import com.example.trab1progmov.MusicPlayerListener;
import com.example.trab1progmov.R;
import com.example.trab1progmov.databinding.FragmentHomeBinding;
import com.example.trab1progmov.ui.MusicListAdapter;
import com.example.trab1progmov.ui.notifications.ColorViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MusicPlayerListener {
    private static final String MUSIC_RESOURCE_PREFIX = "music_";
    private static final String MUSIC_RESOURCE_TYPE = "raw";

    private ScrollView recyclerView;
    private boolean isExpanded = false;

    private MusicPlayerFragment musicPlayerFragment;
    private MusicPlayer musicPlayer;
    private ListView listView;
    private GridView gridView;
    private MusicListAdapter adapter;
    private MusicListAdapter adapterGrid;
    private FrameLayout frameLayout;

    private ColorViewModel colorViewModel;
    private FragmentHomeBinding binding;

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the ViewModel
        colorViewModel = new ViewModelProvider(requireActivity()).get(ColorViewModel.class);

        // Observe the selected colors
        colorViewModel.getSelectedColor().observe(getViewLifecycleOwner(), this::updateColors);

        List<Music> musicList = createMusicList();
        musicPlayer = MusicPlayer.getInstance(requireContext());
        musicPlayer.addListener(this);

        adapter = new MusicListAdapter(requireContext(), musicList, musicPlayer, false);
        adapterGrid = new MusicListAdapter(requireContext(), musicList, musicPlayer, true);
        listView = binding.musicListView;
        gridView = binding.musicGridView;
        listView.setAdapter(adapter);
        gridView.setAdapter(adapterGrid);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Music selectedMusic = adapter.getCurrentMusic(position);

            if (musicPlayer.isPlaying() && musicPlayer.getCurrentMusic().equals(selectedMusic)) {
                musicPlayer.stop();
            } else {
                musicPlayer.setCurrentMusic(selectedMusic);
                musicPlayer.play();
            }
        });

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Music selectedMusic = adapter.getCurrentMusic(position);

            if (musicPlayer.isPlaying() && musicPlayer.getCurrentMusic().equals(selectedMusic)) {
                musicPlayer.stop();
            } else {
                musicPlayer.setCurrentMusic(selectedMusic);
                musicPlayer.play();
            }
        });

        Switch switchListType = binding.switchListType;
        switchListType.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchToGrid();
            } else {
                switchToList();
            }
        });

        frameLayout = binding.musicPlayerFragmentContainer;
        frameLayout.bringToFront();

        return root;
    }

    private List<Music> createMusicList() {
        List<Music> musicList = new ArrayList<>();
        String[] musicNames = getResources().getStringArray(R.array.music_list);
        for (String musicName : musicNames) {
            musicList.add(new Music(this.requireContext(), musicName));
        }
        return musicList;
    }

    private boolean isMusicPlaying() {
        return musicPlayer != null && !musicPlayer.isEmpty() && musicPlayer.isPlaying();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (musicPlayer != null) {
            musicPlayer.release();
        }
    }

    @Override
    public void onMusicStarted(Music music) {
        if (musicPlayerFragment == null || !musicPlayerFragment.isAdded()) {
            musicPlayerFragment = MusicPlayerFragment.newInstance(music, this.requireContext());
            musicPlayer.addListener(musicPlayerFragment);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.musicPlayerFragmentContainer, musicPlayerFragment)
                    .commit();
        } else {
            musicPlayerFragment.updateMusicInformation(music);
        }
    }

    @Override
    public void onMusicPaused() {
        // Implement any behavior for music paused if necessary
    }

    @Override
    public void onMusicStopped() {
        // Implement any behavior for music stopped if necessary
    }

    public void switchToGrid() {
        gridView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    public void switchToList() {
        gridView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    private void updateColors(int[] colors) {
        binding.getRoot().setBackgroundColor(colors[0]); // Background color
        // Example of updating button colors:
        binding.navBar.setBackgroundColor(colors[1]);
    }
}
