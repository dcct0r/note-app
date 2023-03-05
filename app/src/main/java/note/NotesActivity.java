package note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audionote.R;
import InfoActivity.InfoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import com.example.audionote.MainActivity;
import UiScreen.description.NoteDescription;
import UiScreen.main.MainViewModel;
import model.Note;

public class NotesActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_layout);
        switchingActivities();

        recyclerView = findViewById(R.id.notesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        NotesAdapter notesAdapter = new NotesAdapter();
        recyclerView.setAdapter(notesAdapter);

        imageButton = findViewById(R.id.newNoteThoughts);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteDescription.start(NotesActivity.this, null);
            }
        });

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getNoteLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.updateItems(notes);
            }
        });
    }

    private void switchingActivities(){
        bottomNavigationView = findViewById(R.id.bottomNavPanel);
        bottomNavigationView.setSelectedItemId(R.id.nav_notes);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.nav_audio_notes:
                    getWindow().setEnterTransition(null);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    getWindow().setExitTransition(null);
                    finish();
                    return true;
                case R.id.nav_notes:
                    return true;
                case R.id.nav_settings:
                    getWindow().setEnterTransition(null);
                    startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                    overridePendingTransition(0, 0);
                    getWindow().setExitTransition(null);
                    finish();
                    return true;
            }
            return false;
        });
    }
}
