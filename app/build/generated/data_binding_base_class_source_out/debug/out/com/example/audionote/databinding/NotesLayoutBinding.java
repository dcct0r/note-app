// Generated by view binder compiler. Do not edit!
package com.example.audionote.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.audionote.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NotesLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavPanel;

  @NonNull
  public final ImageButton newNoteThoughts;

  @NonNull
  public final RecyclerView notesList;

  @NonNull
  public final TextView titleNotes;

  private NotesLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull BottomNavigationView bottomNavPanel, @NonNull ImageButton newNoteThoughts,
      @NonNull RecyclerView notesList, @NonNull TextView titleNotes) {
    this.rootView = rootView;
    this.bottomNavPanel = bottomNavPanel;
    this.newNoteThoughts = newNoteThoughts;
    this.notesList = notesList;
    this.titleNotes = titleNotes;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NotesLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NotesLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.notes_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NotesLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomNavPanel;
      BottomNavigationView bottomNavPanel = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavPanel == null) {
        break missingId;
      }

      id = R.id.newNoteThoughts;
      ImageButton newNoteThoughts = ViewBindings.findChildViewById(rootView, id);
      if (newNoteThoughts == null) {
        break missingId;
      }

      id = R.id.notesList;
      RecyclerView notesList = ViewBindings.findChildViewById(rootView, id);
      if (notesList == null) {
        break missingId;
      }

      id = R.id.titleNotes;
      TextView titleNotes = ViewBindings.findChildViewById(rootView, id);
      if (titleNotes == null) {
        break missingId;
      }

      return new NotesLayoutBinding((ConstraintLayout) rootView, bottomNavPanel, newNoteThoughts,
          notesList, titleNotes);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}