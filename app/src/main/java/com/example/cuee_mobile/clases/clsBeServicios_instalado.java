package com.example.cuee_mobile.clases;

import com.google.gson.annotations.SerializedName;

public class clsBeServicios_instalado {

    public int IdInstalacion = 0;
    public int IdUsuarioServicio = 0;
    public String IdContador = "";
    public int IdVoltaje = 0;
    public int IdPotencia = 0;
    public int IdTransformador = 0;
    public int IdTipoServicio = 0;
    public String fecha = "";
    public String hora = "";
    public int IdTecnico = 0;
    public String contador_anterior = "";
    public String usuario_anterior = "";
    public String contador_siguiente = "";
    public String usuario_siguiente = "";
    public boolean modificacion_red = false;
    public int subestacion = 0;
    public int id_circuito = 0;
    public double IdGPS = 0;
    public String IdFotografia = "";
    public String direccion = "";
    public int IdMunicipio = 0;
    public int IdDepartamento = 0;
    public String Zona = "";
    public String Colonia = "";
    public String Avenida = "";
    public String Calle = "";
    public String Numero = "";
    public int centro = 0;
    public String id_poste_inicio = "";
    public String tension_nominal_acom = "";
    public int fases_de_conexion = 0;
    public int acometida_subt_area = 0;
    public int long_cable_acom = 0;
    public String tipo_de_conductor = "";
    public String donacion_acom = "";
    public String fecha_puesto_servicio = "";
    public String fecha_retiro_acom = "";
    public String num_serie_medido = "";
    public String tipo_medidor = "";
    public int voltaje_medidor = 0;
    public int voltaje_suministro = 0;
    public int corriente_nominal = 0;
    public int corriente_maxima = 0;
    public double kh = 0;
    public String Rr = "";
    public String fecha_puesto_servicio_m = "";
    public String fecha_retiro_medidor = "";
    public double coor_x = 0;
    public double coor_y = 0;
    public String zona_utm_medidor = "";
    public String fecha_ultimo_pago = "";
    public double numero_contrato = 0;
    public String fecha_contrato = "";
    public String hora_contrato = "";
    public boolean servicio_bajo_demanda = false;
    public double kw_contratada = 0;
    public double potencia_contratada = 0;
    public String ruta = "";
    public int itinerario = 0;
    public int IdUsuario = 0;
    public String fecha_creacion = "";
    public int idusuario_modifica = 0;
    public String fecha_modificacion = "";
    public boolean Activo = false;
    public int estado_servicio = 0;
    public int IdTipoUsuario = 0;
    public String num_tarjeta = "";
    public String tipo_registro = "";
    public boolean servicio_bajo_demandafp = false;
    public boolean es_autoproductor = false;
    public int Lectura_realizada = 0;
    public int Lectura_correcta = 0;

    public int getIdInstalacion() {
        return IdInstalacion;
    }

    public void setIdInstalacion(int idInstalacion) {
        IdInstalacion = idInstalacion;
    }

    public int getIdUsuarioServicio() {
        return IdUsuarioServicio;
    }

    public void setIdUsuarioServicio(int idUsuarioServicio) {
        IdUsuarioServicio = idUsuarioServicio;
    }

    public String getIdContador() {
        return IdContador;
    }

    public void setIdContador(String idContador) {
        IdContador = idContador;
    }

    public int getIdVoltaje() {
        return IdVoltaje;
    }

    public void setIdVoltaje(int idVoltaje) {
        IdVoltaje = idVoltaje;
    }

    public int getIdPotencia() {
        return IdPotencia;
    }

    public void setIdPotencia(int idPotencia) {
        IdPotencia = idPotencia;
    }

    public int getIdTransformador() {
        return IdTransformador;
    }

    public void setIdTransformador(int idTransformador) {
        IdTransformador = idTransformador;
    }

