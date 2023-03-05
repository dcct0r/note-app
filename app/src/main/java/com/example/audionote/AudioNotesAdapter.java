package com.example.audionote;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class AudioNotesAdapter extends RecyclerView.Adapter<AudioNotesAdapter.AudioNoteHolder> {

    private File[] f;
    private LastRecord lastRecord;
    private onPlayButtonClick playButton;

    public AudioNotesAdapter(File[] f, onPlayButtonClick playButton) {
        this.f = f;
        this.playButton = playButton;
    }

    @NonNull
    @Override
    public AudioNoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_audionotes_recycler_list, parent, false);
        lastRecord = new LastRecord();
        return new AudioNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioNoteHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.headline.setText(f[position].getName());
        holder.dataOfTheRec.setText(lastRecord.getLastRecord(f[position].lastModified()));
    }

    @Override
    public int getItemCount() {
        return f.length;
    }

    public class AudioNoteHolder extends RecyclerView.ViewHolder {

        TextView headline;
        TextView dataOfTheRec;
        ImageButton onPlayOnStop;
        ImageButton deleteThoughts;

        public AudioNoteHolder(@NonNull View itemView) {
            super(itemView);
            headline = itemView.findViewById(R.id.user_title_note);
            dataOfTheRec = itemView.findViewById(R.id.data_of_the_note);
            onPlayOnStop = itemView.findViewById(R.id.play_or_pause);
            deleteThoughts = itemView.findViewById(R.id.deleteCurrentAudioNote);

            deleteThoughts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    f[getAdapterPosition()].delete();
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemChanged(getAdapterPosition());
                    notifyItemRangeRemoved(getAdapterPosition(), f.length);
                    notifyItemRangeChanged(getAdapterPosition(), f.length);
                }
            });
            onPlayOnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playButton.onClickPlayButton(f[getAdapterPosition()], getAdapterPosition());
                }
            });
        }
    }
    public interface onPlayButtonClick {
        void onClickPlayButton(File file, int position);
    }
}
