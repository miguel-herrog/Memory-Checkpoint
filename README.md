# Memory Checkpoint

Emulator session launcher and manager built in **Java** that automates bidirectional cloud save synchronization, **SHA-256** integrity verification, and historical version control using **MySQL**.

> **Project Status:** *Active Development (Work in Progress)*

## Overview & Problem Statement
Classic emulators overwrite save files (`.sav`, `.srm`) directly on disk without version control, and generic cloud sync clients can corrupt save data if they sync files while the emulator is still writing to them. **Memory Checkpoint** acts as a session wrapper that:

1. **Pre-Launch (Pull):** Verifies the local save file's SHA-256 hash against the MySQL database and automatically downloads the latest save state if progress was made on another device.
2. **In-Game (Session Tracking):** Launches the emulator process via `ProcessBuilder` and monitors active playtime in the background.
3. **Post-Exit (Push & Versioning):** Asynchronously computes (`ExecutorService`) the new SHA-256 hash upon closing the emulator. If the save file changed, it compresses the file, uploads an incremental backup to the cloud, and records the transaction in MySQL—allowing instant rollback to any previous checkpoint.

## Tech Stack
* **Language:** Java 21 (OOP, Multithreading with `ExecutorService`, `ProcessBuilder`, Java NIO.2, `MessageDigest` SHA-256).
* **Database & Persistence:** MySQL (InnoDB) + JDBC (DAO Pattern and ACID transactions).
* **Build Tool:** Apache Maven.

## Roadmap
- [ ] **Architecture & Database Design:** Relational schema definition (`sql/schema.sql`).
- [ ] **Phase 1:** Maven project setup and JDBC persistence layer (DAO pattern and MySQL transactions).
- [ ] **Phase 2:** SHA-256 integrity verification engine and process wrapper using `ProcessBuilder` and `ExecutorService`.
- [ ] **Phase 3:** Incremental packaging service and remote cloud synchronization (Pre-launch pull / Post-exit push).
- [ ] **Phase 4:** Command-Line Interface (CLI) and checkpoint rollback system.
