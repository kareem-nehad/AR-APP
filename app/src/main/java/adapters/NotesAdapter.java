package adapters;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.helloar.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import fragments.AddFragment;
import fragments.NotesFragment;
import models.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Note> notes = new ArrayList<>();
    NotesFragment notesFragment;

    public NotesAdapter(NotesFragment notesFragment) {
        this.notesFragment = notesFragment;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note currentNote = notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.text.setText(currentNote.getNote());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] bitmap = currentNote.getImage();

                AddFragment addFragment = new AddFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", currentNote.getTitle());
                bundle.putString("text", currentNote.getNote());
                bundle.putByteArray("image", bitmap);
                bundle.putInt("id",currentNote.getId());
                addFragment.setArguments(bundle);
                addFragment.show(notesFragment.getChildFragmentManager(), "AddFragmentSheet");
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes (List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt (int position) {
        return notes.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.adapterTitle);
            text = itemView.findViewById(R.id.adapterText);
        }
    }
}
