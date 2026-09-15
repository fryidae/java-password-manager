# java-password-manager
A secure desktop password manager built in Java that stores credentials in an encrypted vault protected by a master password.

## Concept
 Most people reuse weak passwords across dozens of accounts because remembering strong, unique passwords is impractical. This application solves that problem by storing all credentials in a single encrypted vault, so users only need to remember one strong master password.

## Features

| AES-256 Encryption | Vault data is encrypted using AES in GCM mode (authenticated encryption) |
| PBKDF2 Key Derivation | Master password is converted into a cryptographic key using PBKDF2 with a high iteration count |
| SHA-256 Integrity Verification | Detects tampering with the vault file |
| JavaFX GUI | Login screen and vault management interface built with JavaFX |
| Password Generator | Generates strong random passwords using `SecureRandom` |
| CRUD Operations | Add, view, edit, delete, and search stored credentials |
| Encrypted File Persistence | Vault saved to disk in encrypted form using object serialization |
| Auto-Lock | Returns to the login screen after a period of inactivity |
| Clipboard Clearing | Clears copied passwords from the clipboard automatically |
