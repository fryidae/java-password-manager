# Project Reflection: Java Password Manager

## Overview
This document records the challenges, obstacles, and lessons learned while building the Java Password Manager for my CMPS 260 Honors Contract.

## 9/28/26
- I fixed the CSS loading issue by using getClass().getResource() with .toExternalForm() instead of a plain path string, so JavaFX could properly locate the stylesheet.
- Eclipse's e(fx)clipse plugin threw a "WidgetProperties compatibility error" when creating FXML files, so I worked around it by creating the .fxml file manually and adding the .fxml extension.

## 9/22/26
- I got stumped on why the button image was taking up the whole scene, but I found out I had to set a width and height for the image
      iview.setFitWidth(16);
    	iview.setFitHeight(16);
  
## 9/18/26 Installing JDK
- Tutorial only showed Windows installation for JDK/Eclipse. Had to find Mac alternatives.
- Manual JavaFX setup in Eclipse kept failing with module path errors.
- Switched to Maven. Worked immediately.
