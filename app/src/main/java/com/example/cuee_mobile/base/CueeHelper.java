package com.example.cuee_mobile.base;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.cuee_mobile.R;

import java.text.DecimalFormat;

public class CueeHelper {
    private Context context;

    public CueeHelper(Context ct) {
        context = ct;
    }

    public String formatDosDecimales(double valor){
        DecimalFormat format = new DecimalFormat("0.00");
        format.setMaximumFractionDigits(2);
        return format.format(valor);
    }

    public double round2dec(double val){
        double dval;
        long ival;

        val=val+0.000001;
        dval=val*100;
        ival=Math.round(dval);
        dval=(double) ival;
        val=dval*0.01;

        return val;
    }

    public void toast(String mensaje) {
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
    }

    public void msgbox(String msg) {
        if (msg==null || msg.isEmpty()) {return;}

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        dialog.setTitle(R.string.app_name);
        dialog.setIcon(R.drawable.info);
        dialog.setMessage(msg);

        dialog.setNeutralButton("OK", (dialog1, which) -> {

        });
        dialog.show();
    }
}
