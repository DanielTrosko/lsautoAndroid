package it.danieltrosko.lsauto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

import it.danieltrosko.lsauto.model.CarAcceptance;
import it.danieltrosko.lsauto.retrofit.APIInterface;
import it.danieltrosko.lsauto.retrofit.UnsafeOkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarAcceptanceForm extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_acceptance_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText mark = findViewById(R.id.carAcceptanceMarkEditText);
        EditText model = findViewById(R.id.carAcceptanceModelEditText);
        EditText year = findViewById(R.id.carAcceptanceYearEditText);
        EditText plateNumber = findViewById(R.id.carAcceptancePLateNumberEditText);
        EditText chassisNumber = findViewById(R.id.carAcceptanceChassisNumberEditText);
        EditText meterReading = findViewById(R.id.carAcceptanceMeterReadingEditText);
        EditText email = findViewById(R.id.carAcceptanceEmailEditText);
        EditText name = findViewById(R.id.carAcceptanceNameEditText);
        EditText surname = findViewById(R.id.carAcceptanceSurnameEditText);
        EditText phoneNumber = findViewById(R.id.carAcceptancePhoneNUmberEditText);
        EditText street = findViewById(R.id.carAcceptanceStreetEditText);
        EditText apartmentNumber = findViewById(R.id.carAcceptanceApartmentNUmberEditText);
        EditText houseNumber = findViewById(R.id.carAcceptanceHouseNumberEditText);
        EditText postCode = findViewById(R.id.carAcceptancePostCodeEditText);
        EditText city = findViewById(R.id.carAcceptanceCityEditText);
        EditText faultsReportedByCustomer = findViewById(R.id.carAcceptanceFaultsReportedByCustomerEditEdit);
        EditText estimatedRepairPrice = findViewById(R.id.carAcceptanceEstimatedRepairPriceEditView);
        Button send = findViewById(R.id.carAcceptanceSendButton);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarAcceptance carAcceptance = new CarAcceptance();

                carAcceptance.setMark(mark.getText().toString());
                carAcceptance.setModel(model.getText().toString());
                carAcceptance.setYear(year.getText().toString());
                carAcceptance.setPlateNumber(plateNumber.getText().toString());
                carAcceptance.setChassisNumber(chassisNumber.getText().toString());
                carAcceptance.setMeterReading(meterReading.getText().toString());
                carAcceptance.setEmail(email.getText().toString());
                carAcceptance.setFirstName(name.getText().toString());
                carAcceptance.setSurname(surname.getText().toString());
                carAcceptance.setPhoneNumber(phoneNumber.getText().toString());
                carAcceptance.setStreet(street.getText().toString());
                carAcceptance.setApartmentNumber(apartmentNumber.getText().toString());
                carAcceptance.setHouseNumber(houseNumber.getText().toString());
                carAcceptance.setPostCode(postCode.getText().toString());
                carAcceptance.setCity(city.getText().toString());
                carAcceptance.setFaultsReportedByCustomer(faultsReportedByCustomer.getText().toString());
                carAcceptance.setEstimatedRepairPrice(estimatedRepairPrice.getText().toString());
                carAcceptance.setStatus(LocalDate.now().toString());
                carAcceptance.setStatus("ACCEPTED");

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://10.0.2.2:443")
                        .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                APIInterface apiInterface = retrofit.create(APIInterface.class);
                SharedPreferences myPreference = getSharedPreferences("lsauto", MODE_PRIVATE);
                String token = myPreference.getString("token", "");

                Call<ResponseBody> addnewCarAcceptance = apiInterface.addNewCarAcceptance("Bearer " + token, carAcceptance);

                addnewCarAcceptance.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Przyjeto nowe auto", Toast.LENGTH_SHORT).show();
                            Intent main = new Intent(getApplicationContext(), Main2Activity.class);
                            startActivity(main);
                        }else {
                            Toast.makeText(getApplicationContext(), "Wypełnij prawidłowo pola", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Cannot connect to server", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


    }
}

