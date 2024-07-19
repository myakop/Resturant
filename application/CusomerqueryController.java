package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Cook;
import Model.Dish;
import Model.Restaurant;
import Utils.Expertise;
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

public class CusomerqueryController implements Initializable  {
	@FXML
	ListView<Object> list ;
	@FXML
	ComboBox<String> Expert1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { //  set the expertises in the combobox
		ObservableList<String> expert = FXCollections.observableArrayList("Italien", "Mediterranean", "American", "French", "Indian", "Asian");
		
		Expert1.setItems(expert);
		Expert1.setVisibleRowCount(4);
		
	}
	
	 public void getreleventdish (ActionEvent event) throws IOException{
    	 list.getItems().clear();
    	 list.getItems().addAll(Main.res.getReleventDishList(Main.cust)); // get relevent dish
    	 
     }
	 public void getCookByEx (ActionEvent event)throws IOException{ // get cooks by their expertise
			list.getItems().clear();
			list.getItems().addAll(Main.res.GetCooksByExpertise(Expertise.valueOf(Expert1.getValue())));
		}
	 public void getPopularComp (ActionEvent event)throws IOException{ // get popular compenents
			list.getItems().clear();
		    list.getItems().addAll(Main.res.getPopularComponents());
		}
	 public void Back(ActionEvent event) throws IOException{
			Parent	root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
				Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene	 cn = new Scene(root);
					st.setScene(cn);
					st.show();
			}
	 
	 

}
