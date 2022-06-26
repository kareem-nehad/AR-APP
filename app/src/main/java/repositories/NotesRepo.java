package repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import database.Database;
import database.NoteDao;
import models.Note;

public class NotesRepo {

    private NoteDao notesDao;
    private LiveData<List<Note>> allNotes;

    public NotesRepo (Application application) {
        Database database = Database.getInstance(application);
        notesDao = database.noteDao();
        allNotes = notesDao.getAllNotes();
    }

    public void insert (Note note) {
        new InsertNoteAsyncTask(notesDao).execute(note);
    }
    public void update(Note note) {
        new UpdateNoteAsyncTask(notesDao).execute(note);
    }
    public void delete (Note note) {
        new DeleteNoteAsyncTask(notesDao).execute(note);
    }
    public void deleteAll() {
        new DeleteAllNoteAsyncTask(notesDao).execute();
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void> {

        private NoteDao notesDao;

        public InsertNoteAsyncTask(NoteDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao notesDao;

        public UpdateNoteAsyncTask(NoteDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao notesDao;

        public DeleteNoteAsyncTask(NoteDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao notesDao;

        public DeleteAllNoteAsyncTask(NoteDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.deleteAll();
            return null;
        }
    }
}
