package com.miguelherrog.memorycheckpoint.dao;
import com.miguelherrog.memorycheckpoint.model.GameProfile;
import com.miguelherrog.memorycheckpoint.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GameProfileDAO {

    public boolean insertProfile(GameProfile profile) {
        String sql = "INSERT INTO game_profiles (game_name, emulator_executable, local_save_path) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, profile.getGameName());
            pstmt.setString(2, profile.getEmulatorExecutable());
            pstmt.setString(3, profile.getLocalSavePath());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error en JDBC al insertar el perfil: " + e.getMessage());
            return false;
        }
    }

    public GameProfile getProfileById(int id) {
        String sql = "SELECT * FROM game_profiles WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                GameProfile profile = new GameProfile();
                profile.setId(rs.getInt("id"));
                profile.setGameName(rs.getString("game_name"));
                profile.setEmulatorExecutable(rs.getString("emulator_executable"));
                profile.setLocalSavePath(rs.getString("local_save_path"));
                profile.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                return profile;
            }

        } catch (SQLException e) {
            System.err.println("Error al realiza la consulta: " + e.getMessage());
            return null;
        }
        return null;
    }
}