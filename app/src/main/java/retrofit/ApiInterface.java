package retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v1/news/get_all")
    Call<ResponseBody> getAllNews();
}
