
package models;


public class Reparto {
    private int id_reparto;
    private int id_filmografia;
    private String nombre_actor;
    private String papel;

    public Reparto(int id_reparto, int id_filmografia, String nombre_actor, String papel) {
        this.id_reparto = id_reparto;
        this.id_filmografia = id_filmografia;
        this.nombre_actor = nombre_actor;
        this.papel = papel;
    }

    public int getId_reparto() {
        return id_reparto;
    }

    public void setId_reparto(int id_reparto) {
        this.id_reparto = id_reparto;
    }

    public int getId_filmografia() {
        return id_filmografia;
    }

    public void setId_filmografia(int id_filmografia) {
        this.id_filmografia = id_filmografia;
    }

    public String getNombre_actor() {
        return nombre_actor;
    }

    public void setNombre_actor(String nombre_actor) {
        this.nombre_actor = nombre_actor;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
    

    @Override
    public String toString() {
        return "Reparto{" + "id_reparto=" + id_reparto + ", id_filmografia=" + id_filmografia + ", nombre_actor=" + nombre_actor + ", papel=" + papel + '}';
    }
    
}
