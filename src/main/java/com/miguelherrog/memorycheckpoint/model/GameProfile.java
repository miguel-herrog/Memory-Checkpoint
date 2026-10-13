package com.miguelherrog.memorycheckpoint.model;
import java.time.LocalDateTime;

public class GameProfile {
    private int id;
    private String gameName;
    private String emulatorExecutable;
    private String localSavePath;
    private java.time.LocalDateTime createdAt;

    public GameProfile() {
    }

    public GameProfile(int id, String gameName, String emulatorExecutable, String localSavePath, LocalDateTime createdAt) {
        this.id = id;
        this.gameName = gameName;
        this.emulatorExecutable = emulatorExecutable;
        this.localSavePath = localSavePath;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getEmulatorExecutable() {
        return emulatorExecutable;
    }

    public void setEmulatorExecutable(String emulatorExecutable) {
        this.emulatorExecutable = emulatorExecutable;
    }

    public String getLocalSavePath() {
        return localSavePath;
    }

    public void setLocalSavePath(String localSavePath) {
        this.localSavePath = localSavePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "GameProfile{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", emulatorExecutable='" + emulatorExecutable + '\'' +
                ", localSavePath='" + localSavePath + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}