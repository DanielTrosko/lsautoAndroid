package it.danieltrosko.lsauto;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

class LoadingDIalog {

    private Activity activity;
    private AlertDialog alertDialog;

    LoadingDIalog(Activity myActivity) {
        activity = myActivity;
    }

    void start() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
    }

    void dismiss() {
        alertDialog.dismiss();
    }
}
