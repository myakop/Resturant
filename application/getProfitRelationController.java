package application;

import java.io.IOException;


import Model.Dish;
import Model.Restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class getProfitRelationController {
	@FXML
	ListView<Dish> list;
	
	
	public void getprofitrelation (ActionEvent event)throws IOException{ // get profit relation
		list.getItems().clear();
		list.getItems().addAll(Main.res.getProfitRelation());
	}
	public void gottoquery(ActionEvent event) throws IOException{
		Parent	root = FXMLLoader.load(getClass().getResource("query.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		
	

}
