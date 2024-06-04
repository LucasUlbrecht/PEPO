package com.example.trab1progmov.ui.home;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.trab1progmov.Music;
import com.example.trab1progmov.MusicPlayerFragment;
import com.example.trab1progmov.MusicPlayerListener;
import com.example.trab1progmov.R;
import com.example.trab1progmov.databinding.FragmentHomeBinding;

import com.example.trab1progmov.ui.MusicListAdapter; // Importe a classe correta
import com.example.trab1progmov.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MusicPlayerListener {
    private static final String MUSIC_RESOURCE_PREFIX = "music_";
    private static final String MUSIC_RESOURCE_TYPE = "raw";

    private GestureDetector gestureDetector;
    private ScrollView recyclerView;
    private boolean isExpanded = false;

    private MusicPlayerFragment musicPlayerFragment;
    private MusicPlayer musicPlayer;
    private ListView listView;
    private GridView gridView;
    private MusicListAdapter adapter; // Altere a referência para o novo adaptador
    private MusicListAdapter adapterGrid;
    private FrameLayout frameLayout;

    private Boolean currentTypeViewIsSwitch;

    public HomeFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicialize o musicPlayerFragment aqui
        musicPlayerFragment = new MusicPlayerFragment(); // Ou como você estiver criando este fragmento

        List<Music> musicList = createMusicList();
        musicPlayer = MusicPlayer.getInstance(requireContext());
        musicPlayer.addListener(this);

        //gridView = binding.musicListView;

        adapter = new MusicListAdapter(requireContext(), musicList, musicPlayer, false);
        adapterGrid = new MusicListAdapter(requireContext(), musicList, musicPlayer, true);
        listView = binding.musicListView;
        gridView = binding.musicGridView;
        listView.setAdapter(adapter);
        gridView.setAdapter(adapterGrid);







        // Dentro da sua Activity ou Fragment
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("Sla","Sla");
            Music selectedMusic = adapter.getCurrentMusic(position);

            if (musicPlayer.isPlaying() && musicPlayer.getCurrentMusic().equals(selectedMusic)) {
                // Se a música atual está tocando e é a mesma que foi selecionada novamente,
                // pare a música
                musicPlayer.stop();
            } else {
                // Caso contrário, defina a nova música e reproduza-a
                musicPlayer.setCurrentMusic(selectedMusic);
                musicPlayer.play();
            }
        });
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("Sla","Sla");
            Music selectedMusic = adapter.getCurrentMusic(position);

            if (musicPlayer.isPlaying() && musicPlayer.getCurrentMusic().equals(selectedMusic)) {
                // Se a música atual está tocando e é a mesma que foi selecionada novamente,
                // pare a música
                musicPlayer.stop();
            } else {
                // Caso contrário, defina a nova música e reproduza-a
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

        frameLayout=binding.musicPlayerFragmentContainer;
        frameLayout.bringToFront();


        return root;
    }



    private void checkIfMusicIsPlaying(Music selectedMusic) {
        Log.d("Sla","Sla");
        // Verifique se há uma música tocando
        if (isMusicPlaying()) {
            // Obtenha os dados da música

            // Instancie o MusicPlayerFragment com os dados da música
            Log.d("Sla","Sla");
            musicPlayerFragment = MusicPlayerFragment.newInstance(selectedMusic, this.requireContext());

            // Adicione dinamicamente o MusicPlayerFragment ao HomeFragment
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.musicPlayerFragmentContainer, musicPlayerFragment)
                    .commit();
            musicPlayerFragment.playMusic();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Libera os recursos do MusicPlayer quando a fragment é destruída
        if (musicPlayer != null) {
            musicPlayer.release();
        }
    }

    private List<Music> createMusicList() {
        // Aqui você pode criar sua lista de músicas
        List<Music> musicList = new ArrayList<>();
        String[] musicNames = getResources().getStringArray(R.array.music_list);
        for (int i = 0; i < musicNames.length; i++) {
            musicList.add(new Music(this.requireContext(), musicNames[i]));
        }
        return musicList;
    }




    private boolean isMusicPlaying() {
        if (musicPlayer != null && !musicPlayer.isEmpty()) {
            Log.d("Sla", new Boolean(musicPlayer.isPlaying()).toString());
            return musicPlayer.isPlaying();
        }
        return false;
    }
    @Override
    public void onMusicStarted(Music music) {
        boolean f = false;
        Log.d("its", "recognized");
        if (!musicPlayerFragment.isInShow()) {
            // Crie uma nova instância do fragmento e adicione-o
            musicPlayerFragment = MusicPlayerFragment.newInstance(music, this.requireContext());
            musicPlayer.addListener(musicPlayerFragment);
            getChildFragmentManager().beginTransaction()
                    .add(R.id.musicPlayerFragmentContainer, musicPlayerFragment)
                    .commit();

            Log.d("its", "maked");
            musicPlayerFragment.setIsInShow();
            musicPlayerFragment.updateMusicInformation(music);
        }
            f = true;
        if (!f)
            Log.d("its", "notAdded");
    }


    @Override
    public void onMusicPaused() {

    }

    @Override
    public void onMusicStopped() {

    }

    public void switchToGrid(){
        gridView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }
    public void switchToList(){
        gridView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

}
