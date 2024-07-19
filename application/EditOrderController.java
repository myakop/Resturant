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

public class EditOrderController implements Initializable  {

	@FXML
	ComboBox<Customer> customer;
	@FXML
	ListView<Dish> dish1;
	@FXML
	ListView<Dish> dish2;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		customer.getItems().addAll(Main.res.getCustomers().values());
		customer.setValue(Main.order.getCustomer());
		dish1.getItems().addAll(Main.res.getDishes().values());
		dish2.getItems().addAll(Main.order.getDishes());
		
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
	}
	public void remove(MouseEvent Action) {
		 try {
			int Select = dish2.getSelectionModel().getSelectedIndex();
			
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
			throw new NullPointerException();
		}
	
		int i = Main.order.getId();
		Main.res.removeOrder(Main.order);
		ArrayList<Dish> dishes = new ArrayList<>();
		dishes.addAll(dish2.getItems());
		Order ord = new Order(customer.getValue(),dishes,null);
		ord.setId(i);
		if(Main.res.addOrder(ord)) {
			Main.update();
			Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully edit");
			aler.showAndWait();	
			Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		else {
			Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("feild edit");
			aler.showAndWait();	
			
		}
	}catch(NullPointerException e){
		Alert aler = new Alert(AlertType.ERROR);
		aler.setHeaderText("one of the feilds is null");
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
