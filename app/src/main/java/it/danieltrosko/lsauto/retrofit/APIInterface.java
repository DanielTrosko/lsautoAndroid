package it.danieltrosko.lsauto.retrofit;

import it.danieltrosko.lsauto.model.LoginModel;
import it.danieltrosko.lsauto.model.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/api/authenticate")
    Call<Token> login(@Body LoginModel loginModel);
}
