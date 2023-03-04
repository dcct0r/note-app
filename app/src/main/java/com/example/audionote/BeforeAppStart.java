package com.example.audionote;

import android.app.Application;

import androidx.room.Room;

import model.data.DatabaseApp;
import model.data.NoteDao;

public class BeforeAppStart extends Application {

    private NoteDao noteDao;
    private DatabaseApp databaseApp;
    private static BeforeAppStart instance;
    public static BeforeAppStart getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        databaseApp = Room.databaseBuilder(getApplicationContext(),
                DatabaseApp.class, "app-note-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        noteDao = databaseApp.noteDao();

    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public DatabaseApp getDatabaseApp() {
        return databaseApp;
    }

    public void setDatabaseApp(DatabaseApp databaseApp) {
        this.databaseApp = databaseApp;
    }
}
