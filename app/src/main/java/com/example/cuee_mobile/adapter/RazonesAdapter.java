package com.example.cuee_mobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.clases.clsBeRazon_no_lectura;

import java.util.ArrayList;

public class RazonesAdapter extends BaseAdapter {
    private static ArrayList<clsBeRazon_no_lectura> items;
    private int selectedIndex;
    private LayoutInflater l_Inflater;

    public RazonesAdapter(Context ct, ArrayList<clsBeRazon_no_lectura> clista) {
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
            convertView = l_Inflater.inflate(R.layout.lista_razones, null);
            holder = new ViewHolder();
            holder.lblRazon = convertView.findViewById(R.id.lblRazon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lblRazon.setText(items.get(position).Nombre);

        if(selectedIndex!= -1 && position == selectedIndex) {
            convertView.setBackgroundColor(Color.rgb(0, 128, 0));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView lblRazon;
    }
}
