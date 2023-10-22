package com.example.cuee_mobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.clsBeContadores;

import java.util.ArrayList;

public class ContadoresAdapter extends BaseAdapter {
    private static ArrayList<clsBeContadores> items;
    private int selectedIndex;
    private LayoutInflater l_Inflater;

    public ContadoresAdapter(Context ct, ArrayList<clsBeContadores> clista) {
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
            convertView = l_Inflater.inflate(R.layout.lista_contadores, null);
            holder = new ViewHolder();

            holder.lblContador = convertView.findViewById(R.id.lblContador);
            holder.lblMarca = convertView.findViewById(R.id.lblMarca);
            holder.lblColor = convertView.findViewById(R.id.lblColor);
            holder.lblMarchamo = convertView.findViewById(R.id.lblMarchamo);
            holder.lblFechaCambio = convertView.findViewById(R.id.lblFechaCambio);
            holder.lblFechaCreacion = convertView.findViewById(R.id.lblFechaCreacion);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lblContador.setText(items.get(position).IdContador);
        holder.lblMarca.setText(items.get(position).Nmarca);
        holder.lblColor.setText(items.get(position).Ncolor);
        holder.lblMarchamo.setText(items.get(position).No_marchamo);
        holder.lblFechaCambio.setText(items.get(position).Fecha_Cambio);
        holder.lblFechaCreacion.setText(items.get(position).Fecha_Creacion);

        if(selectedIndex!= -1 && position == selectedIndex) {
            convertView.setBackgroundColor(Color.rgb(0, 128, 0));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView lblContador, lblMarca, lblColor, lblMarchamo, lblFechaCambio, lblFechaCreacion;
    }
}
