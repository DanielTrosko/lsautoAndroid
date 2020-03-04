package it.danieltrosko.lsauto;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import it.danieltrosko.lsauto.model.ActualRepair;
import it.danieltrosko.lsauto.pojo.ShowActualRepairPojo;
import it.danieltrosko.lsauto.retrofit.APIInterface;
import it.danieltrosko.lsauto.retrofit.UnsafeOkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class ShowActualRepair extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String[] headers = {"Data oddania", "Cena", "Marka", "Model"};
    private String[][] space;
    private ArrayList<ActualRepair> repairList = new ArrayList<>();

    public static ShowActualRepair newInstance(int index) {
        ShowActualRepair fragment = new ShowActualRepair();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_actual_repair_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final TableView<String[]> table = view.findViewById(R.id.tableViewShowActualRepair);
        table.setColumnCount(4);
        table.setHeaderBackgroundColor(Color.parseColor("#3ECE86"));

//TODO
// Add new activity with repair details.

        table.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(getContext(), "CLick click ", Toast.LENGTH_SHORT).show();
            }
        });
        //


        ArrayList<ShowActualRepairPojo> data = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:443")
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIInterface apiInterface = retrofit.create(APIInterface.class);
        SharedPreferences myPreference = getActivity().getSharedPreferences("lsauto", MODE_PRIVATE);
        String token = myPreference.getString("token", "");
        Call<ArrayList<ShowActualRepairPojo>> call = apiInterface.getActualRepairCars("Bearer " + token);
        call.enqueue(new Callback<ArrayList<ShowActualRepairPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<ShowActualRepairPojo>> call, Response<ArrayList<ShowActualRepairPojo>> response) {

                if (response.isSuccessful()) {
                    data.addAll(response.body());

                    for (int i = 0; i < data.size(); i++) {
                        ActualRepair repair = new ActualRepair();
                        repair.setDateOfAdmission(data.get(i).getDateOfAdmission());
                        repair.setEstimatedRepairPrice(data.get(i).getEstimatedRepairPrice());
                        repair.setMark(data.get(i).getMark());
                        repair.setModel(data.get(i).getModel());
                        repairList.add(repair);


                    }
                }

                space = new String[repairList.size()][4];

                for (int i = 0; i < repairList.size(); i++) {
                    ActualRepair repair = repairList.get(i);

                    space[i][0] = repair.getDateOfAdmission();
                    space[i][1] = repair.getEstimatedRepairPrice();
                    space[i][2] = repair.getMark();
                    space[i][3] = repair.getModel();

                }
                table.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), headers));
                table.setDataAdapter(new SimpleTableDataAdapter(getContext(), space));
            }

            @Override
            public void onFailure(Call<ArrayList<ShowActualRepairPojo>> call, Throwable t) {

            }
        });
    }
}
