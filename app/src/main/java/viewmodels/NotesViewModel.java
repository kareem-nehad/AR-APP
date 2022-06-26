package viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import models.Note;
import repositories.NotesRepo;

public class NotesViewModel extends AndroidViewModel {

    private NotesRepo repo;
    private LiveData<List<Note>> notes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        repo = new NotesRepo(application);
        notes = repo.getAllNotes();
    }

    public void insert (Note note) {
        repo.insert(note);
    }
    public void update (Note note) {
        repo.update(note);
    }
    public void delete (Note note) {
        repo.delete(note);
    }
    public void deleteAll () {
        repo.deleteAll();
    }
    public LiveData<List<Note>> getAllNotes() {
        return notes;
    }
}
