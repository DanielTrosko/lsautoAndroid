package it.danieltrosko.lsauto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarAcceptanceForm extends AppCompatActivity {


    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private ArrayList<byte[]> bytesPhoto = new ArrayList<>();
    final LoadingDIalog loadingDialog = new LoadingDIalog(CarAcceptanceForm.this);


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            bitmaps.add(bitmap);
            new Thread(() -> {
                byte[] temp = convertToByte(bitmap);
                bytesPhoto.add(temp);
            }).start();


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

        photoFrontLeft.setOnClickListener(v -> dispatchTakePictureIntent());


        send.setOnClickListener(v -> {
            loadingDialog.start();
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


            MultipartBody.Part photoOne = photoOne = MultipartBody.Part.createFormData
                    ("photoOne", "photoOne", RequestBody.create(bytesPhoto.get(0)));
            MultipartBody.Part photoTwo = photoTwo = MultipartBody.Part.createFormData
                    ("photoTwo", "photoTwo", RequestBody.create(bytesPhoto.get(1)));
            MultipartBody.Part photoThree = photoThree = MultipartBody.Part.createFormData
                    ("photoThree", "photoThree", RequestBody.create(bytesPhoto.get(2)));
            MultipartBody.Part photoFour = photoFour = MultipartBody.Part.createFormData
                    ("photoFour", "photoFour", RequestBody.create(bytesPhoto.get(3)));


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
                    loadingDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Brak połączenia", Toast.LENGTH_SHORT).show();
                    loadingDialog.dismiss();
                }
            });


        });
    }


    private static byte[] convertToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap = Bitmap.createScaledBitmap(bitmap, 1280, 720, true);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("Photo file is null", Objects.requireNonNull(ex.getMessage()));
                Toast.makeText(getApplicationContext(), "Blad z plikiem zdjęcia", Toast.LENGTH_SHORT).show();
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "it.danieltrosko.lsauto.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


}