    public int getIdTipoServicio() {
        return IdTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        IdTipoServicio = idTipoServicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdTecnico() {
        return IdTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        IdTecnico = idTecnico;
    }

    public String getContador_anterior() {
        return contador_anterior;
    }

    public void setContador_anterior(String contador_anterior) {
        this.contador_anterior = contador_anterior;
    }

    public String getUsuario_anterior() {
        return usuario_anterior;
    }

    public void setUsuario_anterior(String usuario_anterior) {
        this.usuario_anterior = usuario_anterior;
    }

    public String getContador_siguiente() {
        return contador_siguiente;
    }

    public void setContador_siguiente(String contador_siguiente) {
        this.contador_siguiente = contador_siguiente;
    }

    public String getUsuario_siguiente() {
        return usuario_siguiente;
    }

    public void setUsuario_siguiente(String usuario_siguiente) {
        this.usuario_siguiente = usuario_siguiente;
    }

    public boolean isModificacion_red() {
        return modificacion_red;
    }

    public void setModificacion_red(boolean modificacion_red) {
        this.modificacion_red = modificacion_red;
    }

    public int getSubestacion() {
        return subestacion;
    }

    public void setSubestacion(int subestacion) {
        this.subestacion = subestacion;
    }

    public int getId_circuito() {
        return id_circuito;
    }

    public void setId_circuito(int id_circuito) {
        this.id_circuito = id_circuito;
    }

    public double getIdGPS() {
        return IdGPS;
    }

    public void setIdGPS(double idGPS) {
        IdGPS = idGPS;
    }

    public String getIdFotografia() {
        return IdFotografia;
    }

    public void setIdFotografia(String idFotografia) {
        IdFotografia = idFotografia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        IdMunicipio = idMunicipio;
    }

    public int getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        IdDepartamento = idDepartamento;
    }

    public String getZona() {
        return Zona;
    }

