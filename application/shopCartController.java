package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import Model.Dish;
import Model.Order;
import Model.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class shopCartController  implements Initializable {
	@FXML
	ListView<Dish> list ;
	@FXML
	Label leb;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		list.getItems().addAll(Main.saveorder);
		double price=0;
		for(int i=0; i<list.getItems().size();i++) {
			price = price + list.getItems().get(i).calcDishPrice();
		}
		leb.setText("the price for your order is "+ Double.toString(price)); // the price for the order
		
		
		
	}
	 public void remove (ActionEvent event) throws IOException{ // remove dish from the list
		 try {
    	 int Select = list.getSelectionModel().getSelectedIndex();
    	 Main.saveorder.remove(list.getItems().get(Select));
    	 list.getItems().remove(list.getItems().get(Select));
    	 
    	 double price=0;
    	 for(int i=0; i<list.getItems().size();i++) {
    		
 			price = price + list.getItems().get(i).calcDishPrice();
 		}
    	 leb.setText("the price for your order is "+ Double.toString(price));
    	 
     
	 }catch(IndexOutOfBoundsException e) {
	 
	 }
	 }
	
	public void submit(ActionEvent event) throws IOException{
		ArrayList<Dish> dishes = new ArrayList<>();
		dishes.addAll(list.getItems());
		Order ord = new Order(Main.cust,dishes,null); //submit add the order 
		checkdishid(ord);
		Main.res.addOrder(ord);
		Main.saveorder.clear();
	}
	  public void Back (ActionEvent event) throws IOException{
	    	 Parent root = FXMLLoader.load(getClass().getResource("dishestoneworder.fxml"));
			 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene cn = new Scene(root);
				st.setScene(cn);
				st.show();
	    	 
	     }
	  public void checkdishid(Order ord) {
			int x=0;
			for (int c : Restaurant.getInstance().getDishes().keySet()) {
				if (c>x)
					x=c;
			}
			ord.setId(x+1);
			return;
		}
	  
	  
	  
	
	
	
	
	
	
	
	
	

}
