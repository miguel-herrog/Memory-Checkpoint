package com.miguelherrog.memorycheckpoint;
import com.miguelherrog.memorycheckpoint.util.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.out.println("Problema al intentar la conexion.");
            throw new RuntimeException(e);
        }
    }
}
