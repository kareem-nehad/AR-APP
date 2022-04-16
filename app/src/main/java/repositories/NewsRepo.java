package repositories;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.NewsModel;
import okhttp3.ResponseBody;
import retrofit.ApiInterface;
import retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {

    public MutableLiveData<List<NewsModel>> requestNews() {
        final MutableLiveData<List<NewsModel>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        apiInterface.getAllNews().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response != null) {
                    List<NewsModel> tempList = new ArrayList<>();

                    try {
                        String json = response.body().string();
                        System.out.println(json);
                        System.out.println("response is successful");
                        JSONArray array = new JSONArray(json);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = new JSONObject(String.valueOf(array.getJSONObject(i)));

                            String id = object.getString("id");
                            String image = object.getString("image");
                            String title = object.getString("title");
                            String subTitle = object.getString("sybTitle");
                            String body = object.getString("body");
                            String date = object.getString("date");
                            tempList.add(new NewsModel(id,image,title,subTitle,body,date));
                        }

                        mutableLiveData.setValue(tempList);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Empty response");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
