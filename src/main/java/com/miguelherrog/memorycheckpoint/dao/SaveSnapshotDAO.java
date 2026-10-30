package com.miguelherrog.memorycheckpoint.dao;

import com.miguelherrog.memorycheckpoint.model.SaveSnapshot;
import com.miguelherrog.memorycheckpoint.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveSnapshotDAO {

    public boolean insertSnapshot(SaveSnapshot snapshot) {
        String sql = "INSERT INTO save_snapshots (game_id, sha256_hash, file_size_bytes, backup_file_path) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, snapshot.getGameId());
            pstmt.setString(2, snapshot.getSha256Hash());
            pstmt.setLong(3, snapshot.getFileSizeBytes());
            pstmt.setString(4, snapshot.getBackupFilePath());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar el snapshot: " + e.getMessage());
            return false;
        }
    }
}