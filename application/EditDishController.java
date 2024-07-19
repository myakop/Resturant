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

public class EditDishController implements Initializable {
	@FXML
	TextField dishname ,timetomake;
	@FXML
	ComboBox<String> dishtype;
	@FXML
	ListView<Component> comp1 , comp2;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dishname.setText(Main.dish.getDishName());
		timetomake.setText(Integer.toString(Main.dish.getTimeToMake()));
		ObservableList<String> list = FXCollections.observableArrayList("Starter", "Main", "Dessert");
	    dishtype.setItems(list);
		                                                         // fill all the elements of details dish that we need to edit
		dishtype.setValue(Main.dish.getType().toString());
		comp1.getItems().addAll(Main.res.getComponenets().values());
		comp2.getItems().addAll(Main.dish.getComponenets());
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
	}                                       // edit the components 
	public void remove(MouseEvent Action) {
		 try {
			int Select = comp2.getSelectionModel().getSelectedIndex();
			comp1.getItems().add(comp2.getItems().get(Select));
			comp2.getItems().remove(Select);
			}catch(IndexOutOfBoundsException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("no selected thing");
				aler.showAndWait();
			}
		}
	
	public void submit(ActionEvent event) throws IOException{
		try {
			
			int i = Main.dish.getId();
			Main.res.removeDish(Main.dish);
			for(Dish dish : Main.res.getDishes().values()) {
			if(dish.getDishName().equals(dishname.getText())) {
				throw new SameNameException(); // check that dishname not exist
			}	
				}
			if(dishname.getText().equals("")  || comp2.getItems().isEmpty()) {
				throw new NullPointerException(); // check that nothing null
			}
		
		int time = Integer.parseInt(timetomake.getText());
		ArrayList<Component> comps = new ArrayList<>();
		comps.addAll(comp2.getItems());
		Dish dish = new Dish(dishname.getText(),DishType.valueOf(dishtype.getValue()),comps,time);
		dish.setId(i);
		
		if(Main.res.addDish(dish)) {
			Main.update();
			Alert aler = new Alert(AlertType.CONFIRMATION); // update this dish with the new details
			aler.setHeaderText("seccessfully edit");
			aler.showAndWait();	
			Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
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
	public void Back(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
		Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}

}
