package application;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import Exceptions.SameNameException;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Utils.Gender;
import Utils.Vehicle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddDeliveryPersonController implements Initializable  {
	Parent root;
	Stage st;
	Scene cn;
	@FXML
	TextField firstname;
	@FXML
	TextField lastname;
	@FXML
	DatePicker date;
	@FXML
	ComboBox<String> vehicle;
	@FXML
	ComboBox<String> gender;
	@FXML
	ComboBox<DeliveryArea> area;
	@Override
	public void initialize(URL location, ResourceBundle resources) { // set the genders and the vehicle to the comboBox
		ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Unkown");
	    gender.setItems(list);
	    ObservableList<String> list2 = FXCollections.observableArrayList("Car","Motorcycle", "Bicycle");
	   vehicle.setItems(list2);
	   area.getItems().addAll(Main.res.getAreas().values());
	   
		
	}
	public void SubmittAdddev (ActionEvent event) throws IOException{
		try {
			
		for(DeliveryPerson devp : Main.res.getDeliveryPersons().values()) { // check that dev person is not exist
			if(devp.getFirstName().equals(firstname.getText()) && devp.getLastName().equals(lastname.getText()) && devp.getBirthDay().equals(date.getValue()) && devp.getGender().toString().equals(gender.getValue()) && devp.getVehicle().toString().equals(vehicle.getValue()) && devp.getArea().equals(area.getValue())) {
				throw new SameNameException();
			}
		if(firstname.getText().equals("") || lastname.getText().equals("") || date.getValue().equals(null) ) {
			throw new NullPointerException(); // check that nothing null
		}
			
		}
		DeliveryPerson dev = new DeliveryPerson(firstname.getText(),lastname.getText(),date.getValue(),Gender.valueOf(gender.getValue()),Vehicle.valueOf(vehicle.getValue()),area.getValue());
		checkdevid(dev);
		if(Main.res.addDeliveryPerson(dev,area.getValue())) { // add delivery person
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
			aler.setHeaderText("this delivery person is already exist");
			aler.showAndWait();
			
		}catch(NullPointerException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("one of the feild is null");
			aler.showAndWait();
		}
	
		}
	public void checkdevid(DeliveryPerson dev) {
		int x=0;
		for (int c : Main.res.getDeliveryPersons().keySet()) {
			if (c>x)
				x=c;
		}
		dev.setId(x+1);
		return;
	}
	public void Back(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("Add.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	
	

}
