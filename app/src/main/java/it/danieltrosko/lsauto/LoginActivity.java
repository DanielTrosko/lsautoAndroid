package it.danieltrosko.lsauto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.danieltrosko.lsauto.pojo.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    TextView code;
    Retrofit retrofit;
    APIInterface apiInterface;
    LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        code = findViewById(R.id.code);

        loginButton.setOnClickListener(v -> {
            //
            loginModel = new LoginModel(email.getText().toString(), password.getText().toString());
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            apiInterface = retrofit.create(APIInterface.class);

            Call<LoginModel> login = apiInterface.login(loginModel);
            login.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                    if (response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Logged in ", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(main);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email or password is not correct", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT).show();
                }
            });
            //
        });


//

//

    }
}
