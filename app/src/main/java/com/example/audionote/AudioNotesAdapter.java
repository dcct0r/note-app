package com.example.audionote;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;


public class AudioNotesAdapter extends RecyclerView.Adapter<AudioNotesAdapter.AudioNoteHolder> {

    private List<File> f;
    private LastRecord lastRecord;
    private onPlayButtonClick playButton;
    private boolean isListing = false;

    public interface onPlayButtonClick {
        void onClickPlayButton(File file, int position);
    }
    public AudioNotesAdapter(List<File> f, onPlayButtonClick playButton) {
        this.f = f;
        this.playButton = playButton;
    }

    @NonNull
    @Override
    public AudioNoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_audionotes_recycler_list, parent, false);
        return new AudioNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioNoteHolder holder, @SuppressLint("RecyclerView") int position) {
        lastRecord = new LastRecord();
        holder.headline.setText(f.get(position).getName());
        holder.dataOfTheRec.setText(lastRecord.getLastRecord(f.get(position).lastModified()));

        holder.onPlayOnStop.setOnClickListener(v -> {
            if(isListing) {
                holder.onPlayOnStop.setImageResource(R.drawable.pause_48);
                isListing = false;
            }
            else {
                holder.onPlayOnStop.setImageResource(R.drawable.play_48);
                isListing = true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return f.size();
    }

    public class AudioNoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            deleteThoughts.setOnClickListener(v -> {
                f.get(getAdapterPosition()).delete();
                notifyItemRemoved(getAdapterPosition());
            });

            onPlayOnStop.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            playButton.onClickPlayButton(f.get(getAdapterPosition()), getAdapterPosition());
        }
    }
}
