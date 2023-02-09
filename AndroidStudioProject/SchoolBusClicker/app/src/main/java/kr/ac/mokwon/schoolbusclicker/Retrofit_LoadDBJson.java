package kr.ac.mokwon.schoolbusclicker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_LoadDBJson
{
    private static final String BASE_URL = "https://as8794.cafe24.com/bus_clicker/loadDBJson.php";

    public static Retrofit_interface getApiService()
    {
        return getInstance().create(Retrofit_interface.class);
    }

    private static Retrofit getInstance()
    {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
