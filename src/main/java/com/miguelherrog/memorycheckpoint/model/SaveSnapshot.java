package com.miguelherrog.memorycheckpoint.model;

import java.time.LocalDateTime;

public class SaveSnapshot {
    private int id;
    private int gameId;
    private String sha256Hash;
    private long fileSizeBytes;
    private String backupFilePath;
    private LocalDateTime createdAt;

    public SaveSnapshot() {}

    public SaveSnapshot(int gameId, String sha256Hash, long fileSizeBytes, String backupFilePath) {
        this.gameId = gameId;
        this.sha256Hash = sha256Hash;
        this.fileSizeBytes = fileSizeBytes;
        this.backupFilePath = backupFilePath;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }

    public String getSha256Hash() { return sha256Hash; }
    public void setSha256Hash(String sha256Hash) { this.sha256Hash = sha256Hash; }

    public long getFileSizeBytes() { return fileSizeBytes; }
    public void setFileSizeBytes(long fileSizeBytes) { this.fileSizeBytes = fileSizeBytes; }

    public String getBackupFilePath() { return backupFilePath; }
    public void setBackupFilePath(String backupFilePath) { this.backupFilePath = backupFilePath; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}