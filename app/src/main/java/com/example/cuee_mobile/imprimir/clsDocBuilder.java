package com.example.cuee_mobile.imprimir;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.cuee_mobile.base.VarGlobal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class clsDocBuilder {
    public String fname,cursym;
    public int prw;
    public ArrayList<String> items=new ArrayList<String>();
    private Context cont;
    private BufferedWriter writer = null;
    private FileWriter wfile;
    private DecimalFormat decfrm;
    private int seplen,prwq,prwt,prwh,decimp;
    private double aux;
    private String frmstr,ts;
    protected VarGlobal gl;

    public clsDocBuilder(Context context, int printwidth) {

        cont=context;
        prw=printwidth;
        seplen=prw;

        gl = ((VarGlobal) (((Activity) cont).getApplication()));
        aux=prw;
        prwq=(int) Math.floor(aux/4);
        prwt=(int) Math.floor(aux/3);
        prwh=(int) Math.floor(aux/2);

        System.setProperty("line.separator","\r\n");
        fname = gl.path+"/print.txt";
        decfrm = new DecimalFormat("#,##0.00");
    }

    public void clear(){
        items.clear();
    }

    public void empty() {
        items.add(" ");
    }

    public void add(String tn){
        items.add(tn);
    }

    public void addc(String tn){
        items.add(ctrim(tn));
    }

    public void add2string(String s1,String s2) {
        ts=ltrim(s1,prwh+1)+rtrim(s2,prwh-1);
        items.add(ts);
    }

    public void line() {
        char[] fill = new char[seplen];
        Arrays.fill(fill, '-');
        String s = new String(fill);

        items.add(s);
    }

    public boolean save(){
        return saverep();
    }

    public boolean saverep(){
        String s;
        int lns=0;

        if (items.size()==0) return true;

        try {

            wfile=new FileWriter(fname);
            writer = new BufferedWriter(wfile);

            for (int i = 0; i < items.size(); i++) {
                try {
                    s = items.get(i);
                } catch (Exception e) {
                    s = "";
                }

                if (s.length() > prw) {
                    int largo = s.length();
                    String sOriginal = s;
                    while (largo > prw) {
                        writer.write(s.substring(0, prw));
                        writer.write("\r\n");
                        s = sOriginal.substring(prw, sOriginal.length());
                        sOriginal = s;
                        largo = s.length();
                    }
                }

                writer.write(s);
                writer.write("\r\n");
                lns++;
            }

            writer.close();
            items.clear();

        } catch(Exception e){
            Toast.makeText(cont,e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public String ctrim(String ss) {
        int l=ss.length();
        if (l>prw) {
            ss=ss.substring(0,prw);
        } else {
            int ldisp=(prw-l)/2;
            frmstr=fillempty(ldisp);
            ss=frmstr+ss;
        }

        return ss;
    }

    private String fillempty(int cn) {
        char[] fill = new char[cn];
        Arrays.fill(fill, ' ');
        String s = new String(fill);

        return s;
    }

    private String trim(String ss) {
        int l=ss.length();
        if (l>prw) ss=ss.substring(0,prw);
        return ss;
    }

    public String ltrim(String ss,int sw) {
        int l=ss.length();
        if (l>sw) {
            ss=ss.substring(0,sw);
        } else {
            frmstr="%-"+sw+"s";
            ss=String.format(frmstr,ss);
        }

        return ss;
    }

    public String rtrim(String ss,int sw) {
        int l;

        ss=ss.trim();
        l=ss.length();

        if (l>=sw) {
            ss=ss.substring(0,sw);
        } else {
            frmstr="%"+sw+"s";
            ss=String.format(frmstr,ss);
        }

        return ss;
    }
}
