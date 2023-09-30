package com.example.cuee_mobile.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HelperBD extends SQLiteOpenHelper {
    private final ScriptBD scriptBD;
    public SQLiteDatabase vDatabase;
    public Insert Ins;
    public Update Upd;

    public HelperBD(@Nullable Context context, String nombre) {
        super(context, nombre, null, 1);

        Ins=new Insert();
        Upd=new Update();
        scriptBD = new ScriptBD(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        scriptBD.ejecutarScript(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor OpenDT(String pSQL) {

        Cursor vCursor = null;
        String vError="";

        try {

            if (!vDatabase.isOpen()) vDatabase = getWritableDatabase();

            vCursor = vDatabase.rawQuery(pSQL, null);
            if (vCursor != null){
                vCursor.moveToLast();
            } else {
                throw new Exception("No se obtuvo el registro " + pSQL);
            }

        } catch(Exception ex){

        }

        return vCursor;
    }

    public class Insert {

        private List<String> clFList = new ArrayList<String>();
        private List<String> clVList = new ArrayList<String>();
        private String clTable;

        public Insert() {
            clFList.clear();
            clVList.clear();
            clTable = "";
        }

        public void init(String TableName) {
            clFList.clear();
            clVList.clear();
            clTable = TableName;
        }

        public void add(String pField, String pValue , String pTipo) {

            String SV;

            try  {

                if (pField == "") return;
                if (pTipo == "") return;

                pValue=pValue.replace("'", "");
                SV="'" + pValue + "'";

                if (pTipo == "S") SV="'" +  pValue + "'";
                if (pTipo == "N") SV= pValue;

                clFList.add(pField);
                clVList.add(SV);

            } catch (Exception e) { }

        }

        public void add(String pField, String pValue ) {

            String SV;

            try  {
                //if (pField == "") return;

                pValue=pValue.replace("'", "");

                if (pValue.equals("null")){
                    pValue="";
                }

                SV="'" + pValue + "'";

                clFList.add(pField);
                clVList.add(SV);

            } catch (Exception e) { }

        }

        public void add(String pField, int pValue) {

            String SV;

            try
            {
                if (pField == "") {return;}
                SV= String.valueOf(pValue);

                clFList.add(pField);
                clVList.add(SV);

            } catch (Exception e) { }

        }

        public void add(String pField, long pValue) {

            String SV;

            try{

                if (pField == "") {return;}
                SV= ""+pValue;

                clFList.add(pField);
                clVList.add(SV);

            } catch (Exception e) { }

        }

        public void add(String pField, double pValue) {

            String SV;

            try
            {
                if (pField == "") {return;}
                SV= String.valueOf(pValue);

                clFList.add(pField);
                clVList.add(SV);

            } catch (Exception e) { }

        }

        public void add(String pField, boolean pValue) {

            String SV;

            try  {

                clFList.add(pField);
                if (pValue) clVList.add("1");else clVList.add("0");

            } catch (Exception e) { }

        }

        public String sql() {

            String sVal, S, SF, SV;

            if (clTable == "") {return "";}
            if (clFList.isEmpty()) {return "";}

            try  {

                SV="";
                SF="";
                S="INSERT INTO " + clTable + " (";

                for(int I = 0; I < clFList.size() ; I = I+1) {

                    sVal=clFList.get(I);
                    SF=SF + sVal;
                    if (I < clFList.size()-1) {SF=SF + ",";}

                    sVal=clVList.get(I);
                    SV=SV + sVal;
                    if (I < clFList.size()-1) {SV=SV + ",";}
                }

                S = S + SF + ") VALUES (" + SV + ")";

                return S;

            } catch (Exception e) {
                return "";
            }

        }

    }
    public class Update {

        private List<String> clFList = new ArrayList<String>();
        private String clTable,vWhere;

        public Update() {
            clFList.clear();
            clTable = "";
        }

        public void init(String TableName) {
            clFList.clear();
            clTable = "UPDATE " + TableName + " SET ";
        }


        public void add(String pField, String pValue , String pTipo) {

            String SV;

            try  {

                if (pField == "") return;
                if (pTipo == "") return;

                pValue=pValue.replace("'", "");
                SV="'" + pValue + "'";

                if (pTipo == "S") SV="'" +  pValue + "'";
                if (pTipo == "N") SV= pValue;

                clFList.add(pField + " = "+ SV);

            } catch (Exception e) { }

        }

        public void add(String pField, String pValue) {

            String SV;

            try
            {
                if (pField == "") return;

                pValue=pValue.replace("'", "");
                SV="'" + pValue + "'";
                clFList.add(pField + " = "+ SV);

            } catch (Exception e) { }

        }

        public void add(String pField, boolean pValue ) {

            String SV;

            try  {
                if (pField == "") return;
                if (pValue) SV="1";else SV="0";
                clFList.add(pField + " = "+ SV);
            } catch (Exception e) { }

        }

        public void add(String pField, int pValue ) {

            String SV;

            try  {
                if (pField == "") return;
                SV= String.valueOf(pValue);
                clFList.add(pField + " = "+ SV);
            } catch (Exception e) { }

        }

        public void add(String pField, double pValue ) {

            String SV;

            try  {
                if (pField == "") return;
                SV= String.valueOf(pValue);
                clFList.add(pField + " = "+ SV);
            } catch (Exception e) { }

        }

        public void Where(String pWhere) {
            vWhere = " WHERE " + pWhere;
        }

        public String sql() {

            String sVal,vUpDate;

            if (clTable == "") return "";
            if (clFList.isEmpty()) return "";

            try  {

                vUpDate = clTable;

                for(int I = 0; I < clFList.size() ; I = I+1) {
                    sVal=clFList.get(I);
                    vUpDate=vUpDate + sVal;
                    if (I < clFList.size()-1) {vUpDate=vUpDate + ",";}
                }

                vUpDate=vUpDate + vWhere;

                return vUpDate;

            } catch (Exception e) {
                return "";
            }

        }

    }
}
