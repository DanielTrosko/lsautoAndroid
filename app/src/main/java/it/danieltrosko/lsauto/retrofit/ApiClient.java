package it.danieltrosko.lsauto.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl("https://51.178.52.250:443")
//                .baseUrl("https://10.0.2.2:443")
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
