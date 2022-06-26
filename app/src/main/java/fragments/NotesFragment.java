package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.helloar.R;

import java.util.List;

import adapters.NotesAdapter;
import models.Note;
import viewmodels.NotesViewModel;

public class NotesFragment extends Fragment {

    public NotesFragment() {
        // Required empty public constructor
    }

    private NotesViewModel notesViewModel;
    ImageView add, back;
    RecyclerView notesRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notes, container, false);
        getViews(v);

        notesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        notesRecycler.setHasFixedSize(true);
        notesRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));


        NotesAdapter adapter = new NotesAdapter(this);
        notesRecycler.setAdapter(adapter);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
                System.out.println(notes);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFragment addFragment = new AddFragment();
                addFragment.show(getParentFragmentManager(),"AddFragmentSheet");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentsContainer,new HomeFragment()).commit();
            }
        });

        return v;
    }

    private void getViews(View v) {
        back = v.findViewById(R.id.back_from_notes);
        add = v.findViewById(R.id.addNote);
        notesRecycler = v.findViewById(R.id.notesRecycler);
    }
}