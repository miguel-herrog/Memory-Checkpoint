# Memory Checkpoint

Emulator session launcher and manager built in **Java** that automates bidirectional cloud save synchronization, **SHA-256** integrity verification, and historical version control using **MariaDB/MySQL**.

*Project Status: Core MVP Completed. Moving to GUI and Cloud Integration.*

## Overview & Problem Statement

Classic emulators overwrite save files (`.sav`, `.srm`) directly on disk without version control. Memory Checkpoint acts as a session wrapper that:

1. **Pre-Launch:** Verifies the local save file's SHA-256 hash.
2. **In-Game (Session Tracking):** Launches the emulator process via `ProcessBuilder` and monitors active playtime in the background.
3. **Post-Exit (Versioning):** Computes the new SHA-256 hash upon closing the emulator. If the save file changed, it registers the session and incremental backup in the database.

## Tech Stack

* **Language:** Java 21 (OOP, ProcessBuilder, Java NIO.2, MessageDigest SHA-256).
* **Database & Persistence:** MariaDB (InnoDB) + JDBC (DAO Pattern and ACID transactions).
* **Build Tool:** Apache Maven.

## Roadmap

* [x] **Architecture & Database Design:** Relational schema definition (`sql/schema.sql`).
* [x] **Phase 1:** Maven project setup and JDBC persistence layer (DAO pattern).
* [x] **Phase 2:** SHA-256 integrity verification engine and session monitoring.
* [ ] **Phase 3:** Java Swing Desktop GUI (Game selector, active console, session launcher).
* [ ] **Phase 4:** Cloud integration for bidirectional save sync.
