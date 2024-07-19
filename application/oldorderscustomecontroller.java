package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class oldorderscustomecontroller implements Initializable {
	@FXML
	ListView<Order> list ;
	
	 public void gotoorders(ActionEvent event) throws IOException{
		 Parent root = FXMLLoader.load(getClass().getResource("dishestoneworder.fxml"));
		 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow(); // create new order
			Scene cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	 public void replaceorder(ActionEvent event) throws IOException{
		 try {
		 int Select = list.getSelectionModel().getSelectedIndex();
    	 Main.saveorder.addAll(list.getItems().get(Select).getDishes()); // replace order that the customer do
		 
		 Parent root = FXMLLoader.load(getClass().getResource("payandaddorder.fxml"));
		 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene cn = new Scene(root);
			st.setScene(cn);
			st.show();
	
	 }catch(IndexOutOfBoundsException e){
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("nothing choose ");
			aler.showAndWait();
		}
	 }
	 public void back(ActionEvent event) throws IOException{
		 Parent root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
		 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for(Order ord : Main.res.getOrders().values()) { // fill the list with orders by the customer
			if((ord.getDelivery() != null) && ord.getCustomer().equals(Main.cust)) {
				list.getItems().add(ord);
			}
		}
	}
	public void submitreplace (ActionEvent event) throws IOException{
		int Select = list.getSelectionModel().getSelectedIndex();
		Main.res.addOrder(list.getItems().get(Select));
	}
	
	

}
