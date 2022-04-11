package fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.app.helloar.R;

import java.util.List;

import adapters.NewsAdapter;
import models.NewsModel;
import viewmodels.NewsViewModel;

public class NewsFragment extends Fragment {

    RecyclerView newsRecycler;
    ImageView drawer;
    View view;
    NewsAdapter adapter;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);
        getViews();

        newsRecycler.setHasFixedSize(true);
        newsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NewsAdapter();
        newsRecycler.setAdapter(adapter);

        NewsViewModel newsViewModel = new NewsViewModel();
        newsViewModel.getNews().observe(getActivity(), new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                adapter.getNewsList(newsModels);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void getViews() {
        newsRecycler = view.findViewById(R.id.newsRecycler);
        drawer = view.findViewById(R.id.news_drawer);
    }
}