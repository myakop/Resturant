package application;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import Model.Cook;

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

public class getCooksExpertiseController implements Initializable {
	@FXML
	ComboBox<String> Expert1;
	@FXML
	ListView<Cook> list;
	@Override
	public void initialize(URL location, ResourceBundle resources) { // fill the combobox in the expertise
		ObservableList<String> expert = FXCollections.observableArrayList("Italien", "Mediterranean", "American", "French", "Indian", "Asian");
		
		Expert1.setItems(expert);
		Expert1.setVisibleRowCount(4);
		
	}
	public void getCookByEx (ActionEvent event)throws IOException{ // get cooks by thier expertise
		list.getItems().clear();
		list.getItems().addAll(Main.res.GetCooksByExpertise(Expertise.valueOf(Expert1.getValue())));
	}
	public void gottoquery(ActionEvent event) throws IOException{ // back to the query screen
	Parent	root = FXMLLoader.load(getClass().getResource("query.fxml"));
		Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	
	

}
