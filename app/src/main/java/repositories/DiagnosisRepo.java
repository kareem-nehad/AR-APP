package repositories;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit.ApiInterface;
import retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosisRepo {

    public MutableLiveData<Double> Retinopathy(float age, float systolic, float diastolic, float cholestrol) {
        final MutableLiveData<Double> mutableLiveData = new MutableLiveData<>();
        ArrayList<Float> values = new ArrayList<>();
        values.add(Float.valueOf(age));
        values.add(Float.valueOf(systolic));
        values.add(Float.valueOf(diastolic));
        values.add(Float.valueOf(cholestrol));

        Map<String, ArrayList<Float>> map = new HashMap<>();
        map.put("array", values);

        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        apiInterface.checkRetinopathy(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    System.out.println("Successful Response");
                    try {
                        String json = response.body().string();
                        JSONObject object = new JSONObject(json);
                        double result = (double) object.get("message");
                        mutableLiveData.setValue(result);
                        System.out.println(result);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(response.code());
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t);
            }

        });

        return mutableLiveData;
    }

    public MutableLiveData<Double> DeathEstimator(float age, float systolic, float diastolic, float cholestrol, float poverty, float race, float redBloodCells, float sedimentation, float albumin, float iron, float magnesium, float protein, float gender, float TIBC, float TS, float whiteBloodCells, float BMI, float pulse) {
        final MutableLiveData<Double> mutableLiveData = new MutableLiveData<>();

        ArrayList<Float> values = new ArrayList<>();
        values.add(age);
        values.add(systolic);
        values.add(diastolic);
        values.add(cholestrol);
        values.add(poverty);
        values.add(race);
        values.add(redBloodCells);
        values.add(sedimentation);
        values.add(albumin);
        values.add(iron);
        values.add(magnesium);
        values.add(protein);
        values.add(gender);
        values.add(TIBC);
        values.add(TS);
        values.add(whiteBloodCells);
        values.add(BMI);
        values.add(pulse);

        Map<String, ArrayList<Float>> map = new HashMap<>();
        map.put("array", values);

        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        apiInterface.checkEstimator(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    System.out.println("Successful Response");
                    try {
                        String json = response.body().string();
                        JSONObject object = new JSONObject(json);
                        double result = (double) object.get("message");
                        mutableLiveData.setValue(result);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

}