    public void setZona(String zona) {
        Zona = zona;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public String getAvenida() {
        return Avenida;
    }

    public void setAvenida(String avenida) {
        Avenida = avenida;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public int getCentro() {
        return centro;
    }

    public void setCentro(int centro) {
        this.centro = centro;
    }

    public String getId_poste_inicio() {
        return id_poste_inicio;
    }

    public void setId_poste_inicio(String id_poste_inicio) {
        this.id_poste_inicio = id_poste_inicio;
    }

    public String getTension_nominal_acom() {
        return tension_nominal_acom;
    }

    public void setTension_nominal_acom(String tension_nominal_acom) {
        this.tension_nominal_acom = tension_nominal_acom;
    }

    public int getFases_de_conexion() {
        return fases_de_conexion;
    }

    public void setFases_de_conexion(int fases_de_conexion) {
        this.fases_de_conexion = fases_de_conexion;
    }

    public int getAcometida_subt_area() {
        return acometida_subt_area;
    }

    public void setAcometida_subt_area(int acometida_subt_area) {
        this.acometida_subt_area = acometida_subt_area;
    }

    public int getLong_cable_acom() {
        return long_cable_acom;
    }

    public void setLong_cable_acom(int long_cable_acom) {
        this.long_cable_acom = long_cable_acom;
    }

    public String getTipo_de_conductor() {
        return tipo_de_conductor;
    }

    public void setTipo_de_conductor(String tipo_de_conductor) {
        this.tipo_de_conductor = tipo_de_conductor;
    }

    public String getDonacion_acom() {
        return donacion_acom;
    }

    public void setDonacion_acom(String donacion_acom) {
        this.donacion_acom = donacion_acom;
    }

    public String getFecha_puesto_servicio() {
        return fecha_puesto_servicio;
    }

    public void setFecha_puesto_servicio(String fecha_puesto_servicio) {
        this.fecha_puesto_servicio = fecha_puesto_servicio;
    }

    public String getFecha_retiro_acom() {
        return fecha_retiro_acom;
    }

    public void setFecha_retiro_acom(String fecha_retiro_acom) {
        this.fecha_retiro_acom = fecha_retiro_acom;
    }

    public String getNum_serie_medido() {
        return num_serie_medido;
    }

    public void setNum_serie_medido(String num_serie_medido) {
        this.num_serie_medido = num_serie_medido;
    }

    public String getTipo_medidor() {
        return tipo_medidor;
    }

    public void setTipo_medidor(String tipo_medidor) {
        this.tipo_medidor = tipo_medidor;
    }

    public int getVoltaje_medidor() {
        return voltaje_medidor;
    }

    public void setVoltaje_medidor(int voltaje_medidor) {
        this.voltaje_medidor = voltaje_medidor;
    }

    public int getVoltaje_suministro() {
        return voltaje_suministro;
    }

    public void setVoltaje_suministro(int voltaje_suministro) {
        this.voltaje_suministro = voltaje_suministro;
    }

    public int getCorriente_nominal() {
        return corriente_nominal;
    }

    public void setCorriente_nominal(int corriente_nominal) {
        this.corriente_nominal = corriente_nominal;
    }

    public int getCorriente_maxima() {
        return corriente_maxima;
    }

    public void setCorriente_maxima(int corriente_maxima) {
        this.corriente_maxima = corriente_maxima;
    }

    public double getKh() {
        return kh;
    }

    public void setKh(double kh) {
        this.kh = kh;
    }

    public String getRr() {
        return Rr;
    }

    public void setRr(String rr) {
        Rr = rr;
    }

    public String getFecha_puesto_servicio_m() {
        return fecha_puesto_servicio_m;
    }

    public void setFecha_puesto_servicio_m(String fecha_puesto_servicio_m) {
        this.fecha_puesto_servicio_m = fecha_puesto_servicio_m;
    }

    public String getFecha_retiro_medidor() {
        return fecha_retiro_medidor;
    }

    public void setFecha_retiro_medidor(String fecha_retiro_medidor) {
        this.fecha_retiro_medidor = fecha_retiro_medidor;
    }

    public double getCoor_x() {
        return coor_x;
    }

    public void setCoor_x(double coor_x) {
        this.coor_x = coor_x;
    }

    public double getCoor_y() {
        return coor_y;
    }

    public void setCoor_y(double coor_y) {
        this.coor_y = coor_y;
    }

    public String getZona_utm_medidor() {
        return zona_utm_medidor;
    }

    public void setZona_utm_medidor(String zona_utm_medidor) {
        this.zona_utm_medidor = zona_utm_medidor;
    }

    public String getFecha_ultimo_pago() {
        return fecha_ultimo_pago;
    }

    public void setFecha_ultimo_pago(String fecha_ultimo_pago) {
        this.fecha_ultimo_pago = fecha_ultimo_pago;
    }

    public double getNumero_contrato() {
        return numero_contrato;
    }

    public void setNumero_contrato(double numero_contrato) {
        this.numero_contrato = numero_contrato;
    }

    public String getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(String fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public String getHora_contrato() {
        return hora_contrato;
    }

    public void setHora_contrato(String hora_contrato) {
        this.hora_contrato = hora_contrato;
    }

    public boolean isServicio_bajo_demanda() {
        return servicio_bajo_demanda;
    }

    public void setServicio_bajo_demanda(boolean servicio_bajo_demanda) {
        this.servicio_bajo_demanda = servicio_bajo_demanda;
    }

    public double getKw_contratada() {
        return kw_contratada;
    }

    public void setKw_contratada(double kw_contratada) {
        this.kw_contratada = kw_contratada;
    }

    public double getPotencia_contratada() {
        return potencia_contratada;
    }

    public void setPotencia_contratada(double potencia_contratada) {
        this.potencia_contratada = potencia_contratada;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getItinerario() {
        return itinerario;
    }

    public void setItinerario(int itinerario) {
        this.itinerario = itinerario;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getIdusuario_modifica() {
        return idusuario_modifica;
    }

    public void setIdusuario_modifica(int idusuario_modifica) {
        this.idusuario_modifica = idusuario_modifica;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }

    public int getEstado_servicio() {
        return estado_servicio;
    }

    public void setEstado_servicio(int estado_servicio) {
        this.estado_servicio = estado_servicio;
    }

    public int getIdTipoUsuario() {
        return IdTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        IdTipoUsuario = idTipoUsuario;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipo_registro) {
        this.tipo_registro = tipo_registro;
    }

    public boolean isServicio_bajo_demandafp() {
        return servicio_bajo_demandafp;
    }

    public void setServicio_bajo_demandafp(boolean servicio_bajo_demandafp) {
        this.servicio_bajo_demandafp = servicio_bajo_demandafp;
    }

    public boolean isEs_autoproductor() {
        return es_autoproductor;
    }

    public void setEs_autoproductor(boolean es_autoproductor) {
        this.es_autoproductor = es_autoproductor;
    }

    public int getLectura_realizada() {
        return Lectura_realizada;
    }

    public void setLectura_realizada(int lectura_realizada) {
        Lectura_realizada = lectura_realizada;
    }

    public int getLectura_correcta() {
        return Lectura_correcta;
    }

    public void setLectura_correcta(int lectura_correcta) {
        Lectura_correcta = lectura_correcta;
    }
}
