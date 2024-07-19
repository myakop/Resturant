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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class editContoller  implements Initializable {
	@FXML
	ComboBox<Cook> cook;
	@FXML
	ComboBox<Dish> dish;
	@FXML
	ComboBox<Order> order;
	@FXML
	ComboBox<Delivery> delivery;
	@FXML
	ComboBox<Component> component;
	@FXML
	ComboBox<DeliveryPerson> devperson;
	@FXML
	ComboBox<DeliveryArea> devarea;
	@FXML
	ComboBox<Customer> customer;
	
	@Override
public void initialize(URL location, ResourceBundle resources) { // set all the combobox in the relevant items 
		
	    dish.getItems().addAll(Main.res.getDishes().values());
	    cook.getItems().addAll(Main.res.getCooks().values());
	    for(Order ord : Main.res.getOrders().values()) {
	    	if(ord.getDelivery()==null) {
	    order.getItems().add(ord);
	    	}
	    	if(ord.getDelivery() != null) {
	    		if(!ord.getDelivery().isDelivered()) {
	    			order.getItems().add(ord);
	    		}
	    	}
	    }
	    delivery.getItems().addAll(Main.res.getDeliveries().values());
	    component.getItems().addAll(Main.res.getComponenets().values());
	    devperson.getItems().addAll(Main.res.getDeliveryPersons().values());
	    devarea.getItems().addAll(Main.res.getAreas().values());
	    customer.getItems().addAll(Main.res.getCustomers().values());
	    
	}
	public void Back(ActionEvent event) throws IOException{
		Main.cust = customer.getValue();
		 Parent root = FXMLLoader.load(getClass().getResource("manger.fxml"));
		 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		 Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
                                                  // choosing the item that we needs to edit from the comboBox
	}
public void goeditcustomer(ActionEvent event) throws IOException{
	Main.cust = customer.getValue();
	 Parent root = FXMLLoader.load(getClass().getResource("editcustmoer.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
	 Scene	 cn = new Scene(root);
		st.setScene(cn);
		st.show();
}
  public void goeditcomp(ActionEvent event) throws IOException{
	  Main.comp = component.getValue();
	 Parent root = FXMLLoader.load(getClass().getResource("editcomponent.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
	 Scene	 cn = new Scene(root);
		st.setScene(cn);
		st.show();
}
  
public void goeditcook(ActionEvent event) throws IOException{
	Main.cook = cook.getValue();
	 Parent root = FXMLLoader.load(getClass().getResource("editcook.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
	 Scene	 cn = new Scene(root);
		st.setScene(cn);
		st.show();
}
public void goeditdelivery(ActionEvent event) throws IOException{
	Main.dev = delivery.getValue();
	 Parent root = FXMLLoader.load(getClass().getResource("editdelivery.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
	 Scene	 cn = new Scene(root);
		st.setScene(cn);
		st.show();
}
public void goeditdevperson(ActionEvent event) throws IOException{
	Main.devperson = devperson.getValue();
	Parent root = FXMLLoader.load(getClass().getResource("editdeliveryperson.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
	 Scene	 cn = new Scene(root);
		st.setScene(cn);
		st.show();
}
public void goeditdish(ActionEvent event) throws IOException{
	Main.dish = dish.getValue();
	 Parent root = FXMLLoader.load(getClass().getResource("editdish.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
	 Scene	 cn = new Scene(root);
		st.setScene(cn);
		st.show();
}
public void goeditorder(ActionEvent event) throws IOException{
	Main.order = order.getValue();
	 Parent root = FXMLLoader.load(getClass().getResource("editorder.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
	 Scene	 cn = new Scene(root);
		st.setScene(cn);
		st.show();
}
public void goeditarea(ActionEvent event) throws IOException{
	Main.devarea = devarea.getValue();
	 Parent root = FXMLLoader.load(getClass().getResource("editveliveryarea.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
	 Scene	 cn = new Scene(root);
		st.setScene(cn);
		st.show();
}





}
