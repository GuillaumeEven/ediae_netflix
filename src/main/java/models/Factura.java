
package models;

import java.sql.Date;


public class Factura {
    private int num_factura;
    private int suscripcion_id;
    private int cuenta_id;
    private double importe_pvp;
    private String metodo_pago;
    private Date fecha_factura;

    public Factura(int num_factura, int suscripcion_id, int cuenta_id, double importe_pvp, String metodo_pago, Date fecha_factura) {
        this.num_factura = num_factura;
        this.suscripcion_id = suscripcion_id;
        this.cuenta_id = cuenta_id;
        this.importe_pvp = importe_pvp;
        this.metodo_pago = metodo_pago;
        this.fecha_factura = fecha_factura;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public int getSuscripcion_id() {
        return suscripcion_id;
    }

    public void setSuscripcion_id(int suscripcion_id) {
        this.suscripcion_id = suscripcion_id;
    }

    public int getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(int cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public double getImporte_pvp() {
        return importe_pvp;
    }

    public void setImporte_pvp(double importe_pvp) {
        this.importe_pvp = importe_pvp;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    
    @Override
    public String toString() {
        return "Factura{" + "num_factura=" + num_factura + ", suscripcion_id=" + suscripcion_id + ", cuenta_id=" + cuenta_id + ", importe_pvp=" + importe_pvp + ", metodo_pago=" + metodo_pago + ", fecha_factura=" + fecha_factura + '}';
    }
    
}
