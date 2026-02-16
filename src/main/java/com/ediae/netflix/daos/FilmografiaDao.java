package com.ediae.netflix.daos;

import java.sql.*;
import java.util.List;
import models.Filmografia;

public class FilmografiaDAO extends DAO<Filmografia> {

    // Map a ResultSet row to a Filmografia model instance
    @Override
    protected Filmografia fromResultSet(ResultSet rs) throws SQLException {
        return new Filmografia(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getDate("fecha_estreno"),
                rs.getString("sinopsis"),
                rs.getInt("pais_id"),
                rs.getInt("clasificacion_id")
        );
    }

    // Bind Filmografia fields to a prepared statement in the expected order
    @Override
    protected int setParams(PreparedStatement ps, Filmografia obj, int startIndex) throws SQLException {
        ps.setString(startIndex++, obj.getTitulo());
        ps.setDate(startIndex++, obj.getFecha_estreno());
        ps.setString(startIndex++, obj.getSinopsis());
        ps.setInt(startIndex++, obj.getPais_id());
        ps.setInt(startIndex++, obj.getClasificacion_id());
        return startIndex;
    }

    // SQL used by insert() in BaseDAO. Includes all non-ID fields.
    @Override
    protected String getInsertSQL() {
        return "INSERT INTO filmografia (titulo, fecha_estreno, sinopsis, pais_id, clasificacion_id)";
    }

    // SQL used by update() in BaseDAO. Updates all fields (except id, which is typically in WHERE).
    @Override
    protected String getUpdateSQL() {
        return "UPDATE filmografia SET titulo = ?, fecha_estreno = ?, sinopsis = ?, pais_id = ?, clasificacion_id = ?";
    }

    // Helper for BaseDAO to fetch the primary key from the entity
    @Override
    protected int getId(Filmografia obj) {
        return obj.getId();
    }

    // Table name used by generic SQL in BaseDAO
    @Override
    protected String getTableName() {
        return "filmografia";
    }

    // Number of parameters for insert/update (excluding id)
    @Override
    protected int getParamCount(Filmografia obj) {
        return 5; // titulo, fecha_estreno, sinopsis, pais_id, clasificacion_id
    }

    // ============================
    // BONUS: Convenience methods
    // ============================
    /**
     * Convenience method to insert a film without constructing the Filmografia
     * object externally. Keeps a single place to create the entity and reuse
     * insert(...) from BaseDAO.
     */
    public void directInsert(String titulo, Date fecha, String sinopsis, int paisId, int clasifId, Connection con)
            throws SQLException {
        Filmografia peli = new Filmografia(0, titulo, fecha, sinopsis, paisId, clasifId);
        insert(peli, con); // Uses inherited BaseDAO.insert
    }

    /**
     * Convenience method for console display. Prints a simple list of films
     * with essential fields for quick inspection.
     */
    public void printAll(Connection con) throws SQLException {
        System.out.println("=== PELICULAS ===");
        List<Filmografia> pelis = listAll(con); // Uses inherited BaseDAO.listAll
        if (pelis.isEmpty()) {
            System.out.println("No hay peliculas");
            System.out.println();
            return;
        }
        for (Filmografia p : pelis) {
            System.out.printf("ID:%d | %s (%s) | Pais:%d | Clasi:%d%n",
                    p.getId(), p.getTitulo(), p.getFecha_estreno(),
                    p.getPais_id(), p.getClasificacion_id());
        }
        System.out.println("Total: " + pelis.size() + "\\n");
    }
}
