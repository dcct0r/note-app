package model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int specialID;

    @ColumnInfo(name = "noteText")
    public String noteText;

    @ColumnInfo(name = "dataOfNote")
    public long dataOfNote;

    public Note() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return specialID == note.specialID && dataOfNote == note.dataOfNote && Objects.equals(noteText, note.noteText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialID, noteText, dataOfNote);
    }

    protected Note(Parcel in) {
        specialID = in.readInt();
        noteText = in.readString();
        dataOfNote = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(specialID);
        dest.writeString(noteText);
        dest.writeLong(dataOfNote);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

}
