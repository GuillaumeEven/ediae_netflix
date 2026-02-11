
package models;

import java.sql.Date;

public class Acceso {
    private int id_acceso;
    private int id_filmografia;
    private int id_cuenta;
    private Date fecha_acceso;
    private int tipo_suscripcion_id;

    public Acceso(int id_acceso, int id_filmografia, int id_cuenta, Date fecha_acceso, int tipo_suscripcion_id) {
        this.id_acceso = id_acceso;
        this.id_filmografia = id_filmografia;
        this.id_cuenta = id_cuenta;
        this.fecha_acceso = fecha_acceso;
        this.tipo_suscripcion_id = tipo_suscripcion_id;
    }

    public int getId_acceso() {
        return id_acceso;
    }

    public void setId_acceso(int id_acceso) {
        this.id_acceso = id_acceso;
    }

    public int getId_filmografia() {
        return id_filmografia;
    }

    public void setId_filmografia(int id_filmografia) {
        this.id_filmografia = id_filmografia;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Date getFecha_acceso() {
        return fecha_acceso;
    }

    public void setFecha_acceso(Date fecha_acceso) {
        this.fecha_acceso = fecha_acceso;
    }

    public int getTipo_suscripcion_id() {
        return tipo_suscripcion_id;
    }

    public void setTipo_suscripcion_id(int tipo_suscripcion_id) {
        this.tipo_suscripcion_id = tipo_suscripcion_id;
    }

    @Override
    public String toString() {
        return "Acceso{" + "id_acceso=" + id_acceso + ", id_filmografia=" + id_filmografia + ", id_cuenta=" + id_cuenta + ", fecha_acceso=" + fecha_acceso + ", tipo_suscripcion_id=" + tipo_suscripcion_id + '}';
    }
    
}
