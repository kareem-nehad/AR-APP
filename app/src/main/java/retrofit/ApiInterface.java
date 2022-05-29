package retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("v1/news/get_all")
    Call<ResponseBody> getAllNews();

    @GET ("v1/news/{id}")
    Call<ResponseBody> getSelectedNews(
            @Path("id") String id
    );

    @GET("v1/wiki/get_all")
    Call<ResponseBody> getAllWiki();

    @GET("v1/wiki/{id}")
    Call<ResponseBody> getSelectedWiki(
            @Path("id") String id
    );
}
