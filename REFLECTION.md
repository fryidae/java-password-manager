# Project Reflection: Java Password Manager

## Overview
This document records the challenges, obstacles, and lessons learned while building the Java Password Manager for my CMPS 260 Honors Contract.

## 9/18/26 Installing JDK
- Tutorial only showed Windows installation for JDK/Eclipse. Had to find Mac alternatives.
- Manual JavaFX setup in Eclipse kept failing with module path errors.
- Switched to Maven. Worked immediately.

## 9/22/26
- I got stumped on why the button image was taking up the whole scene, but I found out I had to set a width and height for the image
      iview.setFitWidth(16);
    	iview.setFitHeight(16);
