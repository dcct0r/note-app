package model.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import model.Note;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Note> __insertionAdapterOfNote;

  private final EntityDeletionOrUpdateAdapter<Note> __deletionAdapterOfNote;

  private final EntityDeletionOrUpdateAdapter<Note> __updateAdapterOfNote;

  public NoteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNote = new EntityInsertionAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Note` (`specialID`,`noteText`,`dataOfNote`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.specialID);
        if (value.noteText == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.noteText);
        }
        stmt.bindLong(3, value.dataOfNote);
      }
    };
    this.__deletionAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Note` WHERE `specialID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.specialID);
      }
    };
    this.__updateAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Note` SET `specialID` = ?,`noteText` = ?,`dataOfNote` = ? WHERE `specialID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.specialID);
        if (value.noteText == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.noteText);
        }
        stmt.bindLong(3, value.dataOfNote);
        stmt.bindLong(4, value.specialID);
      }
    };
  }

  @Override
  public void insert(final Note note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNote.insert(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Note note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNote.handle(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Note note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfNote.handle(note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Note> getAll() {
    final String _sql = "SELECT * FROM Note";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSpecialID = CursorUtil.getColumnIndexOrThrow(_cursor, "specialID");
      final int _cursorIndexOfNoteText = CursorUtil.getColumnIndexOrThrow(_cursor, "noteText");
      final int _cursorIndexOfDataOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "dataOfNote");
      final List<Note> _result = new ArrayList<Note>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Note _item;
        _item = new Note();
        _item.specialID = _cursor.getInt(_cursorIndexOfSpecialID);
        if (_cursor.isNull(_cursorIndexOfNoteText)) {
          _item.noteText = null;
        } else {
          _item.noteText = _cursor.getString(_cursorIndexOfNoteText);
        }
        _item.dataOfNote = _cursor.getLong(_cursorIndexOfDataOfNote);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Note>> getAllLiveData() {
    final String _sql = "SELECT * FROM Note";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Note"}, false, new Callable<List<Note>>() {
      @Override
      public List<Note> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSpecialID = CursorUtil.getColumnIndexOrThrow(_cursor, "specialID");
          final int _cursorIndexOfNoteText = CursorUtil.getColumnIndexOrThrow(_cursor, "noteText");
          final int _cursorIndexOfDataOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "dataOfNote");
          final List<Note> _result = new ArrayList<Note>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Note _item;
            _item = new Note();
            _item.specialID = _cursor.getInt(_cursorIndexOfSpecialID);
            if (_cursor.isNull(_cursorIndexOfNoteText)) {
              _item.noteText = null;
            } else {
              _item.noteText = _cursor.getString(_cursorIndexOfNoteText);
            }
            _item.dataOfNote = _cursor.getLong(_cursorIndexOfDataOfNote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Note> loadAllByIds(final int[] noteIds) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM Note WHERE specialID IN (");
    final int _inputSize = noteIds.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int _item : noteIds) {
      _statement.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSpecialID = CursorUtil.getColumnIndexOrThrow(_cursor, "specialID");
      final int _cursorIndexOfNoteText = CursorUtil.getColumnIndexOrThrow(_cursor, "noteText");
      final int _cursorIndexOfDataOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "dataOfNote");
      final List<Note> _result = new ArrayList<Note>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Note _item_1;
        _item_1 = new Note();
        _item_1.specialID = _cursor.getInt(_cursorIndexOfSpecialID);
        if (_cursor.isNull(_cursorIndexOfNoteText)) {
          _item_1.noteText = null;
        } else {
          _item_1.noteText = _cursor.getString(_cursorIndexOfNoteText);
        }
        _item_1.dataOfNote = _cursor.getLong(_cursorIndexOfDataOfNote);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Note findById(final int specialID) {
    final String _sql = "SELECT * FROM Note WHERE specialID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, specialID);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSpecialID = CursorUtil.getColumnIndexOrThrow(_cursor, "specialID");
      final int _cursorIndexOfNoteText = CursorUtil.getColumnIndexOrThrow(_cursor, "noteText");
      final int _cursorIndexOfDataOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "dataOfNote");
      final Note _result;
      if(_cursor.moveToFirst()) {
        _result = new Note();
        _result.specialID = _cursor.getInt(_cursorIndexOfSpecialID);
        if (_cursor.isNull(_cursorIndexOfNoteText)) {
          _result.noteText = null;
        } else {
          _result.noteText = _cursor.getString(_cursorIndexOfNoteText);
        }
        _result.dataOfNote = _cursor.getLong(_cursorIndexOfDataOfNote);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
