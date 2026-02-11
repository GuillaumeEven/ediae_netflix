package com.ediae.netflix.daos;

import com.evendev.ediaebackend.models.Filmografia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmografiaDao extends BaseDao<Filmografia, Integer> {

    private static final String INSERT = "INSERT INTO Filmografia (titulo, fecha_estreno, sinopsis, clasificacion_id, pais_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Filmografia SET titulo = ?, fecha_estreno = ?, sinopsis = ?, clasificacion_id = ?, pais_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Filmografia WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM Filmografia";
    private static final String SELECT_BY_ID = "SELECT * FROM Filmografia WHERE id = ?";

    public FilmografiaDao() {
        super();
    }

    public FilmografiaDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getInsertSql() {
        return INSERT;
    }

    @Override
    protected String getUpdateSql() {
        return UPDATE;
    }

    @Override
    protected String getDeleteSql() {
        return DELETE;
    }

    @Override
    protected String getSelectAllSql() {
        return SELECT_ALL;
    }

    @Override
    protected String getSelectByIdSql() {
        return SELECT_BY_ID;
    }

    @Override
    protected void bindInsert(PreparedStatement stmt, Filmografia entity) throws SQLException {
        stmt.setString(1, entity.gettitulo());
        stmt.setDate(2, entity.getfecha_estreno());
        stmt.setString(3, entity.getsinopsis());
        stmt.setInt(4, entity.getclasificacion());
        stmt.setInt(5, entity.getpais());
    }

    @Override
    protected void bindUpdate(PreparedStatement stmt, Filmografia entity) throws SQLException {
        stmt.setString(1, entity.gettitulo());
        stmt.setDate(2, entity.getfecha_estreno());
        stmt.setString(3, entity.getsinopsis());
        stmt.setInt(4, entity.getclasificacion());
        stmt.setInt(5, entity.getpais());
        stmt.setInt(6, entity.getid());
    }

    @Override
    protected void bindId(PreparedStatement stmt, Integer id) throws SQLException {
        stmt.setInt(1, id);
    }

    // The mapRow method is used to convert a ResultSet row into a Filmografia object
    @Override
    protected Filmografia mapRow(ResultSet rs) throws SQLException {
        Filmografia filmo = new Filmografia(
            rs.getInt("id"),
            rs.getString("titulo"),
            rs.getDate("fecha_estreno"),
            rs.getString("sinopsis"),
            rs.getInt("clasificacion_id"),
            rs.getInt("pais_id")
        );
        // filmo.setid(rs.getInt("id"));
        // filmo.settitulo(rs.getString("titulo"));
        // filmo.setfecha_estreno(rs.getDate("fecha_estreno"));
        // filmo.setsinopsis(rs.getString("sinopsis"));
        // filmo.setclasificacion(rs.getInt("clasificacion_id"));
        // filmo.setpais(rs.getInt("pais_id"));
        return filmo;
    }

    @Override
    protected void setGeneratedId(Filmografia entity, Object generatedId) {
        if (generatedId instanceof Number) {
            entity.setid(((Number) generatedId).intValue());
        }
    }
}
