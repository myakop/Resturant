package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Customer;
import Model.Dish;
import Model.Restaurant;
import javafx.collections.FXCollections;
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
import javafx.collections.ObservableList;

public class queryController implements Initializable {
	
	@FXML
	ComboBox<Customer> customer;
	@FXML
	ListView<Dish> list;
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		ArrayList<Customer> custs = new ArrayList<>(Main.res.getCustomers().values());
		ObservableList<Customer> cust2 = FXCollections.observableArrayList(custs);
		customer.setItems(cust2);
		customer.setVisibleRowCount(4);
		
	}
	public void getrelevntdishs (ActionEvent event)throws IOException{
		list.getItems().clear();
		list.getItems().addAll(Main.res.getReleventDishList(customer.getValue()));
	}
	public void gottoquery(ActionEvent event) throws IOException{
		Parent	root = FXMLLoader.load(getClass().getResource("query.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		
	
	
}

