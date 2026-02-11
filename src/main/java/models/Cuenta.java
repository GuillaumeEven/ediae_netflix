
package models;


public class Cuenta {
    private int id_cuenta;
    private String tipo_cuenta;
    private String nombre;
    private String password_hash;

    public Cuenta(int id_cuenta, String tipo_cuenta, String nombre, String password_hash) {
        this.id_cuenta = id_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.nombre = nombre;
        this.password_hash = password_hash;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "id_cuenta=" + id_cuenta + ", tipo_cuenta=" + tipo_cuenta + ", nombre=" + nombre + ", password_hash=" + password_hash + '}';
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
    
}
