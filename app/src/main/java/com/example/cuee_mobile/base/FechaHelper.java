package com.example.cuee_mobile.base;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public String strFechaIng(String fecha) {
        String rsltfecha="";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = dateFormat.parse(fecha);
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int diffMeses(int anio, int mes) {
        int diferenciaMeses = 0;
        LocalDate fecha, factual;
        try {
            fecha = LocalDate.of(anio, mes, 1);
            factual = LocalDate.now();

            Period periodo = Period.between(fecha, factual);
            diferenciaMeses = periodo.getYears() * 12 + periodo.getMonths();

        } catch (Exception e) {
            Log.e("FECHA HELPER", "diffMeses: ", e);
        }

        return diferenciaMeses;
    }

    public LocalDate setFecha(int anio, int mes, int dia) {
        LocalDate fecha = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                fecha = LocalDate.of(anio, mes, dia);
            }
        } catch (Exception e) {

        }
        return fecha;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int diffMeses(LocalDate fecha1, LocalDate fecha2) {
        int diferenciaMeses = 0;
        try {
            Period periodo = Period.between(fecha1, fecha2);
            diferenciaMeses = periodo.getYears() * 12 + periodo.getMonths();

        } catch (Exception e) {
            Log.e("FECHA HELPER", "diffMeses: ", e);
        }

        return diferenciaMeses;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long diffDias(LocalDate fecha1, LocalDate fecha2) {
        long difDias = 0;
        try {
            difDias = ChronoUnit.DAYS.between(fecha1, fecha2);

        } catch (Exception e) {
            Log.e("FECHA HELPER", "diffMeses: ", e);
        }

        return difDias;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getFechaStr(String pfecha) {
        LocalDate fecha = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            fecha =LocalDate.parse(pfecha, formatter);
        } catch (Exception e) {
            Log.e("FECHA HELPER", "getFechaStr: ", e);
        }

        return fecha;
    }

    public String getNombreMes(int mes) {
        String nombre_mes = "";
        try {
            Map<Integer, String> meses = new HashMap<>();

            meses.put(1, "Enero");
            meses.put(2, "Febrero");
            meses.put(3, "Marzo");
            meses.put(4, "Abril");
            meses.put(5, "Mayo");
            meses.put(6, "Junio");
            meses.put(7, "Julio");
            meses.put(8, "Agosto");
            meses.put(9, "Septiembre");
            meses.put(10, "Octubre");
            meses.put(11, "Noviembre");
            meses.put(12, "Diciembre");

            nombre_mes = meses.get(mes).toUpperCase();

        } catch (Exception e) {
            Log.e("FECHA HELPER", "getNombreMes: ", e);
        }

        return nombre_mes;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String setFechaImp() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraString = fechaHoraActual.format(formatter);

        return fechaHoraString;
    }

    public String setFechaToString(LocalDate fehca) {
        String fechaString = "";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fechaString= fehca.format(formatter);

        } catch (Exception e) {
            Log.e("FECHA HELPER", "getNombreMes: ", e);
        }

        return fechaString;
    }
}
