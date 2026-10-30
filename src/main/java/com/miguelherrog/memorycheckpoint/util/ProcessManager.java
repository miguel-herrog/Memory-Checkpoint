package com.miguelherrog.memorycheckpoint.util;

import java.io.IOException;

public class ProcessManager {

    public static long launchAndMonitor(String executablePath) {
        long startTime = System.currentTimeMillis();

        ProcessBuilder processBuilder = new ProcessBuilder(executablePath);

        try {
            System.out.println("Lanzando proceso externo: " + executablePath);

            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("El emulador se ha cerrado con código de salida: " + exitCode);

        } catch (IOException e) {
            System.err.println("Error de E/S al intentar lanzar el emulador: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("El hilo de monitorización fue interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();

        long durationSeconds = (endTime - startTime) / 1000;
        return durationSeconds;
    }
}