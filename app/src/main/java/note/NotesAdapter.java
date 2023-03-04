package note;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.audionote.BeforeAppStart;
import com.example.audionote.R;

import java.util.List;

import UiScreen.description.NoteDescription;
import model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesHolder> {

    private SortedList<Note> noteSortedList;

    public NotesAdapter() {
        noteSortedList = new SortedList<>(Note.class, new SortedList.Callback<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return 0;
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Note oldItem, Note newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Note item1, Note item2) {
                return item1.specialID == item2.specialID;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_notes_recycler_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
        holder.bind(noteSortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteSortedList.size();
    }

    public void updateItems(List<Note> notes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            noteSortedList.replaceAll((notes));
        }
    }

    public class NotesHolder extends RecyclerView.ViewHolder {

        private TextView yourThoughts;
        private ImageButton deleteButton;

        Note note;

        public NotesHolder(@NonNull View itemView) {
            super(itemView);

            yourThoughts = itemView.findViewById(R.id.yourThoughts);
            deleteButton = itemView.findViewById(R.id.deleteThoughts);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteDescription.start((Activity) itemView.getContext(), note);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BeforeAppStart.getInstance().getNoteDao().delete(note);
                }
            });
        }

        public void bind(Note note) {
            this.note = note;
            yourThoughts.setText(note.noteText);
        }
    }
}
