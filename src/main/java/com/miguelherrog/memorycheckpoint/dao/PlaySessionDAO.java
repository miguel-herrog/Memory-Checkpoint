package com.miguelherrog.memorycheckpoint.dao;

import com.miguelherrog.memorycheckpoint.model.PlaySession;
import com.miguelherrog.memorycheckpoint.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaySessionDAO {

    public boolean insertSession(PlaySession session) {
        String sql = "INSERT INTO play_sessions (profile_id, started_at, ended_at, duration_seconds) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, session.getProfileId());
            pstmt.setObject(2, session.getStartedAt());
            pstmt.setObject(3, session.getEndedAt());
            pstmt.setLong(4, session.getDurationSeconds());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar la sesión de juego: " + e.getMessage());
            return false;
        }
    }
}