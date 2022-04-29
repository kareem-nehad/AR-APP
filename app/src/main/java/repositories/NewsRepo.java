package repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.internal.bind.util.ISO8601Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
            @RequiresApi(api = Build.VERSION_CODES.O)
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
                            String date = object.getString("date");
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            Date d = format.parse(date);
                            System.out.println(d.toString());
                            tempList.add(new NewsModel(id,image,title,subTitle, date));
                        }

                        mutableLiveData.setValue(tempList);
                    } catch (IOException | JSONException | ParseException e) {
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
