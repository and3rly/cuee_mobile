package com.example.cuee_mobile.base;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FechaHelper {

    public FechaHelper(Context ct) {
    }

    public String getFechaCompleta() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String strDate = sdf.format(c.getTime());

        return strDate;
    }
}
