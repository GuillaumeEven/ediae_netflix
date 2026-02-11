
package models;

import java.sql.Date;

public class Suscripcion {
    private int id;
    private int cuenta_id;
    private int tipo_id;
    private Date fecha_contratacion;
    private Date fecha_fin;

    public Suscripcion(int id, int cuenta_id, int tipo_id, Date fecha_contratacion, Date fecha_fin) {
        this.id = id;
        this.cuenta_id = cuenta_id;
        this.tipo_id = tipo_id;
        this.fecha_contratacion = fecha_contratacion;
        this.fecha_fin = fecha_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(int cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "Suscripcion{" + "id=" + id + ", cuenta_id=" + cuenta_id + ", tipo_id=" + tipo_id + ", fecha_contratacion=" + fecha_contratacion + ", fecha_fin=" + fecha_fin + '}';
    }
    
}
