# Project Reflection: Java Password Manager
**CMPS 260 Honors Contract**
**Author:** Caybren Conston

## Overview
This document serves as a reflection on the challenges, obstacles, and lessons learned while independently building a Secure Password Manager in Java for my Honors Contract. The project required moving beyond the basic Java syntax taught in CMPS 260 to master JavaFX for the user interface, and the Java Cryptography Architecture (JCA) for secure data storage. 

## Key Challenges and Solutions

### 1. Environment Setup and Tooling (September)
*   **The Challenge:** The initial tutorial series I followed only provided Windows-specific instructions for installing the JDK and Eclipse. Additionally, manually configuring JavaFX in Eclipse led to persistent module path errors (`Module javafx.controls not found`).
*   **The Solution:** I had to adapt to macOS and initially worked around the FXML wizard bug (the e(fx)clipse "WidgetProperties" error) by manually creating `.fxml` files. However, the ultimate fix was abandoning the manual setup and pivoting to **Maven**. Maven handled the JavaFX dependencies automatically and resolved all module path issues, teaching me the importance of using robust build tools.

### 2. Version Control and Git (September - October)
*   **The Challenge:** As I moved between IntelliJ and Eclipse, my local branch and the remote GitHub repository diverged significantly (5 commits ahead, 13 behind). Pushing my code resulted in authentication errors and rejection.
*   **The Solution:** I learned how to use the terminal to resolve merge conflicts. I executed `git pull --allow-unrelated-histories`, resolved the conflicts, and successfully merged the branches. I also learned to configure Git to use a Personal Access Token (PAT) instead of a password, and I successfully renamed my local branch to `main` to prevent future divergence issues.

### 3. Cryptography and Data Integrity (October)
*   **The Challenge:** Transitioning from basic Java to advanced cryptography was the most difficult part of the project. I had to understand how to safely turn a user's master password into an AES key, and why Initialization Vectors (IVs) are critical.
*   **The Solution:** I built the `CryptoUtils` class from scratch. I utilized `SecureRandom` to generate a 16-byte salt and implemented PBKDF2 with 600,000 iterations to derive a 256-bit AES key. For encryption, I used AES-GCM, ensuring that a fresh, random 12-byte IV was generated for every single save operation. This ensured that even if the vault file was stolen, the data remained unreadable without the master password, and any tampering with the file would throw an `AEADBadTagException`.

### 4. UI Integration and Styling (October - November)
*   **The Challenge:** Tying the logic to the JavaFX UI involved specific layout quirks, such as wrapping the MenuBar and ToolBar in a `VBox` because `BorderPane.setTop()` only accepts one node. Additionally, loading CSS stylesheets initially failed due to path issues.
*   **The Solution:** I resolved the CSS loading issue by using `getClass().getResource().toExternalForm()` rather than a plain string path. I also implemented UI polish features, including a custom `TableCell` to mask passwords with dots (`••••••`), a `FilteredList` wrapped in a `SortedList` to enable instant search, and a `PauseTransition` to automatically lock the app after 5 minutes of inactivity. 

## Reflection on SLOs
*   **Critical and Creative Inquiry:** I independently researched cryptographic concepts and evaluated different algorithms (hashing vs. key derivation) to design a secure architecture.
*   **Integrative and Applied Learning:** I combined object-oriented programming, file I/O, cryptographic APIs, and GUI design into a single, cohesive application that solves a real-world problem (credential management).
*   **Leadership Through Integrity and Initiative:** I managed my own timeline and milestones. When I hit roadblocks with Eclipse or Git, I adapted, researched solutions, and held myself accountable for delivering a working, secure product.

## Conclusion
Building this password manager was a massive step up from the standard coursework. While the initial setup and Git merge conflicts were incredibly frustrating, debugging them taught me resilience. I ended up with a fully functional, encrypted desktop application. If I were to build this again, I would use Maven and a proper Git branching strategy from day one. I am proud of the final product and the security standards I was able to implement.