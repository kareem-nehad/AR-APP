package retrofit;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient extends Application {
    private static RetrofitClient mInstance;
    private static Retrofit retrofit = null;
    private static String BASE_URL = "http://143.110.151.110:5100/";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public synchronized RetrofitClient getmInstance() {
        return mInstance;
    }

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            okhttp3.OkHttpClient client = new OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }

        return retrofit;
    }
}
