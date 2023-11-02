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

    public String getFechaHora() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
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

    public String strFechaHora(String fecha) {
        String rsltfecha="";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = dateFormat.parse(fecha);
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            rsltfecha=dateFormat.format(date);

        }catch (Exception ex){
            Log.e("FECHA HELPER", "strFecha: ", ex );
        }
        return rsltfecha;
    }

    public String strFechaSinHora(String fecha) {
        String rsltfecha="";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse(fecha);
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            rsltfecha=dateFormat.format(date);

        }catch (Exception ex){
            Log.e("FECHA HELPER", "strFechaSinHora: ", ex );
        }
        return rsltfecha;
    }

    public long dayofweek(long f) {
        long y,m,d,dw;

        final Calendar c = Calendar.getInstance();

        c.set(getyear(f), getmonth(f)-1, getday(f));

        dw=c.get(Calendar.DAY_OF_WEEK);

        if (dw==1) dw=7;else dw=dw-1;

        return dw;
    }

    public int getyear(long f) {
        int vy;
        long tmp;

        tmp=(long) f/1000000;
        f=tmp / 10000;
        vy=(int)f+2000;

        return vy;
    }

    public int getmonth(long f) {
        long vy;
        int vm;

        vy=(long) f/1000000;
        f=vy % 1000000;
        f=(int) f%10000;
        vm=(int)f /100;

        return vm;
    }

    public int getday(long f) {
        long vy;
        int vm,vd;

        vy=(long) f/1000000;
        f=vy % 1000000;
        vm=(int) f%10000;
        vd=(int)f %100;

        return vd;
    }

    public long getActDate(){
        long f;
        int cyear,cmonth,cday;

        final Calendar c = Calendar.getInstance();
        cyear = c.get(Calendar.YEAR);
        cmonth = c.get(Calendar.MONTH)+1;
        cday = c.get(Calendar.DAY_OF_MONTH);

        f=cfecha(cyear,cmonth,cday);

        return f;
    }

    public long cfecha(int year,int month, int day) {
        long c;
        c=year % 100;
        c=c*10000+month*100+day;

        return c*1000000;
    }
}
