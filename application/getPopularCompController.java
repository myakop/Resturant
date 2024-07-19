package application;

import java.io.IOException;

import Model.Component;
import Model.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class getPopularCompController {
	@FXML
	ListView<Component> comp ;



public void getPopularComp (ActionEvent event)throws IOException{  // get puplar components
	comp.getItems().clear();
	comp.getItems().addAll(Main.res.getPopularComponents());
}
public void gottoquery(ActionEvent event) throws IOException{
	Parent	root = FXMLLoader.load(getClass().getResource("query.fxml"));
		Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	



}