package com.caybren.passwordmanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

public class PersonView extends VBox {
	
	//Instance Variables
	private TableView<Person> table;
	private TableColumn<Person, String> firstNameColumn;
	private TableColumn<Person, String> lastNameColumn;
	private TableColumn<Person, Integer> ageColumn;
	
	private TextField lastName;
	private TextField firstName;
	private TextField age;
	
	private Button btnAdd;
	private Button btnDel;
	
	
	public PersonView() {
		buildUI();
	}

	private void buildUI() {
		table = new TableView<Person>();
    	table.setEditable(true);
    	
    	firstNameColumn = new TableColumn<Person, String>("First Name");
    	firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
    	firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	firstNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>(){
    	
    		public void handle(CellEditEvent<Person, String> event) {
    			Person person = event.getRowValue();
    			person.setFirstName(event.getNewValue());
    		}
    	});
    	
    	lastNameColumn = new TableColumn<Person, String>("Last Name");
    	lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
    	lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	lastNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>(){
        	
    		public void handle(CellEditEvent<Person, String> event) {
    			Person person = event.getRowValue();
    			person.setLastName(event.getNewValue());
    		}
    	});
    	
    	ageColumn = new TableColumn<Person, Integer>("Age");
    	ageColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
    	ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    	ageColumn.setOnEditCommit(new EventHandler<CellEditEvent<Person, Integer>>(){
        	
    		public void handle(CellEditEvent<Person, Integer> event) {
    			Person person = event.getRowValue();
    			person.setAge(event.getNewValue());
    		}
    	});
    	
    	table.getColumns().add(firstNameColumn);
    	table.getColumns().add(lastNameColumn);
    	table.getColumns().add(ageColumn);
    	
    	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	
    	firstName = new TextField();
    	firstName.setPromptText("First Name");
    	firstName.setTooltip(new Tooltip("Enter First Name"));
    	
    	lastName = new TextField();
    	lastName.setPromptText("Last Name");
    	lastName.setTooltip(new Tooltip("Enter Last Name"));
    	
    	age = new TextField();
    	age.setPromptText("Age");
    	age.setTooltip(new Tooltip("Enter Age"));
    	
    	HBox hbox1 = new HBox();
    	hbox1.setSpacing(10);
    	hbox1.setPadding(new Insets(10, 10, 10, 10));
    	hbox1.getChildren().addAll(firstName, lastName, age);
    	
    	btnAdd = new Button("Add");
    	btnAdd.setPrefWidth(100);
    	btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Person person = new Person(firstName.getText(), lastName.getText(), Integer.parseInt(age.getText()));
				add(person);
				
			}
    		
    	});
    	
    	btnDel = new Button("Delete");
    	btnDel.setPrefWidth(100);
    	btnDel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int row = table.getSelectionModel().getSelectedIndex();
				delete(row);
			}
    		
    	});
    	
    	
    	HBox hbox2 = new HBox();
    	hbox2.setPadding(new Insets(0, 10, 10, 10));
    	hbox2.setAlignment(Pos.TOP_RIGHT);
    	hbox2.getChildren().addAll(btnAdd, btnDel);
    	
    	
		
    	getChildren().addAll(table, hbox1, hbox2);
	}
	
	public void add(Person person) {
		table.getItems().add(person);
		clearFields();
	}
	
	public void delete(int row) {
		if(row >=  0) {
			table.getItems().remove(row);
			table.getSelectionModel().clearSelection();
		}
	}
	
	private void clearFields() {
		firstName.clear();
		lastName.clear();
		age.clear();
		firstName.requestFocus();
	}

}


