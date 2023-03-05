package com.example.audionote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import note.NotesActivity;
import InfoActivity.InfoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AudioNotesAdapter.onPlayButtonClick{

    private final int REQUEST_CODE = 300; //code to get access for recording
    private ImageButton recordButton, refreshButton;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private boolean isRecording = false;
    private boolean isStarted = false;
    private String recordAccess = Manifest.permission.RECORD_AUDIO;
    private MediaRecorder mediaRecorder;
    private String recordFileName;
    private File[] fileCatalog;
    private AudioNotesAdapter audioNotesAdapter;
    private File currentFilePlaying;
    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialise();
        getSupportActionBar().hide();
        switchingActivities();

        String path = MainActivity.this.getExternalFilesDir("/").getAbsolutePath();
        File direct = new File(path);
        fileCatalog = direct.listFiles();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        DividerItemDecoration divider = new DividerItemDecoration(
                recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(
                ContextCompat.getDrawable(getBaseContext(), R.drawable.recycler_vertical_spacing));
        recyclerView.addItemDecoration(divider);

        recyclerView.setLayoutManager(linearLayoutManager);
        audioNotesAdapter = new AudioNotesAdapter(fileCatalog, this);
        recyclerView.setAdapter(audioNotesAdapter);

        refreshButton.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

        onRecordAction(recordButton);

    }

    private void initialise() {
        recyclerView = findViewById(R.id.audioNotesList);
        recordButton = findViewById(R.id.startRecording);
        refreshButton = findViewById(R.id.refreshThat);
    }

    private void switchingActivities(){
        bottomNavigationView = findViewById(R.id.bottomNavPanel);
        bottomNavigationView.setSelectedItemId(R.id.nav_audio_notes);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.nav_audio_notes:
                    return true;
                case R.id.nav_notes:
                    getWindow().setEnterTransition(null);
                    startActivity(new Intent(getApplicationContext(), NotesActivity.class));
                    overridePendingTransition(0, 0);
                    getWindow().setExitTransition(null);
                    finish();
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

    private void onRecordAction(@NonNull ImageButton imageButton) {
        imageButton.setOnClickListener(v -> {
            if(!isRecording) {
                //Recording started
                if(allowToMic()) {
                    startRecording();
                    imageButton.setBackgroundResource(R.color.white);
                    imageButton.setImageResource(R.drawable.pause_48);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Recording!", Toast.LENGTH_SHORT);
                    toast.show();
                    isRecording = true;
                }
            } else {
                //Recording stopped
                stopRecording();
                imageButton.setBackgroundResource(R.drawable.circle_shape);
                imageButton.setImageResource(R.drawable.mic_24);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Stopped!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void stopRecording() {
        isRecording = false;
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void startRecording() {
        String recordPath = MainActivity.this.getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat dataOfRecording = new SimpleDateFormat("hhmmss", Locale.getDefault());
        Date currentTime = new Date();
        recordFileName = "AudioNote" + dataOfRecording.format(currentTime) + ".aac";

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        mediaRecorder.setOutputFile(recordPath + "/" + recordFileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mediaRecorder.start();
    }

    private boolean allowToMic() {
        if(ActivityCompat.checkSelfPermission(this, recordAccess) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{recordAccess}, REQUEST_CODE);
            return false;
        }
    }

    @Override
    public void onClickPlayButton(File file, int position) {
       if(isStarted) {
           stopAudioNote();
           Log.d("PLAY", "STOP");
       }
       else {
           Log.d("PLAY", "START FUCKING PLAY");
           currentFilePlaying = file;
           playAudioNote(currentFilePlaying);
       }
    }

    private void stopAudioNote() {
        isStarted = false;
        mediaPlayer.stop();
    }

    private void playAudioNote(File currentFilePlaying) {

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(currentFilePlaying.getAbsolutePath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isStarted = true;
        mediaPlayer.setOnPreparedListener(mp -> mp.start());
    }
}