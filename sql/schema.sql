CREATE DATABASE IF NOT EXISTS memory_checkpoint
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE memory_checkpoint;

CREATE TABLE IF NOT EXISTS game_profiles (
    id INT AUTO_INCREMENT PRIMARY KEY,           
    game_name VARCHAR(120) NOT NULL,             
    emulator_executable VARCHAR(500) NOT NULL,   
    local_save_path VARCHAR(500) NOT NULL,       
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS save_snapshots (
    id INT AUTO_INCREMENT PRIMARY KEY,           
    game_id INT NOT NULL,                        
    sha256_hash CHAR(64) NOT NULL,               
    file_size_bytes BIGINT NOT NULL,             
    backup_file_path VARCHAR(500) NOT NULL,      
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_snapshot_game
        FOREIGN KEY (game_id) REFERENCES game_profiles(id)
        ON DELETE CASCADE,

    UNIQUE KEY uk_game_hash (game_id, sha256_hash)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS play_sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,           
    game_id INT NOT NULL,                        
    started_at DATETIME NOT NULL,                
    ended_at DATETIME NOT NULL,                  
    duration_seconds INT NOT NULL,               

    CONSTRAINT fk_session_game
        FOREIGN KEY (game_id) REFERENCES game_profiles(id)
        ON DELETE CASCADE
) ENGINE=InnoDB;
