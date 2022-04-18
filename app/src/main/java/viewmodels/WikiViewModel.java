package viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import models.WikiModel;
import repositories.WikiRepo;

public class WikiViewModel {
    private WikiRepo wikiRepo;
    private MutableLiveData<List<WikiModel>> mutableLiveData;

    public WikiViewModel () {
        this.wikiRepo = new WikiRepo();
    }

    public LiveData<List<WikiModel>> getWikis() {
        if (mutableLiveData == null) {
            mutableLiveData = wikiRepo.requestWikis();
        }

        return mutableLiveData;
    }
}
