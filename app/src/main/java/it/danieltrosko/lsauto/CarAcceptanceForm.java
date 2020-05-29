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
import android.util.Patterns;
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
import java.util.regex.Pattern;

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
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

            Map<String, String> param = new HashMap<>();
            param.put("mark", String.valueOf(mark.getText()));
            param.put("model", String.valueOf(model.getText()));
            param.put("year", String.valueOf(year.getText()));
            param.put("plateNumber", String.valueOf(plateNumber.getText()));
            param.put("chassisNumber", String.valueOf(chassisNumber.getText()));
            param.put("meterReading", String.valueOf(meterReading.getText()));
            param.put("email", String.valueOf(email.getText()));
            param.put("firstName", String.valueOf(name.getText()));
            param.put("surname", String.valueOf(surname.getText()));
            param.put("phoneNumber", String.valueOf(phoneNumber.getText()));
            param.put("street", String.valueOf(street.getText()));
            param.put("apartmentNumber", String.valueOf(apartmentNumber.getText()));
            param.put("houseNumber", String.valueOf(houseNumber.getText()));
            param.put("postCode", String.valueOf(postCode.getText()));
            param.put("city", String.valueOf(city.getText()));
            param.put("faultsReportedByCustomer", String.valueOf(faultsReportedByCustomer.getText()));
            param.put("estimatedRepairPrice", String.valueOf(estimatedRepairPrice.getText()));


            MultipartBody.Part photoOne = null;
            MultipartBody.Part photoTwo = null;
            MultipartBody.Part photoThree = null;
            MultipartBody.Part photoFour = null;


            try {
                photoOne = photoOne = MultipartBody.Part.createFormData
                        ("photoOne", "photoOne", RequestBody.create(bytesPhoto.get(0)));
                photoTwo = photoTwo = MultipartBody.Part.createFormData
                        ("photoTwo", "photoTwo", RequestBody.create(bytesPhoto.get(1)));
                photoThree = photoThree = MultipartBody.Part.createFormData
                        ("photoThree", "photoThree", RequestBody.create(bytesPhoto.get(2)));
                photoFour = photoFour = MultipartBody.Part.createFormData
                        ("photoFour", "photoFour", RequestBody.create(bytesPhoto.get(3)));
            } catch (IndexOutOfBoundsException e) {
                loadingDialog.dismiss();
                e.printStackTrace();
            }


            APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
            SharedPreferences myPreference = getSharedPreferences("lsauto", MODE_PRIVATE);
            String token = myPreference.getString("token", "");

            String emailInput = String.valueOf(email.getText());
            System.err.println(emailInput);

            if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                if (!bytesPhoto.isEmpty()) {
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
                } else {
                    Toast.makeText(getApplicationContext(), "Zrób 4 zdjęcia", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(getApplicationContext(), "Wprowadz poprawny email", Toast.LENGTH_SHORT).show();
            }


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

