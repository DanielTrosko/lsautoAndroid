package it.danieltrosko.lsauto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import it.danieltrosko.lsauto.model.LoginModel;
import it.danieltrosko.lsauto.model.Token;
import it.danieltrosko.lsauto.retrofit.APIInterface;
import it.danieltrosko.lsauto.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    LoginModel loginModel;
    final LoadingDIalog loadingDIalog = new LoadingDIalog(LoginActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            loadingDIalog.start();


            loginModel = new LoginModel(email.getText().toString(), password.getText().toString());

            APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);

            Call<Token> login = apiInterface.login(loginModel);
            login.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    if (response.isSuccessful() & response.code() == 200) {
                        assert response.body() != null;
                        SharedPreferences.Editor editor = getSharedPreferences("lsauto", MODE_PRIVATE).edit();
                        editor.putString("token", response.body().getToken());
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "Logged in ", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(main);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email or password is not correct", Toast.LENGTH_SHORT).show();
                    }
                    loadingDIalog.dismiss();
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT).show();
                    loadingDIalog.dismiss();
                }
            });

        });
    }
}
