## Learning Log

## 9/24/26
- Learned the basic functions of HBox, VBox, and TextFields
- button.setMaxSize() accounts for the height and Width of buttons
- FlowPane makes buttons flow with the default horizontal orientation. (Vbox and Hbox combined)
       -Can set hGap, vGaps, and setAlignment
-AnchorPanes allow each child to have 4 anchors
      -set Left, Right, Top ,Bottom

## 9/23/26
-Found out how to make buttons trigger system output.
- Added button text and iview as arguments instead of separate statements.
- Labels work the same syntactically to set up as buttons.

## 9/22/26 
-The code for adding images to a button
      Button button = new Button();
    	button.setText("Save");
    	Image image = new Image(getClass().getResourceAsStream("file.png"));
    	ImageView iview = new ImageView(image);
    	button.setGraphic(iview);
    	root.setCenter(button);
      
## 9/18/26 — Environment Setup
- Tutorial only showed Windows install for JDK/Eclipse. Had to find Mac alternatives.
- Manual JavaFX setup in Eclipse kept failing with module path errors.
- Switched to Maven. Worked immediately.

## 9/15
- Start learning JavaFX programming via "Java Code Junkie" on YouTube.


  
