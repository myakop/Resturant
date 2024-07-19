package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import Model.Delivery;
import Model.DeliveryPerson;
import Model.Restaurant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class getDeliveryByPersonConeroller implements Initializable{
	@FXML
	ComboBox<DeliveryPerson> dev;
	@FXML
	ComboBox<Integer> month;
	@FXML
	ListView<Delivery> list;
	@Override
	public void initialize(URL location, ResourceBundle resources) { // fill the combobox in all delivery persons
		ArrayList<DeliveryPerson> custs = new ArrayList<>(Main.res.getDeliveryPersons().values());
		ObservableList<DeliveryPerson> cust2 = FXCollections.observableArrayList(custs);
		dev.setItems(cust2);
		dev.setVisibleRowCount(4);
        ObservableList<Integer> months = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12); // fill all the months
		
		month.setItems(months);
		month.setVisibleRowCount(4);
		
	}
	public void getDeilveryByPerson (ActionEvent event)throws IOException{ // get delivery by person 
		list.getItems().clear();
		list.getItems().addAll(Main.res.getDeliveriesByPerson(dev.getValue(),month.getValue()));
	}
	public void gottoquery(ActionEvent event) throws IOException{
		Parent	root = FXMLLoader.load(getClass().getResource("query.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		
	
	

}
