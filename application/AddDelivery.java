package application;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
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

public class AddDelivery implements Initializable{
	
	@FXML
	 ComboBox<DeliveryPerson> devperson;
	@FXML
	 ComboBox<DeliveryArea> devarea;
	@FXML
	ListView<Order> list1 , list2;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		devarea.getItems().addAll(Main.res.getAreas().values()); // set the comboBox in all delivery area
		devperson.getItems().addAll(Main.res.getDeliveryPersons().values()); // set the combobox in all delivery person
		for(Order ord :Main.res.getOrders().values()) {
			if(ord.getDelivery()==null) {  
				list1.getItems().add(ord); // add to the list the orders that without delivery
			}
		}	
	}
	public void addact(ActionEvent event) throws IOException {
		try {
		int Select = list1.getSelectionModel().getSelectedIndex();
		list2.getItems().add(list1.getItems().get(Select));
		list1.getItems().remove(list1.getItems().get(Select));
		}catch(IndexOutOfBoundsException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("no selected thing");
			aler.showAndWait();
		}                                                        
	}                                                         //choosing the orders to add delivery
	 public void remove(MouseEvent Action) {
		 try {
			int Select = list2.getSelectionModel().getSelectedIndex();
			list1.getItems().add(list2.getItems().get(Select));
			list2.getItems().remove(Select);
			}catch(IndexOutOfBoundsException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("no selected thing");
				aler.showAndWait();
			}
		}
	
	public void submitadddelivery(ActionEvent event) throws IOException{
		try {
			if(list2.getItems().isEmpty()) {
				throw new NullPointerException();
			}
		TreeSet<Order> tree = new TreeSet<Order>();
		tree.addAll(list2.getItems());
		Main.res.createAIMacine(devperson.getValue(), devarea.getValue(), tree); // craete the relevent delivers for this orders
		Iterator<Delivery> itrator = Main.res.createAIMacine(devperson.getValue(), devarea.getValue(), tree).iterator();
		
		while(itrator.hasNext()) {
			Delivery d = itrator.next();
			checkid(d);
			if(Main.res.addDelivery(d)){ // add all the delivers 
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
		}
		}catch(NullPointerException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("one of the feilds is null");
			aler.showAndWait();
		}
		
		
		
		
	}
	public void Back(ActionEvent event) throws IOException{ // back to add screen
		Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
		Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void checkid(Delivery dev) { // check the last id
		int x=0;
		for (int c : Main.res.getDeliveries().keySet()) {
			if (c>x)
				x=c;
		}
		dev.setId(x+1);
		return;
	
	
	
	
	}
}
