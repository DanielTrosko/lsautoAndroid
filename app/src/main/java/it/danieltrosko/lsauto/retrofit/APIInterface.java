package it.danieltrosko.lsauto.retrofit;

import java.util.ArrayList;
import java.util.List;

import it.danieltrosko.lsauto.model.Car;
import it.danieltrosko.lsauto.model.CarAcceptance;
import it.danieltrosko.lsauto.model.LoginModel;
import it.danieltrosko.lsauto.model.Token;
import it.danieltrosko.lsauto.pojo.CarPojo;
import it.danieltrosko.lsauto.pojo.ShowActualRepairPojo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/api/authenticate")
    Call<Token> login(@Body LoginModel loginModel);

    @Headers("Accept: application/json")
    @GET("/api/car/getallcars")
    Call<ArrayList<CarPojo>> getAllCars(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @GET("/api/repair/getactualrepaircars")
    Call<ArrayList<ShowActualRepairPojo>> getActualRepairCars(@Header("Authorization") String token);


    @POST("/api/repair/addnewcaracceptance")
    Call<ResponseBody> addNewCarAcceptance(@Header("Authorization") String token, @Body CarAcceptance carAcceptance);
}
