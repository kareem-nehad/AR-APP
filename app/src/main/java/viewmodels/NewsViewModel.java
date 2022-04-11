package viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import models.NewsModel;
import repositories.NewsRepo;

public class NewsViewModel extends ViewModel {
    private NewsRepo newsRepo;
    private MutableLiveData<List<NewsModel>> mutableLiveData;

    public NewsViewModel() {
        this.newsRepo = new NewsRepo();
    }

    public LiveData<List<NewsModel>> getNews() {
        if (mutableLiveData == null) {
            mutableLiveData = newsRepo.requestNews();
        }

        return mutableLiveData;
    }

}
