# Project Reflection: Java Password Manager

## Overview
This document records the challenges, obstacles, and lessons learned while building the Java Password Manager for my CMPS 260 Honors Contract.

## 10/21/26
- added final touches to app.java
- app now runs end to end 

## 10/20/26
-AppController complete
-MainView updated
-Supporting methods for LoginView and VaultView added

## 10/16/26
- LoginView complete with buildUI() and LoginView().
- VaultView Vbox in top region because BorderPane.setTop() accepts only one node. I need both a MenuBar and a ToolBar up there so I wrap them in a VBox (which stacks vertically), then put the VBox in the top region.
-VaultView complete with buildUI(), getters, setters, and default constructor. menu, toolbar, and table.
-VaultEntry complete with buildUI() with four input fields and default constructor.
-begin AppController

## 10/15/26
-PasswordGen class complete with PasswordGen()
-VaultEntry complete with VaultEntry() getters and setters
-Started Vault with Vault()

## 10/14/26
-Finished CryptoUtils w/ deriveKey(), encode(), and decode() methods.

## 10/13/26
- Finished learning basic JavaFX controls, methods, syntax, classes, etc.
- Started CryptoUtils with generateSalt()
- Local and remote branches had diverged, so I merged the remote history, pushed to main, and renamed my local branch to match GitHub's default, fixing the split branch problem once and for all.

## 9/28/26
- I fixed the CSS loading issue by using getClass().getResource() with .toExternalForm() instead of a plain path string, so JavaFX could properly locate the stylesheet.
- Eclipse's e(fx)clipse plugin threw a "WidgetProperties compatibility error" when creating FXML files, so I worked around it by creating the .fxml file manually and adding the .fxml extension.
- My branch had diverged from main (5 commits ahead, 13 behind), so I pulled the remote changes into my branch, resolved the merge, was 6 commits ahead then I opened a pull request to fully fix the situation.

## 9/22/26
- I got stumped on why the button image was taking up the whole scene, but I found out I had to set a width and height for the image
      iview.setFitWidth(16);
    	iview.setFitHeight(16);
  
## 9/18/26 Installing JDK
- Tutorial only showed Windows installation for JDK/Eclipse. Had to find Mac alternatives.
- Manual JavaFX setup in Eclipse kept failing with module path errors.
- Switched to Maven. Worked immediately.
