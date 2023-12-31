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
    private ArrayList<auxLecturaServicio> items = new ArrayList<>();
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
        try {
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

            holder.lblInstalacion.setText(items.get(position).IdUsuarioServicio + "");
            holder.lblContador.setText(items.get(position).IdContador + "");
            holder.lblUsuario.setText(items.get(position).Usuario);
            holder.lblItinerario.setText(items.get(position).IdItinerario + "");

            String realizada, correcta;
            realizada = items.get(position).Lectura_realizada+"";
            correcta = items.get(position).Lectura_correcta ? "Si":"No";
            holder.lblLecRealizada.setText(realizada);
            holder.lblLecCorrecta.setText(correcta);

            int lrealizada = items.get(position).Lectura_realizada;
            boolean lcorrecta = items.get(position).Lectura_correcta;

            if (lrealizada > 0 && lcorrecta) {
                //Verde
                convertView.setBackgroundColor(Color.parseColor("#00E676"));
            } else if (lrealizada > 0 && !lcorrecta) {
                //Rojo
                convertView.setBackgroundColor(Color.parseColor("#EF5350"));
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }
        } catch (Exception e) {

        }

        /*if(selectedIndex!= -1 && position == selectedIndex) {
            convertView.setBackgroundColor(Color.parseColor("#FCE4EC"));
        }*/

        return convertView;
    }

    static class ViewHolder {
        TextView lblInstalacion, lblContador, lblUsuario, lblItinerario, lblLecRealizada, lblLecCorrecta;
    }
}
