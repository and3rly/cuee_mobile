package com.example.cuee_mobile.base;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FechaHelper {

    public FechaHelper(Context ct) {
    }

    public String getFechaCompleta() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String strDate = sdf.format(c.getTime());

        return strDate;
    }

    public String getFecha() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());

        return strDate;
    }

    public String convertirFecha(String fecha) {
        String vFecha = "";
        try {
            SimpleDateFormat dateFormat;

            if (fecha.contains("-")) {
                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            } else {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            }

            Date date = dateFormat.parse(fecha);
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            vFecha = dateFormat.format(date);

        } catch (Exception e) {
            Log.e("FECHA HELPER", "convierteFecha: ", e );
        }
        return vFecha;
    }

    public String strFecha(String fecha) {
        String rsltfecha="";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = dateFormat.parse(fecha);
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            rsltfecha=dateFormat.format(date);

        }catch (Exception ex){
            Log.e("FECHA HELPER", "strFecha: ", ex );
        }
        return rsltfecha;
    }
}
