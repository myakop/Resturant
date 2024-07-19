package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import Model.Customer;
import Model.Dish;
import Model.Order;


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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddOrderController implements Initializable{
	Parent root;
	Stage st;
	Scene cn;
	
	@FXML
	ComboBox<Customer> customer;
	@FXML
	ListView<Dish> dish1;
	@FXML
	ListView<Dish> dish2;

	@Override
	public void initialize(URL location, ResourceBundle resources) { // set the all the dishes and customers to comboBox and list
		
	    dish1.getItems().addAll(Main.res.getDishes().values());
	    customer.getItems().addAll(Main.res.getCustomers().values());
	    
	}
	
	public void addact(MouseEvent Action) {
		try {
		int Select = dish1.getSelectionModel().getSelectedIndex();
		dish2.getItems().add(dish1.getItems().get(Select));
		}catch(IndexOutOfBoundsException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("no selected thing");
			aler.showAndWait();
		}
	}                                        //      choosing the dishes to create  new order 
	 public void remove(MouseEvent Action) {
		 try {
			int Select = dish2.getSelectionModel().getSelectedIndex();
			dish1.getItems().add(dish2.getItems().get(Select));
			dish2.getItems().remove(Select);
			}catch(IndexOutOfBoundsException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("no selected thing");
				aler.showAndWait();
			}
		}
	
	public void SubmittAddord (ActionEvent event) throws IOException{
		try {
			
			if(dish2.getItems().isEmpty()) {
				throw new NullPointerException(); // check that we choose dishes
			}
		
		ArrayList<Dish> dishes = new ArrayList<>();
		dishes.addAll(dish2.getItems());
		Order ord = new Order(customer.getValue(),dishes,null);
		checkdishid(ord);
		if(Main.res.addOrder(ord)) { // add new order 
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
		}catch(NullPointerException e){
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("one of the feilds is null");
			aler.showAndWait();
		
		}
		}
		
		
	
	
	public void checkdishid(Order ord) {
		int x=0;
		for (int c : Main.res.getOrders().keySet()) {
			if (c>x)
				x=c;
		}
		ord.setId(x+1);
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


