package com.example.cuee_mobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.clsBeLectura;

import java.util.ArrayList;

public class CLecturaAdapter extends BaseAdapter {
    private static ArrayList<clsBeLectura> items;
    private int selectedIndex;
    private LayoutInflater l_Inflater;

    public CLecturaAdapter(Context ct, ArrayList<clsBeLectura> clista) {
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
            convertView = l_Inflater.inflate(R.layout.lista_clectura, null);
            holder = new ViewHolder();

            holder.lblIdLectura = convertView.findViewById(R.id.lblIdLectura);
            holder.lblUsuario = convertView.findViewById(R.id.lblUsuario);
            holder.lblContador = convertView.findViewById(R.id.lblContador);
            holder.lblLectura = convertView.findViewById(R.id.lblLectura);
            holder.lblConsumo = convertView.findViewById(R.id.lblConsumo);
            holder.lblLecturaKW = convertView.findViewById(R.id.lblLecturaKW);
            holder.lblFecha = convertView.findViewById(R.id.lblFecha);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lblIdLectura.setText(items.get(position).IdLectura+"");
        holder.lblUsuario.setText(items.get(position).IdUsuarioServicio+"");
        holder.lblContador.setText(items.get(position).IdContador);
        holder.lblLectura.setText(items.get(position).Lectura+"");
        holder.lblConsumo.setText(items.get(position).Consumo+"");
        holder.lblLecturaKW.setText(items.get(position).Lectura_kw+"");
        holder.lblFecha.setText(items.get(position).Fecha);

        if(selectedIndex!= -1 && position == selectedIndex) {
            convertView.setBackgroundColor(Color.rgb(0, 128, 0));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView lblIdLectura, lblContador, lblUsuario, lblLectura, lblConsumo, lblLecturaKW, lblFecha;
    }
}
