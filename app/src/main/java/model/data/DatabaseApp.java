package model.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract NoteDao noteDao();
}
