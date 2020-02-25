package it.danieltrosko.lsauto;

import it.danieltrosko.lsauto.pojo.LoginModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/api/authenticate")
    Call<LoginModel> login(@Body LoginModel loginModel);
}
