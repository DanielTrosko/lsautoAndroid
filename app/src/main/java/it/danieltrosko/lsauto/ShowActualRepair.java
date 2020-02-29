package it.danieltrosko.lsauto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ShowActualRepair extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ShowActualRepair newInstance(int index) {
        ShowActualRepair fragment = new ShowActualRepair();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_actual_repair_layout, container, false);
    }
}
