package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Customer;
import Utils.Gender;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditCustomerController implements Initializable {
	@FXML
	TextField firstname , lastname , username;
	@FXML
	ComboBox<String> gend   ;
	@FXML
	ComboBox<String> neigh;
	@FXML
	CheckBox sensg,sensl;
	@FXML
	DatePicker birthday;
	@FXML
	PasswordField  password;
	@FXML
	Label  doubleuser;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Unkown");
		    gend.setItems(list);
		 ObservableList<String> list2 = FXCollections.observableArrayList("Neve_Shanan", "Kiriat_Haim", "DownTown", "Vardia", "Bat_Galim", "Merkaz_Karmel"," Denya", "Kiriat_Eliezer",
					"Hadar", "Romema", "German_Colony", "Vadi_Nisnas", "Vadi_Saliv", "Neot_Peres", "Kababir"," Neve_David",
					"Karmelia", "Halisa"," French_Karmel", "Ramat_Hanasi", "Neve_Yosef", "Ramat_Almogi");
		    neigh.setItems(list2); 
		    
		firstname.setText(Main.cust.getFirstName());   // fill all the elements in the customers details that we need to edit
		lastname.setText(Main.cust.getLastName());
		username.setText(Main.cust.getUsername());
		gend.setValue(Main.cust.getGender().toString());
		neigh.setValue(Main.cust.getNeighberhood().toString());
		birthday.setValue(Main.cust.getBirthDay());
		password.setText(Main.cust.getPassword());
		sensg.setSelected(Main.cust.isSensitiveToGluten());
		sensl.setSelected(Main.cust.isSensitiveToLactose());
		
	
	}
	
	public void submitedit (ActionEvent event)throws IOException{
try {
			
			if(firstname.getText().equals("") || lastname.getText().equals("") || birthday.getValue().equals(null) || username.getText().equals("") || password.getText().equals("")  ) {
				throw new NullPointerException(); // check that nothing null
			}
	
	boolean checkusername=true;
	for(Customer c : Main.res.getCustomers().values()) {
		if(c.getUsername().equals(username.getText())  && !Main.cust.getUsername().equals(username.getText())){
			checkusername=false; // check that user name not exists
			doubleuser.setText("username is Already exists , plaese inter another username ");
		}	
	}
		if(checkusername) {
			Main.cust.setFirstName(firstname.getText());
			Main.cust.setLastName(lastname.getText());
			Main.cust.setUsername(username.getText());
			Main.cust.setPassword(password.getText());
			Main.cust.setGender(Gender.valueOf(gend.getValue())); //  set the new details that we edit
			Main.cust.setNeighberhood(Neighberhood.valueOf(neigh.getValue()));
			Main.cust.setSensitiveToGluten(sensg.isSelected());
			Main.cust.setSensitiveToLactose(sensl.isSelected());
			Main.cust.setBirthDay(birthday.getValue());
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
         }catch(NullPointerException e) {
	       Alert aler = new Alert(AlertType.ERROR);
	       aler.setHeaderText("one of the feild is null");
	       aler.showAndWait();
}
	}
	public void backcust(ActionEvent event) throws IOException{
		 Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
		 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	

}
