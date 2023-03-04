package UiScreen.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.audionote.BeforeAppStart;
import java.util.List;
import model.Note;

public class MainViewModel extends ViewModel {
    private LiveData<List<Note>> noteLiveData = BeforeAppStart.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }
}
