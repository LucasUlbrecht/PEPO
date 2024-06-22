package com.example.trab1progmov.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trab1progmov.R;
import com.example.trab1progmov.databinding.FragmentNotificationsBinding;
import com.example.trab1progmov.ui.notifications.ColorViewModel;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private ColorViewModel colorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using View Binding
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the ViewModel
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
                int color = Color.RED; // default color
                switch (colorName) {
                    case "Melancia":
                        color = Color.RED;
                        break;
                    case "Maçã":
                        color = Color.GREEN;
                        break;
                    case "Uva":
                        color = Color.BLUE;
                        break;
                    // Add more cases as needed
                }
                binding.getRoot().setBackgroundColor(color);
                colorViewModel.setSelectedColor(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
