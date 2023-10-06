package com.example.cuee_mobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.auxLecturaServicio;

import java.util.ArrayList;

public class LecturaAdapter extends BaseAdapter {
    private static ArrayList<auxLecturaServicio> items;
    private int selectedIndex;
    private LayoutInflater l_Inflater;

    public LecturaAdapter(Context ct, ArrayList<auxLecturaServicio> clista) {
        items = clista;
        l_Inflater = LayoutInflater.from(ct);
        selectedIndex = -1;
    }

    public void setSelectedIndex(int ind) {
        selectedIndex = ind;
        notifyDataSetChanged();
    }

    public void refreshItems() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.lista_servicios_lectura, null);
            holder = new ViewHolder();

            holder.lblInstalacion = convertView.findViewById(R.id.lblInstalacion);
            holder.lblContador = convertView.findViewById(R.id.lblContador);
            holder.lblUsuario = convertView.findViewById(R.id.lblUsuario);
            holder.lblItinerario = convertView.findViewById(R.id.lblItinerario);
            holder.lblLecRealizada = convertView.findViewById(R.id.lblLecRealizada);
            holder.lblLecCorrecta = convertView.findViewById(R.id.lblLecCorrecta);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lblInstalacion.setText(items.get(position).IdInstalacion+"");
        holder.lblContador.setText(items.get(position).IdContador+"");
        holder.lblUsuario.setText(items.get(position).Usuario);
        holder.lblItinerario.setText(items.get(position).IdItinerario+"");

        String realizada, correcta;
        int lrealizada = items.get(position).Lectura_realizada;
        int lcorrecta = items.get(position).Lectura_correcta;

        realizada = items.get(position).Lectura_realizada == 1 ? "Si" : "No";
        correcta = items.get(position).Lectura_correcta == 1 ? "Si" : "No";

        holder.lblLecRealizada.setText(realizada);
        holder.lblLecCorrecta.setText(correcta);

        if (lrealizada == 1 && lcorrecta == 1) {
            //Verde
            convertView.setBackgroundColor(Color.parseColor("#00E676"));
        } else if (lrealizada == 1 && lcorrecta == 0) {
            //Rojo
            convertView.setBackgroundColor(Color.parseColor("#EF5350"));
        }

        if(selectedIndex!= -1 && position == selectedIndex && lrealizada == 0 && lcorrecta == 0) {
            convertView.setBackgroundColor(Color.parseColor("#FCE4EC"));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView lblInstalacion, lblContador, lblUsuario, lblItinerario, lblLecRealizada, lblLecCorrecta;
    }
}
