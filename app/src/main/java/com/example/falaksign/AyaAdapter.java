package com.example.falaksign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AyaAdapter extends RecyclerView.Adapter<AyaAdapter.ViewHolder> {

    Context context;
    List<Aya> ayaList;

    MediaPlayer mediaPlayer;

    //onCompletionListener method
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            mediaPlayer = null;
        }
    };

    public AyaAdapter(Context context, List<Aya> ayaList) {
        this.context = context;
        this.ayaList = ayaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.aya_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Aya aya = ayaList.get(position);

        holder.ayaTextTV.setText(aya.getAyaText() + "");

        holder.startAyaSoundIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = null;
                mediaPlayer = MediaPlayer.create(context, ayaList.get(position).getAyaSound());
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                    holder.startAyaSoundIV.setImageDrawable(context.getResources().getDrawable(R.drawable.play));
                } else {
                    mediaPlayer.pause();
                    holder.startAyaSoundIV.setImageDrawable(context.getResources().getDrawable(R.drawable.pause));
                }
            }
        });

        holder.stopAyaSoundIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    holder.startAyaSoundIV.setImageDrawable(context.getResources().getDrawable(R.drawable.pause));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ayaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView startAyaSoundIV, stopAyaSoundIV;
        TextView ayaTextTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            startAyaSoundIV = itemView.findViewById(R.id.startAyaSoundIV);
            stopAyaSoundIV = itemView.findViewById(R.id.stopAyaSoundIV);
            ayaTextTV = itemView.findViewById(R.id.ayaTextTV);

        }
    }
}
