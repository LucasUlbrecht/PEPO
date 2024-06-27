package com.example.trab1progmov.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trab1progmov.ui.ActivityLogin;
import com.example.trab1progmov.R;
import com.example.trab1progmov.databinding.FragmentNotificationsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private ColorViewModel colorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the ColorViewModel
        colorViewModel = new ViewModelProvider(requireActivity()).get(ColorViewModel.class);

        // Set up the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.temas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerTema.setAdapter(adapter);

        binding.spinnerTema.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String colorName = (String) parent.getItemAtPosition(position);
                int[] colors = getColorsFromName(colorName);
                updateColors(colors);

                // Set the selected colors in the ViewModel
                colorViewModel.setSelectedColors(colors);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Configure the ImageButton
        binding.imgBtnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit(v);
            }
        });

        return root;
    }

    private int[] getColorsFromName(String colorName) {
        switch (colorName) {
            case "Melancia":
                return new int[]{
                        ContextCompat.getColor(requireContext(), R.color.background_melancia),
                        ContextCompat.getColor(requireContext(), R.color.navbar_melancia),
                        ContextCompat.getColor(requireContext(), R.color.background_melancia),
                };
            case "Maçã":
                return new int[]{
                        ContextCompat.getColor(requireContext(), R.color.background_maca),
                        ContextCompat.getColor(requireContext(), R.color.navbar_maca),
                        ContextCompat.getColor(requireContext(), R.color.navbar_maca),
                };
            case "Uva":
                return new int[]{
                        ContextCompat.getColor(requireContext(), R.color.background_uva),
                        ContextCompat.getColor(requireContext(), R.color.navbar_uva),
                        ContextCompat.getColor(requireContext(), R.color.background_uva),
                };
            default:
                return new int[]{
                        ContextCompat.getColor(requireContext(), R.color.background_melancia),
                        ContextCompat.getColor(requireContext(), R.color.navbar_melancia),
                        ContextCompat.getColor(requireContext(), R.color.background_melancia),
                };
        }
    }

    private void updateColors(int[] colors) {
        binding.getRoot().setBackgroundColor(colors[0]);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setBackgroundColor(colors[1]);

        Window window = requireActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(colors[2]);
    }

    // Método para ser chamado ao clicar no botão
    public void exit(View view) {
        Intent intent = new Intent(getActivity(), ActivityLogin.class);
        startActivity(intent);
        requireActivity().finish(); // Encerra a atividade atual
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
