package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Customer;
import Model.Dish;
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
import javafx.stage.Stage;

public class AddBlacklistController implements Initializable{
	@FXML
	ComboBox<Customer> customer;
	@FXML
	ListView<Customer> black;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		black.getItems().clear();
	    black.getItems().addAll(Main.res.getblacklist()); // set black customer in the list
	    customer.getItems().addAll(Main.res.getCustomers().values());// set all the customer in the comboBox
	    
	}
	
	public void Add (ActionEvent event) throws IOException{
		try {
			if(Main.res.addCustomerToBlackList(customer.getValue())) { // add customer to black
				black.getItems().add(customer.getValue()); // add this customer to the list
				Alert aler = new Alert(AlertType.CONFIRMATION);
		 		aler.setHeaderText("succesfully add ");
		 		aler.showAndWait();
			}
		}catch(NullPointerException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("choose a customer!");
			aler.showAndWait();
		}
	}
		
		public void Back(ActionEvent event) throws IOException{ //back to add
		Parent	root = FXMLLoader.load(getClass().getResource("Add.fxml"));
		Stage	 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		
	
	

}
