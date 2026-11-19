# Java Password Manager

A secure, desktop-based password manager built with Java and JavaFX. This application allows users to store, retrieve, and manage their login credentials locally using strong, authenticated encryption.

## Features

- **Master Password Authentication:** A single master password unlocks the vault.
- **Strong Cryptography:** Uses AES-256 in GCM mode for authenticated encryption, and PBKDF2 for secure key derivation.
- **Password Generator:** Generates strong, random passwords using `SecureRandom`.
- **CRUD Operations:** Add, view, edit, and delete password entries.
- **Instant Search:** Filter the vault instantly by site name.
- **Password Masking:** Passwords are hidden (`••••••••`) in the table view by default to prevent shoulder-surfing.
- **Auto-Lock:** The application automatically locks and returns to the login screen after 5 minutes of inactivity.
- **Secure File Persistence:** The vault is encrypted and saved to the local disk.

## Tech Stack

- **Language:** Java 17+
- **GUI Framework:** JavaFX 21
- **Build Tool:** Maven
- **Cryptography:** Java Cryptography Architecture (JCA) 

## Security Architecture

This application was built with a focus on secure local storage:
1.  **Key Derivation:** The master password is never stored. It is used to derive a 256-bit AES key using **PBKDF2 with HMAC-SHA256** and 600,000 iterations.
2.  **Unique Salt:** A cryptographically secure 16-byte salt is generated on first launch and stored in a separate file. This prevents rainbow table attacks.
3.  **Authenticated Encryption:** Vault data is encrypted using **AES/GCM/NoPadding**. GCM provides both confidentiality and integrity, meaning if an attacker tampers with a single bit of the encrypted file, decryption will fail loudly with an `AEADBadTagException`.
4.  **Unique IVs:** A fresh 12-byte Initialization Vector (IV) is generated for every single save operation to prevent pattern leakage.
5.  **Data Location:** Following macOS conventions, the encrypted vault and salt are stored in `~/Library/Application Support/PasswordManager/`.

## Prerequisites

- **Java Development Kit (JDK):** Version 17 or higher.
- **Maven:** Installed and configured.
- **JavaFX:** Managed automatically via Maven dependencies.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/fryidae/java-password-manager.git
   cd java-password-manager
   