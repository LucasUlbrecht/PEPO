package com.example.trab1progmov.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.trab1progmov.R;
import com.example.trab1progmov.databinding.FragmentDashboardBinding;
import com.example.trab1progmov.ui.home.MusicPlayer;

public class DashboardFragment extends Fragment {
    private GestureDetector gestureDetector;

    private FragmentDashboardBinding binding;
    private boolean isExpanded = false;
    private boolean isTransitionStarted = false;
    private static final int SWIPE_THRESHOLD = 100;
    private MusicPlayer musicPlayer;
    private TextView textViewNome;
    private TextView textViewLetra;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        musicPlayer = MusicPlayer.getInstance(requireContext());



        textViewNome = binding.txtNomeMusica; // TextView para o nome da música
        textViewLetra = binding.txtLetra; // TextView para a letra da música

        // Atualizar o nome da música
        updateMusicName();

        // Atualizar a letra da música
        int letraId = musicPlayer.getMusic().getLetterId();
        String letra = getResources().getString(letraId);
        Log.d("sla", letra);
        textViewLetra.setText(letra);
        Button button = root.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });



        return root;
    }

    private void updateMusicName() {
        // Atualizar o TextView com o nome da música atual
        textViewNome.setText(musicPlayer.getMusic().getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void exit(){
        navigateToMainActivity();
    }
    private void navigateToMainActivity() {
        // Remover o fragmento atual
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


}
