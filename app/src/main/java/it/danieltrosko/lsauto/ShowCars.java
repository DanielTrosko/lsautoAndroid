package it.danieltrosko.lsauto;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import java.util.ArrayList;


import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import it.danieltrosko.lsauto.model.Car;
import it.danieltrosko.lsauto.pojo.CarPojo;
import it.danieltrosko.lsauto.retrofit.APIInterface;
import it.danieltrosko.lsauto.retrofit.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ShowCars extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String[] headers = {"Marka", "Model", "Silnik", "Rocznik", "Nr rejestracyjny"};
    private String[][] space;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<CarPojo> data;


    public static ShowCars newInstance(int index) {
        ShowCars fragment = new ShowCars();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final TableView<String[]> table = view.findViewById(R.id.tableView);
        table.setColumnCount(5);
        table.setHeaderBackgroundColor(Color.parseColor("#DCDCDC"));


        data = new ArrayList<>();


        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        SharedPreferences myPreference = getActivity().getSharedPreferences("lsauto", MODE_PRIVATE);
        String token = myPreference.getString("token", "");
        Call<ArrayList<CarPojo>> call = apiInterface.getAllCars("Bearer " + token);
        call.enqueue(new Callback<ArrayList<CarPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<CarPojo>> call, Response<ArrayList<CarPojo>> response) {

                if (response.isSuccessful()) {
                    data.addAll(response.body());

                    for (int i = 0; i < data.size(); i++) {
                        Car car = new Car();
                        car.setId(data.get(i).getId());
                        car.setMark(data.get(i).getMark());
                        car.setModel(data.get(i).getModel());
                        car.setYear(data.get(i).getYear());
                        car.setEngineDesignation(data.get(i).getEngineDesignation());
                        car.setEngineCode(data.get(i).getEngineCode());
                        car.setChassisNumber(data.get(i).getChassisNumber());
                        car.setMeterReading(data.get(i).getMeterReading());
                        car.setPlateNumber(data.get(i).getPlateNumber());
                        cars.add(car);

                    }
                }

                space = new String[cars.size()][5];

                for (int i = 0; i < cars.size(); i++) {
                    Car car = cars.get(i);

                    space[i][0] = car.getMark();
                    space[i][1] = car.getModel();
                    space[i][2] = car.getEngineDesignation();
                    space[i][3] = car.getYear();
                    space[i][4] = car.getPlateNumber();

                }
                table.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), headers));
                table.setDataAdapter(new SimpleTableDataAdapter(getContext(), space));
            }


            @Override
            public void onFailure(Call<ArrayList<CarPojo>> call, Throwable t) {

            }
        });

        table.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent main = new Intent(getContext(), ShowCarDetails.class);
                main.putExtra("id", data.get(rowIndex).getId());
                main.putExtra("mark", data.get(rowIndex).getMark());
                main.putExtra("model", data.get(rowIndex).getModel());
                main.putExtra("engineDesignation", data.get(rowIndex).getEngineDesignation());
                main.putExtra("engineCode", data.get(rowIndex).getEngineCode());
                main.putExtra("year", data.get(rowIndex).getYear());
                main.putExtra("plateNumber", data.get(rowIndex).getPlateNumber());
                main.putExtra("chassisNumber", data.get(rowIndex).getChassisNumber());
                main.putExtra("meterReading", data.get(rowIndex).getMeterReading());
                startActivity(main);
            }
        });
        //
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_cars_layout, container, false);
    }
}
