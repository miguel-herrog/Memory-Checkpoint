package com.miguelherrog.memorycheckpoint.model;

import java.time.LocalDateTime;

public class PlaySession {
    private int id;
    private int profileId;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private long durationSeconds;

    public PlaySession() {}

    public PlaySession(int profileId, LocalDateTime startedAt, LocalDateTime endedAt, long durationSeconds) {
        this.profileId = profileId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.durationSeconds = durationSeconds;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProfileId() { return profileId; }
    public void setProfileId(int profileId) { this.profileId = profileId; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }

    public long getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(long durationSeconds) { this.durationSeconds = durationSeconds; }
}