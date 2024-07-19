package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import Exceptions.SameNameException;
import Model.DeliveryArea;
import Utils.Neighberhood;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddDeliveryAreaController  implements Initializable{
	Parent root;
	Stage st;
	Scene cn;	
	@FXML
	TextField areaname ;
	@FXML
	TextField time;
	
	@FXML
	ListView<String>   neigh2;
	@FXML
	ListView<String> neigh1;

	@Override
	public void initialize(URL location, ResourceBundle resources) { // set all the neighberhoods in the list
		 ObservableList<String> list2 = FXCollections.observableArrayList("Neve_Shanan", "Kiriat_Haim", "DownTown", "Vardia", "Bat_Galim", "Merkaz_Karmel"," Denya", "Kiriat_Eliezer",
					"Hadar", "Romema", "German_Colony", "Vadi_Nisnas", "Vadi_Saliv", "Neot_Peres", "Kababir"," Neve_David",
					"Karmelia", "Halisa"," French_Karmel", "Ramat_Hanasi", "Neve_Yosef", "Ramat_Almogi");
	    neigh1.getItems().addAll(list2);
	}
	
	public void addact(MouseEvent Action) {
		try {
		int Select = neigh1.getSelectionModel().getSelectedIndex();
		neigh2.getItems().add(neigh1.getItems().get(Select));
		}catch(IndexOutOfBoundsException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("no selected thing");
			aler.showAndWait();
		}                                           // choosing the neigh to add deliveryArea
	}
	 public void remove(MouseEvent Action) {
		 try {
			int Select = neigh2.getSelectionModel().getSelectedIndex();
			neigh1.getItems().add(neigh2.getItems().get(Select));
			neigh2.getItems().remove(Select);
			}catch(IndexOutOfBoundsException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("no selected thing");
				aler.showAndWait();
			}
		}
	
	public void SubmittAddDev (ActionEvent event) throws IOException{
		try {
			for(DeliveryArea area : Main.res.getAreas().values()) {
				if(area.getAreaName().equals(areaname.getText())) { // check that the name is not exists
					throw new SameNameException();
				}
			}
			if(areaname.getText().equals("")  ||  neigh2.getItems().isEmpty() ) {
				throw new NullPointerException(); // check that nothing null
			}
		
		
		int time2 = Integer.parseInt(time.getText());
		HashSet<Neighberhood> hash = new HashSet<>();
		ArrayList<String> areas = new ArrayList<>();
		areas.addAll(neigh2.getItems());
		for (String s : areas) {
			Neighberhood d = Neighberhood.valueOf(s);
			hash.add(d);
		}
		DeliveryArea dev = new DeliveryArea(areaname.getText(),hash,time2);
		checkdareaid(dev);
		if(Main.res.addDeliveryArea(dev)) { // add new delivery area
			Main.update();
			Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully add");
			aler.showAndWait();	
			Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
	}
		else {
			Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("feild add");
			aler.showAndWait();
		}
		}catch(SameNameException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("this name is already exist");
			aler.showAndWait();
		}catch(NumberFormatException e){
       	 Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("you inter a ilegal text ");
			aler.showAndWait();
		}catch(NullPointerException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("there is feild null ");
			aler.showAndWait();
		}
	}
	
	public void checkdareaid(DeliveryArea dev) { // check the last id
		int x=0;
		for (int c : Main.res.getAreas().keySet()) {
			if (c>x)
				x=c;
		}
		dev.setId(x+1);
		return;
	}
	public void Back(ActionEvent event) throws IOException{  // back to add screen
		root = FXMLLoader.load(getClass().getResource("Add.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
}

