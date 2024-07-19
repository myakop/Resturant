package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Exceptions.SameNameException;
import Model.Component;
import Model.Dish;
import Utils.DishType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddDishcontroller  implements Initializable{
	Parent root;
	Stage st;
	Scene cn;
	@FXML
	TextField dishname ,timetomake;
	@FXML
	ComboBox<String> dishtype;
	@FXML
	ListView<Component> comp1 , comp2;

	@Override
	public void initialize(URL location, ResourceBundle resources) { // set the comps and dishtype to the list and comboBox
		ObservableList<String> list = FXCollections.observableArrayList("Starter", "Main", "Dessert");
	    dishtype.setItems(list);
	    comp1.getItems().addAll(Main.res.getComponenets().values());
	    
	}
	
	public void addact(MouseEvent Action) {
		try {
		int Select = comp1.getSelectionModel().getSelectedIndex();
		comp2.getItems().add(comp1.getItems().get(Select));
		}catch(IndexOutOfBoundsException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("no selected thing");
			aler.showAndWait();
		}
	}                                              // choose the comps that we need to create new dish
	 public void remove(MouseEvent Action) {
		 try {
			int Select = comp2.getSelectionModel().getSelectedIndex();
			
			comp2.getItems().remove(Select);
			}catch(IndexOutOfBoundsException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("no selected thing");
				aler.showAndWait();
			}
		}
	
	public void SubmittAddDish (ActionEvent event) throws IOException{
		
		try {
			for(Dish dish : Main.res.getDishes().values()) {
			if(dish.getDishName().equals(dishname.getText())) { // check if dishname is not exist
				throw new SameNameException();
			}	
				}
			if(dishname.getText().equals("")) { // check that in not null
				throw new NullPointerException();
			}
			
		int time = Integer.parseInt(timetomake.getText());
		ArrayList<Component> comps = new ArrayList<>();
		comps.addAll(comp2.getItems());
		Dish dish = new Dish(dishname.getText(),DishType.valueOf(dishtype.getValue()),comps,time);
		checkdishid(dish); 
		if(Main.res.addDish(dish)) {  // add new dish
 			Main.update();
			Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully add");
			aler.showAndWait();	
			Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
	}
		else {
			
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("feild add");
			aler.showAndWait();
		}
		}catch (NullPointerException e){
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("one of the feilds are null plaese check ");
			aler.showAndWait();
			}catch(SameNameException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("this name of dish is already exist");
				aler.showAndWait();
			}catch(NumberFormatException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("you inter illegal value");
				aler.showAndWait();
			}
		
		}
	
	
	public void checkdishid(Dish dish) {
		int x=0;
		for (int c : Main.res.getDishes().keySet()) {
			if (c>x)
				x=c;
		}
		dish.setId(x+1);
		return;
	}
	public void Back(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("Add.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	
	
	

}
