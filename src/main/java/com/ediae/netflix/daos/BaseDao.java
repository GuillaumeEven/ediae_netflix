package com.ediae.netflix.daos;

import com.ediae.netflix.utils.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<Model, Id> {

    protected final Connection connection;

    protected BaseDao() {
        this(DBManager.connect());
    }

    protected BaseDao(Connection connection) {
        this.connection = connection;
    }

    protected abstract String getInsertSql();

    protected abstract String getUpdateSql();

    protected abstract String getDeleteSql();

    protected abstract String getSelectAllSql();

    protected abstract String getSelectByIdSql();

    protected abstract void bindInsert(PreparedStatement stmt, Model entity) throws SQLException;

    protected abstract void bindUpdate(PreparedStatement stmt, Model entity) throws SQLException;

    protected abstract void bindId(PreparedStatement stmt, Id id) throws SQLException;

    protected abstract Model mapRow(ResultSet rs) throws SQLException;

    protected abstract void setGeneratedId(Model entity, Object generatedId);

    public void insert(Model entity) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(getInsertSql(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            bindInsert(stmt, entity);
            stmt.executeUpdate();

            // Get the new generated ID and set it to the entity
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Object generatedId = generatedKeys.getObject(1);
                    setGeneratedId(entity, generatedId);
                    System.out.println("Inserted entity with generated ID: " + generatedId);
                }
            }
        }
    }

    public void update(Model entity) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(getUpdateSql())) {
            bindUpdate(stmt, entity);
            stmt.executeUpdate();
        }
    }

    public void delete(Id id) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(getDeleteSql())) {
            bindId(stmt, id);
            stmt.executeUpdate();
        }
    }

    public List<Model> findAll() throws SQLException {
        List<Model> results = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        // this is a more traditional way to handle resources, but it requires manual closing in the finally block
        try {
            stmt = connection.prepareStatement(getSelectAllSql());
            rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(mapRow(rs));
            }
        } finally {
            // NOTE: This manual closing is unnecessary with try-with-resources,
            // but shown here for pedagogical purposes
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
        return results;
    }

    public Optional<Model> findById(Id id) throws SQLException {
        // using try-with-resources to ensure proper resource management
        try (PreparedStatement stmt = connection.prepareStatement(getSelectByIdSql())) {
            // apply the id to the prepared statement
            // stmt.setInt(1, id); // this is an example, the actual implementation depends on the model
            bindId(stmt, id);
            try (ResultSet rs = stmt.executeQuery()) {
                // if because of the id, we expect at most one result, we can directly return an Optional<Model>
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }
}
