package com.caybren.passwordmanager;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;

public class MainView {
	private Stage stage;
	private  PersonTableView table	;
	
	public MainView() {
		buildUI();
	}
	
	private void buildUI() {
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("Password Manager");
    	BorderPane root = new BorderPane();
    	/*
    	//9/23/26
    	root.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Event Filter");
				//event.consume() makes event handler not process

			}
    	});
    	root.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Event Handler");
				
			}
    		
    	});
    	*/
    	//9/24/26
    	
    	
    	
    	/*
    	//1. create text field
    	TextField textField = new TextField();
    	HBox hbox = new HBox(textField);
    	
    	//2. set prompt action
    	textField.setPromptText("Enter some text");
    	textField.setFocusTraversable(false); 
    	
    	//3. set on action
    	textField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("text entered:");
				// 4. get text from text field
				System.out.println(textField.getText());
				//5. set text
				textField.setText("I replaced yo shi");
				
			}
    	});
    	*/
    	
    	//VBox vbox = new VBox(); SAME AS HBOX but boxes are vertica
    	
    	//Button within HBox
    	/*Button button1 = new Button("One");
    	//button1.setPrefWidth(75);
    	button1.setMaxWidth(Integer.MAX_VALUE);
    	HBox.setMargin(button1, new Insets(10));
    	HBox.setHgrow(button1, Priority.SOMETIMES); //only grows after always 
    	//
    	Button button2 = new Button("Two");
    	//button2.setPrefWidth(75);
    	button2.setMaxWidth(Integer.MAX_VALUE);
    	HBox.setMargin(button2, new Insets(10));
    	HBox.setHgrow(button2, Priority.SOMETIMES);
    	//
    	Button button3 = new Button("Three");
    	//button3.setPrefWidth(75);
    	button3.setMaxWidth(Integer.MAX_VALUE);
    	HBox.setMargin(button3, new Insets(10));
    	HBox.setHgrow(button3, Priority.SOMETIMES);
    	//
    	Region region = new Region();
    	HBox.setHgrow(region, Priority.ALWAYS);
    	//
    	hbox.getChildren().addAll(button1,button2, region, button3);*/
    	
    	
    
    	table = new PersonTableView();
    	root.setCenter(table);
    	
    	
    	Scene scene = new Scene(root, 600, 300); //change hbox/vbox to root 
    	/*
    	//9/28 Style Sheets
    	//scene.getStylesheets().add("com.caybren.passwordmanager/stylesheet.css");
    	scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
    	
    	//scene.setFill(Color.PURPLE);
    	//scene.setCursor(Cursor.CROSSHAIR);
    
    	Image image = new Image(getClass().getResourceAsStream("save.png"));
    	ImageView iview = new ImageView(image);
    	Button Sbutton = new Button("Save", iview);
    	iview.setFitWidth(16);
    	iview.setFitHeight(16);
    	root.getChildren().add(Sbutton); //puts the button inside the root.
    	//button.setText("Save"); 
    	//button.setGraphic(iview);
    	Sbutton.setFont(new Font("Arial", 24	));
    	
    	
    	Sbutton.setOnAction(new EventHandler<ActionEvent>(){ 
    		
    		public void handle(ActionEvent event) { 
    			System.out.println("Save has been pressed ");
    		}
    	});
    	*/
    	stage.setScene(scene);
    	stage.show();		
	}

}
