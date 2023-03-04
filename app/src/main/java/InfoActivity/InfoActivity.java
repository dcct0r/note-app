package InfoActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.audionote.MainActivity;
import com.example.audionote.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import note.NotesActivity;

public class InfoActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);
        getSupportActionBar().hide();
        switchingActivities();
    }

    private void switchingActivities(){
        bottomNavigationView = findViewById(R.id.bottomNavPanel);
        bottomNavigationView.setSelectedItemId(R.id.nav_settings);
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
                    getWindow().setEnterTransition(null);
                    startActivity(new Intent(getApplicationContext(), NotesActivity.class));
                    overridePendingTransition(0, 0);
                    getWindow().setExitTransition(null);
                    finish();
                    return true;
                case R.id.nav_settings:
                    return true;
            }
            return false;
        });
    }
}
