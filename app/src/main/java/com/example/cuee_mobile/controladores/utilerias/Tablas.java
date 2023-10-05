package com.example.cuee_mobile.controladores.utilerias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cuee_mobile.R;
import com.example.cuee_mobile.adapter.TablasDetAdapter;
import com.example.cuee_mobile.adapter.TablasEncAdapter;
import com.example.cuee_mobile.controladores.PBase;

import java.util.ArrayList;

public class Tablas extends PBase {

    private Spinner cmbTabla;
    private ImageView imgLimpiar;
    private EditText txtWhere;
    private GridView grid, dgrid;
    private ProgressBar pgr;
    private LinearLayout lyEnc;
    private ArrayList<String> tlista = new ArrayList<>();
    private ArrayList<String> values=new ArrayList<>();
    private ArrayList<String> dvalues=new ArrayList<>();
    private TablasEncAdapter adapter;
    private TablasDetAdapter dadapter;
    private String tabla;
    private int cw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablas);
        super.SetBase();

        cmbTabla = findViewById(R.id.cmbTablas);
        imgLimpiar = findViewById(R.id.imgLimpiar);
        txtWhere = findViewById(R.id.txtWhere);
        grid = findViewById(R.id.grdEnc);
        dgrid = findViewById(R.id.grdDet);
        pgr = findViewById(R.id.pgrTablas);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        cw = (int) ((displayMetrics.widthPixels-22)/5)-1;

        setHandlers();
        setTablas();
    }

    private void setHandlers() {
        cmbTabla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView spinlabel;

                try {
                    spinlabel = (TextView) parent.getChildAt(0);
                    spinlabel.setTextColor(Color.BLACK);
                    spinlabel.setPadding(5, 0, 0, 0);
                    spinlabel.setTextSize(18);
                    spinlabel.setTypeface(spinlabel.getTypeface(), Typeface.BOLD);

                    tabla = tlista.get(position);
                    if (!tabla.equalsIgnoreCase(" ")) {
                        procesarTabla();
                    }
                } catch (Exception e) {
                    helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName()+" - "+ e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        grid.setOnItemClickListener((parent, view, position, id) -> {

            try {
                Object lvObj = grid.getItemAtPosition(position);
                String item = (String) lvObj;

                adapter.setSelectedIndex(position);
                helper.toast(item);
            } catch (Exception e) {
                helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName()+" - "+e.getMessage());
            }
        });

        dgrid.setOnItemClickListener((parent, view, position, id) -> {

            try {
                Object lvObj = dgrid.getItemAtPosition(position);
                String item = (String) lvObj;

                dadapter.setSelectedIndex(position);
                helper.toast(item);
            } catch (Exception e) {
                helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName()+" - "+e.getMessage());
            }
        });

        dgrid.setOnItemLongClickListener((parent, view, position, id) -> {

            try {
                Object lvObj = dgrid.getItemAtPosition(position);
                String item = (String) lvObj;

                adapter.setSelectedIndex(position);
                helper.msgbox(item);
            } catch (Exception e) {
                helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName()+" - "+e.getMessage());
            }
            return true;
        });

        txtWhere.setOnKeyListener((arg0, arg1, arg2) -> {
            if (arg2.getAction() == KeyEvent.ACTION_DOWN) {
                switch (arg1) {
                    case KeyEvent.KEYCODE_ENTER:
                        procesarTabla();
                        return true;
                }
            }
            return false;
        });

        imgLimpiar.setOnClickListener(view -> txtWhere.setText(""));
    }

    private void setTablas() {
        Cursor DT;
        tlista.clear(); tlista.add(" ");
        try {
            sql = "SELECT name FROM sqlite_master WHERE type='table' AND name<>'android_metadata' order by name";
            DT = Con.OpenDT(sql);

            if (DT.getCount() > 0) {
                DT.moveToFirst();
                while (!DT.isAfterLast()) {
                    tlista.add(DT.getString(0));
                    DT.moveToNext();
                }
            }

            if (DT != null) DT.close();

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tlista);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            cmbTabla.setAdapter(dataAdapter);
        } catch (Exception e) {
            helper.msgbox(new Object() {} .getClass().getEnclosingClass().getName() +" - "+ e);
        }
    }

    private void procesarTabla() {
        try{
            pgr.setVisibility(View.VISIBLE);
            pgr.setVisibility(View.VISIBLE);

            values.clear();
            dvalues.clear();

            adapter = new TablasEncAdapter(Tablas.this, values);
            grid.setAdapter(adapter);

            dadapter = new TablasDetAdapter(Tablas.this, dvalues);
            dgrid.setAdapter(dadapter);

            Handler mtimer = new Handler();
            Runnable mrunner = () -> showData(tabla);
            mtimer.postDelayed(mrunner, 1000);

        }catch (Exception e){
            helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName()+" - "+e.getMessage());
        }

    }

    @SuppressLint("Range")
    private void showData(String tn) {
        Cursor PRG,dt;
        String n,flt,ss = "";
        int cc=1,j;

        //lblCant.setText("");

        try {
            ss="SELECT ";

            sql = "PRAGMA table_info('"+tn+"')";
            PRG=db.rawQuery(sql, null);
            cc=PRG.getCount();

            PRG.moveToFirst();j=0;

            while (!PRG.isAfterLast()) {
                n=PRG.getString(PRG.getColumnIndex("name"));
                values.add(n);
                ss=ss+n;if (j<cc-1) ss=ss+",";
                PRG.moveToNext();j++;
            }

            ss=ss+" FROM "+tn;

            flt=txtWhere.getText().toString();
            if (!flt.isEmpty()) ss=ss+" WHERE "+flt+" COLLATE NOCASE";

        } catch (Exception e) {
            helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName()+" - "+e.getMessage() + sql);
        }

        ViewGroup.LayoutParams layoutParams = grid.getLayoutParams();
        layoutParams.width =((int) (cw*cc))+25;
        grid.setLayoutParams(layoutParams);

        grid.setColumnWidth(cw);
        grid.setStretchMode(GridView.NO_STRETCH);
        grid.setNumColumns(cc);

        adapter=new TablasEncAdapter(this,values);
        grid.setAdapter(adapter);


        ViewGroup.LayoutParams dlayoutParams = dgrid.getLayoutParams();
        dlayoutParams.width =((int) (cw*cc))+25;
        dgrid.setLayoutParams(dlayoutParams);

        dgrid.setColumnWidth(cw);
        dgrid.setStretchMode(GridView.NO_STRETCH);
        dgrid.setNumColumns(cc);

        try {
            dt=Con.OpenDT(ss);
            if (dt.getCount()==0) {
                pgr.setVisibility(View.INVISIBLE);return;
            }

            dt.moveToFirst();
            while (!dt.isAfterLast()) {

                for (int i = 0; i < cc; i++) {
                    try {
                        ss=dt.getString(i);
                    } catch (Exception e) {
                        ss="?";
                    }
                    dvalues.add(ss);
                }
                dt.moveToNext();
            }
        } catch (Exception e) {
            helper.msgbox(new Object(){}.getClass().getEnclosingMethod().getName()+" - "+e.getMessage() + sql);
        }

        dadapter=new TablasDetAdapter(this,dvalues);
        dgrid.setAdapter(dadapter);

        pgr.setVisibility(View.INVISIBLE);

    }

}