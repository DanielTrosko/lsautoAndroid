package it.danieltrosko.lsauto;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowCarDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car_details);
        Bundle bundle = getIntent().getExtras();
        String id = "";
        if (bundle != null){
            id = bundle.getString("id");
        }
        TextView header = findViewById(R.id.carDetailHeader);
        header.setText("Samochód " + id);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Car " + id);

        TextView mark = findViewById(R.id.carShowDetailsMarkTextViewData);
        TextView model = findViewById(R.id.carShowDetailsModelTextViewData);
        TextView engineDesignation = findViewById(R.id.carShowDetailsEngineDesignationTextViewData);
        TextView engineCode = findViewById(R.id.carShowDetailsEngineCodeTextViewData);
        TextView year = findViewById(R.id.carShowDetailsYearTextViewData);
        TextView plateNumber = findViewById(R.id.carShowDetailsPlateNumberTextViewData);
        TextView chassisNumber = findViewById(R.id.carShowDetailsChassisNumberTextViewData);
        TextView meterReading = findViewById(R.id.carShowDetailsMeterReadingTextViewData);
        mark.setText(bundle.getString("mark"));
        model.setText(bundle.getString("model"));
        engineDesignation.setText(bundle.getString("engineDesignation"));
        engineCode.setText(bundle.getString("engineCode"));
        year.setText(bundle.getString("year"));
        plateNumber.setText(bundle.getString("plateNumber"));
        chassisNumber.setText(bundle.getString("chassisNumber"));
        meterReading.setText(bundle.getString("meterReading"));



    }
}
