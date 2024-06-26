package com.example.trab1progmov.ui;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.trab1progmov.Music;
import com.example.trab1progmov.MusicFragment;
import com.example.trab1progmov.R;
import com.example.trab1progmov.ui.home.MusicPlayer;

import java.util.List;

// MusicListAdapter.java
public class MusicListAdapter extends ArrayAdapter<Music> {

        private LayoutInflater inflater;
        private MusicPlayer musicPlayer; // Adicionando uma variável de instância para o MusicPlayer

        private boolean isGridView; // Adiciona um novo campo para indicar se é um GridView

        public MusicListAdapter(@NonNull Context context, @NonNull List<Music> objects, MusicPlayer musicPlayer, boolean isGridView) {
            super(context, 0, objects);
            inflater = LayoutInflater.from(context);
            this.musicPlayer = musicPlayer;
            this.isGridView = isGridView; // Inicializa o campo isGridView
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        ViewHolder holder;

        if (itemView == null) {
            itemView = inflater.inflate(isGridView ? R.layout.grid_item_layout : R.layout.list_item_layout, parent, false);
            holder = new ViewHolder();
            holder.imageView = itemView.findViewById(R.id.image_view);
            holder.textView = itemView.findViewById(R.id.text_view);

            if (!isGridView) { // Se for um ListView, configure o botão
                holder.button = itemView.findViewById(R.id.button);
                holder.button.setOnClickListener(v -> {
                    int pos = (int) v.getTag();
                    Music selectedMusic = getItem(pos);
                    musicPlayer.play(selectedMusic);
                });
            }

            itemView.setTag(holder);

            Music currentMusic = getItem(position);
            setupListItemClick(itemView, currentMusic);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }

        Music currentMusic = getItem(position);

        if (currentMusic != null) {

            holder.imageView.setImageResource(currentMusic.getPhotoId());

            if (!isGridView) { // Se for um ListView, configure o botão
                holder.textView.setText(currentMusic.getName());
                holder.button.setTag(position);
            }
        }

        return itemView;
    }


    public Music getCurrentMusic(int position) {
            Log.d("Sla", getItem(position).getName());
        return getItem(position);
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
        Button button; // Adiciona um campo para o botão
    }
    // Adicione este método à sua classe MusicListAdapter
    private void setupListItemClick(View itemView, Music selectedMusic) {
        itemView.setOnClickListener(v -> {
            if (musicPlayer.getCurrentMusic() == selectedMusic && musicPlayer.isPlaying()) {
                // Se a mesma música estiver tocando e já estiver reproduzindo, pause-a
                musicPlayer.pause();
            } else {
                // Caso contrário, reproduza a música selecionada
                musicPlayer.play(selectedMusic);
            }
        });
    }

}

