package com.miguelherrog.memorycheckpoint;

import com.miguelherrog.memorycheckpoint.dao.PlaySessionDAO;
import com.miguelherrog.memorycheckpoint.dao.SaveSnapshotDAO;
import com.miguelherrog.memorycheckpoint.model.PlaySession;
import com.miguelherrog.memorycheckpoint.model.SaveSnapshot;
import com.miguelherrog.memorycheckpoint.util.HashUtil;
import com.miguelherrog.memorycheckpoint.util.ProcessManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO MEMORY CHECKPOINT ===");

        // Configuración de la sesión (El ID 1 corresponde a The Legend of Zelda en tu BD)
        int profileId = 1;
        String emuladorCmd = "ls"; // Usamos 'sleep' para que el programa espere 2 segundos simulando el juego
        String saveFilePath = "test/save.srm";
        String backupsDir = "backups/";

        try {
            // 0. Preparativos automáticos para que la prueba no falle por falta de archivos
            File saveFile = new File(saveFilePath);
            saveFile.getParentFile().mkdirs();
            if (!saveFile.exists()) saveFile.createNewFile();
            new File(backupsDir).mkdirs();

            // 1. Calcular huella SHA-256 antes de abrir el juego
            String initialHash = HashUtil.calculateSHA256(saveFilePath);
            System.out.println("[INFO] Hash inicial: " + initialHash);

            // 2. Iniciar sesión de juego
            System.out.println("[INFO] Lanzando emulador...");
            LocalDateTime startedAt = LocalDateTime.now();

            // SIMULACIÓN: Simulamos que el emulador guarda la partida escribiendo texto en el archivo
            Files.writeString(Path.of(saveFilePath), "Partida guardada a las " + LocalDateTime.now() + "\n");

            // Esto pausará la ejecución 2 segundos reales
            long duration = ProcessManager.launchAndMonitor(emuladorCmd);
            LocalDateTime endedAt = LocalDateTime.now();

            // 3. Registrar la duración de la partida en MariaDB
            PlaySession session = new PlaySession(profileId, startedAt, endedAt, duration);
            PlaySessionDAO sessionDAO = new PlaySessionDAO();
            sessionDAO.insertSession(session);
            System.out.println("[ÉXITO] Sesión registrada: " + duration + " segundos.");

            // 4. Calcular huella SHA-256 al cerrar el juego
            String finalHash = HashUtil.calculateSHA256(saveFilePath);
            System.out.println("[INFO] Hash final: " + finalHash);

            // 5. Comparar hashes y hacer el volcado si la partida avanzó
            if (!initialHash.equals(finalHash)) {
                System.out.println("[DETECTADO] El archivo de guardado ha cambiado. Creando backup...");

                String backupFileName = "save_" + System.currentTimeMillis() + ".srm";
                Path backupPath = Path.of(backupsDir, backupFileName);

                // Copiar el archivo físicamente a la carpeta de backups
                Files.copy(Path.of(saveFilePath), backupPath, StandardCopyOption.REPLACE_EXISTING);

                // Registrar el backup en la base de datos
                long fileSizeBytes = Files.size(backupPath);
                SaveSnapshot snapshot = new SaveSnapshot(profileId, finalHash, fileSizeBytes, backupPath.toString());
                SaveSnapshotDAO snapshotDAO = new SaveSnapshotDAO();

                if (snapshotDAO.insertSnapshot(snapshot)) {
                    System.out.println("[ÉXITO] Backup histórico guardado en: " + backupPath);
                } else {
                    System.err.println("[ERROR] Fallo al registrar el snapshot en la base de datos.");
                }
            } else {
                System.out.println("[INFO] No se detectaron cambios en la partida. No se creará backup.");
            }

        } catch (IOException e) {
            System.err.println("[ERROR] Problema leyendo/escribiendo los archivos: " + e.getMessage());
        }

        System.out.println("=== CICLO FINALIZADO CON ÉXITO ===");
    }
}