package com.example.cuee_mobile.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ScriptBD {
    private Context ct;
    private String sql;

    public ScriptBD(Context context) {
        this.ct = context;
    }

    public boolean ejecutarScript(SQLiteDatabase db) {
        try {
            return script(db);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean script(SQLiteDatabase db) {
        try {
            sql = "CREATE TABLE [CONTADORES] (" +
                    "IdContador TEXT NOT NULL ," +
                    "IdMarca INTERGER DEFAULT NULL," +
                    "Activo INTENGER DEFAULT 0," +
                    "No_marchamo TEXT DEFAULT NULL," +
                    "Color TEXT DEFAULT NULL," +
                    "IdUsuarioServicio INTEGER DEFAULT NULL," +
                    "Fecha_Cambio TEXT DEFAULT NULL," +
                    "Fecha_Creacion TEXT DEFAULT NULL," +
                    "Lectura REAL DEFAULT NULL," +
                    "PRIMARY KEY([IdContador])"+
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [INSTITUCION] (" +
                    "IdInstitucion INTEGER NOT NULL," +
                    "Nombre TEXT DEFAULT NULL," +
                    "Logo TEXT DEFAULT NULL," +
                    "Preciots REAL DEFAULT NULL," +
                    "Preciotns REAL DEFAULT NULL," +
                    "Luz_publica REAL DEFAULT NULL," +
                    "Cargo_fijo REAL DEFAULT NULL," +
                    "Mora REAL DEFAULT NULL," +
                    "Tarifa_demanda REAL DEFAULT NULL," +
                    "Precio_kw REAL DEFAULT NULL," +
                    "Precio_pc REAL DEFAULT NULL," +
                    "Cargo_fijo_btdp REAL DEFAULT NULL," +
                    "Multas_varias REAL DEFAULT NULL," +
                    "Cobro_instalaciones REAL DEFAULT NULL," +
                    "Cobro_reconexiones REAL DEFAULT NULL," +
                    "Cobro_multa REAL DEFAULT NULL," +
                    "Ultima_solicitud REAL DEFAULT NULL," +
                    "Ultimo_usuario REAL DEFAULT NULL," +
                    "Ultimo_contrato REAL DEFAULT NULL," +
                    "Porcentaje_lectura INTEGER DEFAULT NULL," +
                    "Cargo_fijo_btdfp REAL DEFAULT NULL," +
                    "Tarifa_demandafp REAL DEFAULT NULL," +
                    "Precio_kwfp REAL DEFAULT NULL," +
                    "Precio_pcfp REAL DEFAULT NULL," +
                    "Serie_pf TEXT DEFAULT NULL," +
                    "Correl_pf INTEGER DEFAULT NULL," +
                    "Correl_pf_actual INTEGER DEFAULT NULL," +
                    "Fel_codigo_acceso TEXT DEFAULT NULL," +
                    "Fel_usuario TEXT DEFAULT NULL," +
                    "Fel_clave TEXT DEFAULT NULL," +
                    "NIT_Emisor TEXT DEFAULT NULL," +
                    "Direccion_emisor TEXT DEFAULT NULL," +
                    "Correo_emisor TEXT DEFAULT NULL," +
                    "Nombre_Comercial TEXT DEFAULT NULL," +
                    "Nombre_Emisor TEXT DEFAULT NULL," +
                    "Codigo_postal TEXT DEFAULT NULL," +
                    "Municipio TEXT DEFAULT NULL," +
                    "Departamento TEXT DEFAULT NULL," +
                    "Pais TEXT DEFAULT NULL," +
                    "Afiliacion_IVA TEXT DEFAULT NULL," +
                    "Fel_token TEXT DEFAULT NULL," +
                    "Fel_requestor TEXT DEFAULT NULL," +
                    "Porcentaje_iva REAL DEFAULT NULL," +
                    "Fel_codigo_escenario_isr INTEGER DEFAULT NULL," +
                    "Fel_tipo_frase_isr INTEGER DEFAULT NULL," +
                    "Fel_texto_isr TEXT DEFAULT NULL," +
                    "Fel_codigo_escenario_iva INTEGER DEFAULT NULL," +
                    "Fel_tipo_frase_iva INTEGER DEFAULT NULL," +
                    "Fel_texto_iva TEXT DEFAULT NULL," +
                    "Numero_resolucion TEXT DEFAULT NULL," +
                    "Fecha_resolucion TEXT DEFAULT NULL," +
                    "Frase_exento TEXT DEFAULT NULL," +
                    "Monto_permitido_cf REAL DEFAULT NULL," +
                    "PRIMARY KEY([IdInstitucion])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [INSTITUCION_DETALLE] (" +
                    "IdInstitucion INTEGER NOT NULL," +
                    "IdPeriodoParametros INTEGER NOT NULL," +
                    "FechaInicio TEXT DEFAULT NULL," +
                    "FechaFin TEXT DEFAULT NULL," +
                    "Preciots REAL DEFAULT NULL," +
                    "Preciotns REAL DEFAULT NULL," +
                    "Luz_publica REAL DEFAULT NULL," +
                    "Cargo_fijo REAL DEFAULT NULL," +
                    "Mora REAL DEFAULT NULL," +
                    "Tarifa_demanda REAL DEFAULT NULL," +
                    "Precio_kw REAL DEFAULT NULL," +
                    "Precio_pc REAL DEFAULT NULL," +
                    "Cargo_fijo_btdp REAL DEFAULT NULL," +
                    "Multas_varias REAL DEFAULT NULL," +
                    "Cobro_instalaciones REAL DEFAULT NULL," +
                    "Cobro_reconexiones REAL DEFAULT NULL," +
                    "Cobro_multa REAL DEFAULT NULL," +
                    "Activo INTEGER DEFAULT 0," +
                    "IdUsuarioCreo INTEGER DEFAULT NULL," +
                    "Fecha_Creacion TEXT DEFAULT NULL," +
                    "Fecha_Modificacion TEXT DEFAULT NULL," +
                    "IdUsuarioModifico INTEGER DEFAULT NULL," +
                    "Cargo_fijo_btdfp REAL DEFAULT NULL," +
                    "Tarifa_demandafp REAL DEFAULT NULL," +
                    "Precio_kwfp REAL DEFAULT NULL," +
                    "Precio_pcfp REAL DEFAULT NULL," +
                    "Precio_luz_autoproductor REAL DEFAULT NULL," +
                    "Cargo_fijo_autoproductor REAL DEFAULT NULL," +
                    "PRIMARY KEY([IdInstitucion],[IdPeriodoParametros])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [MESES_MORA_PAGADA] (" +
                    "IdRecibo INTEGER NOT NULL," +
                    "NoMes INTEGER DEFAULT NULL," +
                    "Anno INTEGER DEFAULT NULL," +
                    "MoraPagada INTEGER DEFAULT 0," +
                    "Anulado INTEGER DEFAULT 0," +
                    "PRIMARY KEY([IdRecibo])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [MESES_MORA_PROFORMA] (" +
                    "IdProforma INTEGER NOT NULL," +
                    "NoMes INTEGER DEFAULT NULL," +
                    "Anno INTEGER DEFAULT NULL," +
                    "MoraPagada INTEGER DEFAULT 0," +
                    "Anulado INTEGER DEFAULT 0," +
                    "PRIMARY KEY([IdProforma])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [MESES_PROFORMA] (" +
                    "idproformadet INTEGER NOT NULL," +
                    "idproforma INTEGER DEFAULT NULL," +
                    "IdUsuarioServicio INTEGER DEFAULT NULL," +
                    "nomes INTEGER DEFAULT NULL," +
                    "mes TEXT DEFAULT NULL," +
                    "idrenglon INTEGER DEFAULT NULL," +
                    "descripcion TEXT DEFAULT NULL," +
                    "cantidad REAL DEFAULT NULL," +
                    "anno INTEGER DEFAULT NULL,"+
                    "PRIMARY KEY([idproformadet])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [PARAMETROS] (" +
                    "idparametro INTEGER NOT NULL," +
                    "parametro TEXT DEFAULT NULL," +
                    "VALOR REAL DEFAULT NULL," +
                    "PRIMARY KEY([idparametro])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [RUTA_LECTURA] (" +
                    "IdRuta INTEGER NOT NULL," +
                    "Nombre TEXT DEFAULT NULL," +
                    "Activo TEXT DEFAULT NULL," +
                    "IdTecnicoDef INTEGER DEFAULT NULL," +
                    "PRIMARY KEY([IdRuta])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [RUTA_TECNICO] (" +
                    "IdRutaTecnico INTEGER NOT NULL," +
                    "IdTecnico INTEGER DEFAULT NULL," +
                    "IdRuta INTEGER DEFAULT NULL," +
                    "Activo INTEGER DEFAULT 0," +
                    "Fecha_Agr TEXT DEFAULT NULL," +
                    "User_Agr INTEGER DEFAULT NULL," +
                    "Fecha_Mod TEXT DEFAULT NULL," +
                    "User_Mod INTEGER DEFAULT NULL," +
                    "PRIMARY KEY ([IdRutaTecnico])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [TECNICOS] (" +
                    "IdTecnico INTEGER NOT NULL," +
                    "Nombre TEXT DEFAULT NULL," +
                    "Dpi TEXT DEFAULT NULL," +
                    "Direccion TEXT DEFAULT NULL," +
                    "Activo INTEGER DEFAULT 0," +
                    "Codigo TEXT DEFAULT NULL," +
                    "Clave TEXT DEFAULT NULL," +
                    "PRIMARY KEY ([IdTecnico])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [USUARIOS_POR_RUTA] (" +
                    "IdRuta INTEGER NOT NULL," +
                    "IdItinerario INTEGER NOT NULL," +
                    "IdUsuarioServicio INTEGER NOT NULL," +
                    "NombreItinerario TEXT DEFAULT NULL," +
                    "Orden INTEGER DEFAULT NULL," +
                    "PRIMARY KEY ([IdRuta],[IdItinerario], [IdUsuarioServicio])" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE [PAGOS_DETALLE_REP] (" +
                    "id INTEGER NOT NULL," +
                    "idusuarioservicio INTEGER NOT NULL," +
                    "nombreusuarioservicio TEXT DEFAULT NULL," +
                    "nocontador TEXT DEFAULT NULL," +
                    "nomarchamo TEXT DEFAULT NULL," +
                    "tiposervicio TEXT DEFAULT NULL," +
                    "tipotarifa TEXT DEFAULT NULL," +
                    "nomes INTEGER DEFAULT NULL," +
                    "año INTEGER DEFAULT NULL," +
                    "voltios REAL DEFAULT NULL," +
                    "lectura_anterior REAL DEFAULT NULL," +
                    "lectura_actual REAL DEFAULT NULL," +
                    "consumo REAL DEFAULT NULL," +
                    "fecha_inicio TEXT DEFAULT NULL," +
                    "fecha_fin TEXT DEFAULT NULL," +
                    "dias_facturados INTEGER DEFAULT NULL," +
                    "mes_operacion TEXT DEFAULT NULL," +
                    "fecha_emision TEXT DEFAULT NULL," +
                    "cargo_fijo REAL DEFAULT NULL," +
                    "consumo_energia_ts REAL DEFAULT NULL," +
                    "importe_ts REAL DEFAULT NULL," +
                    "consumo_energia_tns REAL DEFAULT NULL," +
                    "importe_tns REAL DEFAULT NULL," +
                    "consumo_energia_total REAL DEFAULT NULL," +
                    "importe_consumo_total REAL DEFAULT NULL," +
                    "alumbrado_publico REAL DEFAULT NULL," +
                    "fecha_sistema TEXT DEFAULT NULL," +
                    "potencia_contratada REAL DEFAULT NULL," +
                    "potencia_maxima REAL DEFAULT NULL," +
                    "importe_potencia_maxima REAL DEFAULT NULL," +
                    "servicio_bajo_demanda INTEGER DEFAULT 0," +
                    "IdPago INTEGER DEFAULT NULL," +
                    "servicio_bajo_demandafp INTEGER DEFAULT 0," +
                    "PRIMARY KEY ([id],[idusuarioservicio])" +
                    ");";
            db.execSQL(sql);

        } catch (Exception e) {
            return  false;
        }
        return true;
    }
}
