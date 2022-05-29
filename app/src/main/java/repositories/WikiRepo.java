package repositories;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.WikiModel;
import okhttp3.ResponseBody;
import retrofit.ApiInterface;
import retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WikiRepo {

    public MutableLiveData<List<WikiModel>> requestWikis() {
        final MutableLiveData<List<WikiModel>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        apiInterface.getAllWiki().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response != null) {
                    List<WikiModel> tempList = new ArrayList<>();

                    try {
                        String json = response.body().string();
                        System.out.println(json);
                        System.out.println("response is successful");
                        JSONArray array = new JSONArray(json);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = new JSONObject(String.valueOf(array.getJSONObject(i)));

                            String image = object.getString("image");
                            String title = object.getString("title");
                            String id = object.getString("id");

                            tempList.add(new WikiModel(id,title,image));
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

    public MutableLiveData<WikiModel> requestSelectedWiki(String id) {
        final MutableLiveData<WikiModel> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        apiInterface.getSelectedWiki(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                WikiModel tempModel = new WikiModel();
                if (response.isSuccessful() && response.isSuccessful()) {
                    try {
                        String json = response.body().string();
                        System.out.println(json);
                        JSONObject object = new JSONObject(json);

                        String image = object.getString("image");
                        String model = object.getString("model");
                        String title = object.getString("title");
                        String category = object.getString("cat");
                        String body = object.getString("body");
                        String id = object.getString("id");

                        tempModel = new WikiModel(title,image,model,category,body);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    mutableLiveData.setValue(tempModel);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
