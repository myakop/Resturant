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

public class EditDeevPersonController implements Initializable {
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
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Unkown");
	    gender.setItems(list);
	    ObservableList<String> list2 = FXCollections.observableArrayList("Car","Motorcycle", "Bicycle");
	   vehicle.setItems(list2);
	   area.getItems().addAll(Main.res.getAreas().values());
	   firstname.setText(Main.devperson.getFirstName());
	   lastname.setText(Main.devperson.getLastName());
	   date.setValue(Main.devperson.getBirthDay());
	   vehicle.setValue(Main.devperson.getVehicle().toString()); // fill the elements in the the devperson details that we need to edit
	   gender.setValue(Main.devperson.getGender().toString());
	   area.setValue(Main.devperson.getArea());
		
	}
	public void Submit (ActionEvent event) throws IOException{
		
		try {
			
			for(DeliveryPerson devp : Main.res.getDeliveryPersons().values()) {
				if(devp.getFirstName().equals(firstname.getText()) && devp.getLastName().equals(lastname.getText()) && devp.getBirthDay().equals(date.getValue()) && devp.getGender().toString().equals(gender.getValue()) && devp.getVehicle().toString().equals(vehicle.getValue()) && devp.getArea().equals(area.getValue())) {
					throw new SameNameException(); // check that devperson not exist
				}
			if(firstname.getText().equals("") || lastname.getText().equals("") || date.getValue().equals(null) ) {
				throw new NullPointerException(); // check that nothing null
			}
		Main.devperson.setFirstName(firstname.getText());
		Main.devperson.setLastName(lastname.getText());
		Main.devperson.setBirthDay(date.getValue());
		Main.devperson.setVehicle(Vehicle.valueOf(vehicle.getValue())); // set all the new details 
		Main.devperson.setGender(Gender.valueOf(gender.getValue()));
		Main.devperson.setArea(area.getValue());
		Main.update();
		Alert aler = new Alert(AlertType.CONFIRMATION);
		aler.setHeaderText("seccessfully edit");
		aler.showAndWait();	
		Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
		Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
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
	public void Back(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
		Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}

}
