package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Component;

import Model.Dish;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class newOrderController implements Initializable {
	@FXML
	ListView<Dish> list ;
	@FXML
	ListView<Component> list2;
	@FXML
	ComboBox<Component> comps;
	@FXML
	Label leb;
	
	int x=0;
	
	@Override
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		list.getItems().addAll(Main.res.getDishes().values()); // fill the list in all the dishes
		ArrayList<Component> delvery = new ArrayList<>(Main.res.getComponenets().values());
		ObservableList<Component> cust2 = FXCollections.observableArrayList(delvery);
		comps.setItems(cust2);
		comps.setVisibleRowCount(4);
			
	          leb.setText("you have "+ x +" dishes in your cart ");
	}
	

     public void Addwithoutchange (ActionEvent event) throws IOException{ //add dish to the cart without change
    	 try {
    	 int Select = list.getSelectionModel().getSelectedIndex();
 		Main.saveorder.add(list.getItems().get(Select));
 		Main.update();
 		Alert aler = new Alert(AlertType.CONFIRMATION);
 		aler.setHeaderText("succesfully add ");
 		aler.showAndWait();
 		x++;
 		leb.setText("you have "+ x +" dishes in your cart ");
 		
 		
}catch(IndexOutOfBoundsException e){
	Alert aler = new Alert(AlertType.WARNING);
	aler.setHeaderText("nothing choose ");
	aler.showAndWait();
}
     }
     public void edit (ActionEvent event) throws IOException{ // edit components for some dish
    	 try {
    		 list2.getItems().clear();
    	 int Select = list.getSelectionModel().getSelectedIndex();
    	 list2.getItems().addAll(list.getItems().get(Select).getComponenets());
     }
    	 catch(IndexOutOfBoundsException e){
 			Main.update();Alert aler = new Alert(AlertType.WARNING);
 			aler.setHeaderText("nothing choose ");
 			aler.showAndWait();
 		}
     }
     public void addcomp (ActionEvent event) throws IOException{
    	 
     list2.getItems().add(comps.getValue());
     }
     
     public void Addnewdish (ActionEvent event) throws IOException{
    	 try {
    	 int Select = list.getSelectionModel().getSelectedIndex();
    	 ArrayList<Component> componenets = new ArrayList<Component>();
    	 componenets.addAll(list2.getItems());
    	 Dish dish = new Dish(list.getItems().get(Select).getDishName(),list.getItems().get(Select).getType(), componenets, list.getItems().get(Select).getTimeToMake());
    	 checkdishid(dish);
    	 Main.saveorder.add(dish);
    	 Main.update();
    	 Alert aler = new Alert(AlertType.CONFIRMATION);  // create new dish with the comp that the user update
  		aler.setHeaderText("succesfully add ");
  		aler.showAndWait();
  		x++;
 		leb.setText("you have "+ x +" dishes in your cart ");
}catch(IndexOutOfBoundsException e){
	Main.update();Alert aler = new Alert(AlertType.WARNING);
	aler.setHeaderText("nothing choose");
	aler.showAndWait();
}
     }
     
     public void getreleventdish (ActionEvent event) throws IOException{ // update the list with relevant dishes
    	 list.getItems().clear();
    	 list.getItems().addAll(Main.res.getReleventDishList(Main.cust));
    	 
     }
     public void remove (ActionEvent event) throws IOException{
    	 try {
    	 int Select = list2.getSelectionModel().getSelectedIndex();
    	 list2.getItems().remove(list2.getItems().get(Select));
     }catch(IndexOutOfBoundsException e){
			Main.update();Alert aler = new Alert(AlertType.WARNING);
			aler.setHeaderText("nothing choose ");
			aler.showAndWait();
		}
     }
     public void submit (ActionEvent event) throws IOException{ // go to the cart
    	 Parent root = FXMLLoader.load(getClass().getResource("payandaddorder.fxml"));
		 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene cn = new Scene(root); 
			st.setScene(cn);
			st.show();
    	 
     }
     public void checkdishid(Dish dish) {
 		int x=0;
 		for (int c : Main.res.getDishes().keySet()) {
 			if (c>x)
 				x=c;
 		}
 		dish.setId(x+1);
 		return;
 	}
     
     
     
     
}

