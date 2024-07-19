package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Component;
import Model.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewMenuController implements Initializable {

	
	@FXML
	ListView<Dish> dishes;
	@FXML
	ListView<Component> components;
	
	


  public void viewcomp(MouseEvent Action) { // show the compenents for the dish we choose
	  try {
	  components.getItems().clear();
	int Select = dishes.getSelectionModel().getSelectedIndex();
	components.getItems().addAll(dishes.getItems().get(Select).getComponenets());
  }catch(IndexOutOfBoundsException e) {
		Alert aler = new Alert(AlertType.ERROR);
		aler.setHeaderText("no selected thing");
		aler.showAndWait();
	}
}

    public void initialize(URL location, ResourceBundle resources) {
	dishes.getItems().addAll(Main.res.getDishes().values()); // show all the dishes in the restaurant
}
    public void backcust(ActionEvent event) throws IOException{
	 Parent root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
	 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene cn = new Scene(root);
		st.setScene(cn);
		st.show();
}

}
