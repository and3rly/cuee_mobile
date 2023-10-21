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
import com.example.cuee_mobile.clases.clsBeMenu;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    private ArrayList<clsBeMenu> items;
    private LayoutInflater inflater;
    private  int selectedIndex;

    public MenuAdapter(Context ct, ArrayList<clsBeMenu> menu) {
        items = menu;
        inflater = LayoutInflater.from(ct);
        selectedIndex = -1;
    }

    public void setSelectedIndex(int ind) {
        selectedIndex = ind;
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
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        String sc;
        int iconid,cant;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_menu, null);
            holder = new ViewHolder();

            holder.imgIcono = convertView.findViewById(R.id.imgIcono);
            holder.lblTitulo = convertView.findViewById(R.id.lblTitulo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lblTitulo.setText(items.get(position).titulo);
        holder.imgIcono.setImageResource(R.drawable.lectura);

        if (items.get(position).icono==1) holder.imgIcono.setImageResource(R.drawable.factura);
        if (items.get(position).icono==2) holder.imgIcono.setImageResource(R.drawable.consulta);
        if (items.get(position).icono==3) holder.imgIcono.setImageResource(R.drawable.comunicacion);
        if (items.get(position).icono==4) holder.imgIcono.setImageResource(R.drawable.config);
        if (items.get(position).icono==5) holder.imgIcono.setImageResource(R.drawable.cierre);
        if (items.get(position).icono==6) holder.imgIcono.setImageResource(R.drawable.logout);

        if(selectedIndex!= -1 && position == selectedIndex) {
            convertView.setBackgroundColor(Color.rgb(0, 128, 0));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imgIcono;
        TextView lblTitulo;
    }
}
