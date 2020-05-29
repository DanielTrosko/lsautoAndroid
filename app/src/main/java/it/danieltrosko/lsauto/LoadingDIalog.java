package it.danieltrosko.lsauto;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

class LoadingDIalog {

    private Activity activity;
    private Fragment fragment;
    private AlertDialog alertDialog;

    LoadingDIalog(Activity myActivity) {
        activity = myActivity;
    }
    LoadingDIalog(Fragment myFragment) {
        fragment = myFragment;
    }

    void start() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.transparent_loading_dialog, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    void startFragment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());

        LayoutInflater inflater = fragment.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.transparent_loading_dialog, null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    void dismiss() {
        alertDialog.dismiss();
    }
}
