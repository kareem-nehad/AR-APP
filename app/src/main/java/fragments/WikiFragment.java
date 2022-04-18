package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.helloar.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import adapters.WikiAdapter;
import models.WikiModel;
import viewmodels.WikiViewModel;

public class WikiFragment extends Fragment {

    RecyclerView wikiRecycler;
    ImageView drawer;
    TextView date;
    WikiAdapter adapter;
    ProgressBar progressBar;

    public WikiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wiki, container, false);
        getViews(view);

        // Recycler view settings
        wikiRecycler.setHasFixedSize(true);
        wikiRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));

        adapter = new WikiAdapter();
        wikiRecycler.setAdapter(adapter);

        // ViewModel implementation
        WikiViewModel wikiViewModel = new WikiViewModel();
        wikiViewModel.getWikis().observe(getActivity(), new Observer<List<WikiModel>>() {
            @Override
            public void onChanged(List<WikiModel> wikiModels) {
                progressBar.setVisibility(View.GONE);
                adapter.getWikiList(wikiModels);
                adapter.notifyDataSetChanged();
            }
        });

        // Date
        Calendar calendar = Calendar.getInstance();
        date.setText(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault())+" "+ Calendar.getInstance().get(Calendar.YEAR));

        return view;
    }

    private void getViews(View view) {
        wikiRecycler = view.findViewById(R.id.wikiRecycler);
        drawer = view.findViewById(R.id.wiki_drawer);
        date = view.findViewById(R.id.wiki_date);
        progressBar = view.findViewById(R.id.wiki_progress);
    }
}