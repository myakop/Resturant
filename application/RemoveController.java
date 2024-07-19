package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
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
import javafx.stage.Stage;

public class RemoveController implements Initializable {
	
	@FXML
	ListView<Object> list;
	@FXML
	ComboBox<DeliveryArea> areas;
	@Override
	public void initialize(URL location, ResourceBundle resources) {// fill in the list all the objects that we choose
		areas.getItems().addAll(Main.res.getAreas().values());
		
	}
	
	
	public void getCook (ActionEvent event)throws IOException{
		
		list.getItems().clear();
		list.getItems().addAll(Main.res.getCooks().values());
	}
	public void getComp (ActionEvent event)throws IOException{
		list.getItems().clear();
		list.getItems().addAll(Main.res.getComponenets().values());
	}
	public void getCust (ActionEvent event)throws IOException{
		list.getItems().clear();
		list.getItems().addAll(Main.res.getCustomers().values());
	}
	public void getdev (ActionEvent event)throws IOException{
		list.getItems().clear();
		list.getItems().addAll(Main.res.getDeliveries().values());
	}
	public void getdevperson (ActionEvent event)throws IOException{
		list.getItems().clear();
		list.getItems().addAll(Main.res.getDeliveryPersons().values());
	}
	public void getdevarea (ActionEvent event)throws IOException{
		list.getItems().clear();
		list.getItems().addAll(Main.res.getAreas().values());
	}
	public void getOrder (ActionEvent event)throws IOException{
		list.getItems().clear();
		for(Order ord : Main.res.getOrders().values()) {
			if(ord.getDelivery() != null) {
				list.getItems().add(ord);
			}
		}
	}
	public void getDish (ActionEvent event)throws IOException{
		list.getItems().clear();
		list.getItems().addAll(Main.res.getDishes().values());
	}
	
	
	public void removeobject(ActionEvent event)throws IOException{ // remove the object that we choose
		
	try {
		int Select = list.getSelectionModel().getSelectedIndex();
		if(list.getItems().get(Select) instanceof Cook) {
			if(Main.res.removeCook((Cook)list.getItems().get(Select))) {
				list.getItems().remove(Select);
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully remove");
			aler.showAndWait();
			}
		}
		int Select2 = list.getSelectionModel().getSelectedIndex();
		if(list.getItems().get(Select2) instanceof Customer) {
			if(Main.res.removeCustomer((Customer)list.getItems().get(Select))) {
				list.getItems().remove(Select2);
			Main.update();
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully remove");
			aler.showAndWait();
		}
		}
		int Select3 = list.getSelectionModel().getSelectedIndex();
		if(list.getItems().get(Select3) instanceof Delivery) {
			if(Main.res.removeDelivery((Delivery)list.getItems().get(Select))) {
				
				list.getItems().remove(Select3);
			Main.update();
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully remove");
			aler.showAndWait();
		}
		}
		int Select4 = list.getSelectionModel().getSelectedIndex();
		if(list.getItems().get(Select4) instanceof Component) {
			if(Main.res.removeComponent((Component)list.getItems().get(Select))) {
				list.getItems().remove(Select4);
			Main.update();
			Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully remove");
			aler.showAndWait();
		}
		}
		int Select5 = list.getSelectionModel().getSelectedIndex();
		if(list.getItems().get(Select5) instanceof DeliveryArea) {
		if(	Main.res.removeDeliveryArea((DeliveryArea)list.getItems().get(Select5), areas.getValue())) {
			areas.getItems().remove(list.getItems().get(Select5));
			list.getItems().remove(Select5);
			
			Main.update();
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully remove");
			aler.showAndWait();
		}
		}
		int Select6 = list.getSelectionModel().getSelectedIndex();
		if(list.getItems().get(Select6) instanceof Order) {
			if(Main.res.removeOrder((Order)list.getItems().get(Select))) {
				list.getItems().remove(Select6);
			Main.update();
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully remove");
			aler.showAndWait();
		}
		}
		int Select7 = list.getSelectionModel().getSelectedIndex();
		if(list.getItems().get(Select7) instanceof Dish) {
			if(Main.res.removeDish((Dish)list.getItems().get(Select))) {
				list.getItems().remove(Select7);
			Main.update();
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully remove");
		
			aler.showAndWait();
		}
		}
		int Select8 = list.getSelectionModel().getSelectedIndex();
		if(list.getItems().get(Select8) instanceof DeliveryPerson) {
			if(Main.res.removeDeliveryPerson((DeliveryPerson)list.getItems().get(Select))) {
				list.getItems().remove(Select8);
			Main.update();
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully remove");
			aler.showAndWait();
		}
		}
	
	
	}
	
		catch(IndexOutOfBoundsException e){
			Main.update();Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("nothing choose or no more things to remove");
			aler.showAndWait();
		}
		
}

		
	
	
	public void backmanger(ActionEvent event) throws IOException{
		Parent	root = FXMLLoader.load(getClass().getResource("manger.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
	

}
