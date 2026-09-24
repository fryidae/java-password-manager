package com.caybren.passwordmanager;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
    	
    	
    	
    	primaryStage.setTitle("Password Manager");
    	StackPane root = new StackPane();
    	root.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Event Filter");
				//event.consum() makes event handler not process
			}
    	});
    	root.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Event Handler");
				
			}
    		
    	});
    	Scene scene = new Scene(root, 600, 300);
    	
    	//scene.setFill(Color.PURPLE);
    	//scene.setCursor(Cursor.CROSSHAIR);
    	
    	Image image = new Image(getClass().getResourceAsStream("save.png"));
    	ImageView iview = new ImageView(image);
    	Button button = new Button("Save", iview);
    	iview.setFitWidth(16);
    	iview.setFitHeight(16);
    	root.getChildren().add(button); //puts the button inside the root.
    	//button.setText("Save"); 
    	//button.setGraphic(iview);
    	button.setFont(new Font("Arial", 24	));
    	
    	
    	button.setOnAction(new EventHandler<ActionEvent>(){ 
    		
    		public void handle(ActionEvent event) { 
    			System.out.println("Save has been pressed ");
    		}
    	});
    	
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	
    	
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}