package com.example.cuee_mobile.base;

import android.app.AlertDialog;
import android.content.Context;
import com.example.cuee_mobile.R;

public class CueeHelper {
    private Context context;

    public CueeHelper(Context ct) {
    }

    public void msgbox(String msg) {

        if (msg==null || msg.isEmpty()) {return;}

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        dialog.setTitle(R.string.app_name);
        dialog.setMessage(msg);

        dialog.setNeutralButton("OK", (dialog1, which) -> {

        });
        dialog.show();

    }
}
