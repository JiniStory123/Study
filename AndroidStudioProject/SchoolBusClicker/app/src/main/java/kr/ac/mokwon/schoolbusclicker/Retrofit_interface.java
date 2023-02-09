package kr.ac.mokwon.schoolbusclicker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Retrofit_interface {

    @GET("posts/{UserID}")
    Call<Retrofit_Data_model_BusDB> test_api_get(
            @Path("UserID") String userid);
}
