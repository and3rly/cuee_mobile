package com.example.cuee_mobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.clsBePendientes_lectura;

import java.util.ArrayList;

public class LPendientesAdapter extends BaseAdapter {
    private ArrayList<clsBePendientes_lectura> items;
    private int selectedIndex;
    private LayoutInflater l_Inflater;

    public LPendientesAdapter(Context ct, ArrayList<clsBePendientes_lectura> clista) {
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
                convertView = l_Inflater.inflate(R.layout.lista_pendientes, null);
                holder = new ViewHolder();

                holder.txtUsuario = convertView.findViewById(R.id.txtUsuario);
                holder.txtRazonLectura = convertView.findViewById(R.id.txtRazonLectura);
                holder.lyUsuario = convertView.findViewById(R.id.lyUsuario);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.txtUsuario.setText(items.get(position).IdUsuarioServicio + " - " + items.get(position).Nombre );

            String razon =  null;
            razon =  items.get(position).Razon;
            if (razon != null) {
                holder.txtRazonLectura.setText(items.get(position).Razon);
                convertView.setBackgroundColor(Color.parseColor("#FFF3E0"));
            } else {
                holder.txtRazonLectura.setText(" - - - - - ");
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }

        } catch (Exception e) {

        }

        return convertView;
    }

    static class ViewHolder {
        TextView txtUsuario, txtRazonLectura;
        LinearLayout lyUsuario;
    }
}
