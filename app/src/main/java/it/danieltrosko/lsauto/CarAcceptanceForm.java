package it.danieltrosko.lsauto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.danieltrosko.lsauto.model.CarAcceptance;
import it.danieltrosko.lsauto.retrofit.APIInterface;
import it.danieltrosko.lsauto.retrofit.ApiClient;
import it.danieltrosko.lsauto.retrofit.UnsafeOkHttpClient;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarAcceptanceForm extends AppCompatActivity {


    private static final int CAMERA_PIC_REQUEST = 1337;
    static final int REQUEST_TAKE_PHOTO = 1;

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            bitmaps.add(image);
        }



    }

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
        Button photoFrontLeft = findViewById(R.id.carAcceptancePhotoLeftFrontButton);

        photoFrontLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });


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

                Map<String, String> param = new HashMap<>();
                param.put("mark", mark.getText().toString());
                param.put("model", model.getText().toString());
                param.put("year", year.getText().toString());
                param.put("plateNumber", plateNumber.getText().toString());
                param.put("chassisNumber", chassisNumber.getText().toString());
                param.put("meterReading", meterReading.getText().toString());
                param.put("email", email.getText().toString());
                param.put("firstName", name.getText().toString());
                param.put("surname", surname.getText().toString());
                param.put("phoneNumber", phoneNumber.getText().toString());
                param.put("street", street.getText().toString());
                param.put("apartmentNumber", apartmentNumber.getText().toString());
                param.put("houseNumber", houseNumber.getText().toString());
                param.put("postCode", postCode.getText().toString());
                param.put("city", city.getText().toString());
                param.put("faultsReportedByCustomer", faultsReportedByCustomer.getText().toString());
                param.put("estimatedRepairPrice", estimatedRepairPrice.getText().toString());



                MultipartBody.Part photoOne = MultipartBody.Part.createFormData
                        ("photoOne", "photoOne", RequestBody.create(convertToByte(bitmaps.get(0))));
                MultipartBody.Part photoTwo = MultipartBody.Part.createFormData
                        ("photoTwo", "photoTwo", RequestBody.create(convertToByte(bitmaps.get(1))));
                MultipartBody.Part photoThree = MultipartBody.Part.createFormData
                        ("photoThree", "photoThree", RequestBody.create(convertToByte(bitmaps.get(2))));
                MultipartBody.Part photoFour = MultipartBody.Part.createFormData
                        ("photoFour", "photoFour", RequestBody.create(convertToByte(bitmaps.get(3))));


                APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
                SharedPreferences myPreference = getSharedPreferences("lsauto", MODE_PRIVATE);
                String token = myPreference.getString("token", "");

                Call<ResponseBody> addnewCarAcceptance = apiInterface.addNewCarAcceptance("Bearer " + token, param, photoOne, photoTwo, photoThree, photoFour);

                addnewCarAcceptance.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Przyjeto nowe auto", Toast.LENGTH_SHORT).show();
                            Intent main = new Intent(getApplicationContext(), Main2Activity.class);
                            startActivity(main);
                        } else {
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


    private static byte[] convertToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        return stream.toByteArray();
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }



}

