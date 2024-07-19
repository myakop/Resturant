package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Customer;
import Model.Delivery;
import Model.ExpressDelivery;
import Model.Order;
import Model.RegularDelivery;
import Utils.MyFileLogWriter;
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

public class getDeliveryController implements Initializable {
	
	
	@FXML
	ComboBox<Delivery> dev;
	@FXML
	 public ListView<String> ordtocust;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Delivery> delvery = new ArrayList<>();
		for(Delivery dev : Main.res.getDeliveries().values()) {
			if(!dev.isDelivered()) {
				delvery.add(dev);
			}
		}
		                                                                          // set the combobox in all the delivers
		ObservableList<Delivery> cust2 = FXCollections.observableArrayList(delvery);
		dev.setItems(cust2);
		dev.setVisibleRowCount(4);	
	}
	
	public void getdelivery(ActionEvent event) throws IOException{ // update that the delivery that is reached
		Main.res.deliver(dev.getValue());
		if(dev.getValue() instanceof RegularDelivery)
			for(Order o : ((RegularDelivery)dev.getValue()).getOrdertree()) {
				ordtocust.getItems().add(o+" had reached to Customer "+o.getCustomer());
				
				
			}
			if(dev.getValue() instanceof ExpressDelivery) {

				ordtocust.getItems().add(((ExpressDelivery)dev.getValue()).getExorder()+" had reached to Customer "+((ExpressDelivery)dev.getValue()).getExorder().getCustomer());
				
			}
	}
	
	public void gottoquery(ActionEvent event) throws IOException{
		Parent	root = FXMLLoader.load(getClass().getResource("query.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		

}
