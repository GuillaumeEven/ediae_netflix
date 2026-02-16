package com.ediae.netflix.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Base class for all DAO implementations. Provides common CRUD operations.
 * Subclasses implement table-specific mapping and SQL fragments.
 * 
 * USAGE: Pass Connection explicitly to each method for transaction control.
 * 
 * @param <T> Your entity class (User, Order, Product, etc.)
 */
public abstract class DAO<T> {
    
    // ============================
    // ABSTRACT METHODS - SUBCLASSES MUST IMPLEMENT THESE
    // ============================
    
    /**
     * Converts a database row (ResultSet) into your specific object type T
     * Called automatically by findById(), listAll(), etc.
     */
    protected abstract T fromResultSet(ResultSet rs) throws SQLException;
    
    /**
     * Sets the object fields as parameters in SQL PreparedStatement
     * @param startIndex Where to start numbering parameters (usually 1)
     * @return Next available parameter index (for chaining more parameters)
     * 
     * Example: for User(name, email), sets ? for name, ? for email, returns 3
     */
    protected abstract int setParams(PreparedStatement ps, T obj, int startIndex) throws SQLException;
    
    /**
     * INSERT SQL fragment - just the columns part (no VALUES clause)
     * MUST return same # of columns as getParamCount()
     * Example: "INSERT INTO users (name, email)"
     * Full SQL becomes: "INSERT INTO users (name, email) VALUES (?, ?)"
     */
    protected abstract String getInsertSQL();
    
    /**
     * UPDATE SQL fragment - just the SET clause (no WHERE clause)
     * Full SQL becomes: "UPDATE users SET name = ?, email = ? WHERE id = ?"
     */
    protected abstract String getUpdateSQL();
    
    /**
     * Extracts the ID field from your object T (for WHERE id = ? clauses)
     */
    protected abstract int getId(T obj);
    
    /**
     * Your database table name
     */
    protected abstract String getTableName();
    
    /**
     * How many parameters setParams() will bind (excludes ID for updates)
     * Must match # of columns in getInsertSQL()/getUpdateSQL()
     */
    protected abstract int getParamCount(T obj);

    // ============================
    // CRUD METHODS
    // ============================
    
    /**
     * Creates new record. Auto-generates ID if database supports it.
     * Caller manages Connection life-cycle (try-with-resources recommended).
     * 
     * @param obj Object to insert
     * @param con Database connection (same con for transaction across DAOs)
     */
    public final void insert(T obj, Connection con) throws SQLException {
        // Builds: INSERT INTO table(col1, col2) VALUES (?, ?)
        String sql = getInsertSQL() + " VALUES (" + questionMarks(getParamCount(obj)) + ")";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setParams(ps, obj, 1);
            ps.executeUpdate();
        }
    }
    
    /**
     * Finds one record by primary key ID
     * @return Optional.empty() if not found
     */
    public final Optional<T> findById(int id, Connection con) throws SQLException {
        String tableName = getTableName();
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(fromResultSet(rs));
                }
                return Optional.empty();
            }
        }
    }
    
    /**
     * Returns ALL records from table
     */
    public final List<T> listAll(Connection con) throws SQLException {
        String tableName = getTableName();
        String sql = "SELECT * FROM " + tableName;
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(fromResultSet(rs));
            }
            return list;
        }
    }
    
    /**
     * Updates existing record by ID
     */
    public final void update(T obj, Connection con) throws SQLException {
        int updateParamsCount = getParamCount(obj);
        String sql = getUpdateSQL() + " WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            setParams(ps, obj, 1);
            ps.setInt(updateParamsCount + 1, getId(obj));
            ps.executeUpdate();
        }
    }
    
    /**
     * Deletes record by ID
     */
    public final void delete(T obj, Connection con) throws SQLException {
        String tableName = getTableName();
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, getId(obj));
            ps.executeUpdate();
        }
    }
    
    // ============================
    // SQL HELPER METHODS
    // ============================
    
    /**
     * Generates ?, ?, ? strings for VALUES clause
     * Example: questionMarks(3) returns "?, ?, ?"
     */
    private String questionMarks(int count) {
        if (count <= 0) throw new IllegalArgumentException("Param count must be > 0");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("?");
            if (i < count - 1) sb.append(",");
        }
        return sb.toString();
    }
}
