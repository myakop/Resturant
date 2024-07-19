package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Exceptions.SameCookExcpetion;
import Model.Cook;
import Utils.Expertise;
import Utils.Gender;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditCookController implements Initializable {
	Parent root;
	Stage st;
	Scene cn;
	
	@FXML
	TextField cfirstname , clastname;
	@FXML
	DatePicker cbirthday;
	@FXML
	ComboBox<String> cgender;
	@FXML
	 ComboBox<String>cexpert;
	@FXML
	CheckBox ischef;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Unkown");
	    cgender.setItems(list);
	 ObservableList<String> list2 = FXCollections.observableArrayList("Italien", "Mediterranean", "American", "French", "Indian", "Asian");
	 cexpert.setItems(list2);
		
		
		cfirstname.setText(Main.cook.getFirstName());  //fill all the elements in the details that we need to change
		clastname.setText(Main.cook.getLastName());
		cbirthday.setValue(Main.cook.getBirthDay());
		cgender.setValue(Main.cook.getGender().toString());
		cexpert.setValue(Main.cook.getExpert().toString());
		ischef.setSelected(Main.cook.isChef());
	}
	public void submit(ActionEvent event) throws IOException {
		try {
		for(Cook cook : Main.res.getCooks().values()) {
			if(cook.getFirstName().equals(cfirstname.getText()) && cook.getLastName().equals(clastname.getText()) && cook.getExpert().toString().equals(cexpert.getValue()) && cook.getGender().toString().equals(cgender.getValue()) && cook.getBirthDay().equals(cbirthday.getValue()) && (cook.isChef()==ischef.isSelected()))  {
				throw new SameCookExcpetion(); // check that the cook not exist
			}
		}
		if(cfirstname.getText().equals("") || clastname.getText().equals("") || cbirthday.getValue().equals(null)) {
			throw new NullPointerException(); // check that nothing null
		}
		Main.cook.setFirstName(cfirstname.getText());
		Main.cook.setLastName(clastname.getText());
		Main.cook.setBirthDay(cbirthday.getValue());              // set the new details
		Main.cook.setGender(Gender.valueOf(cgender.getValue()));
		Main.cook.setExpert(Expertise.valueOf(cexpert.getValue()));
		Main.cook.setChef(ischef.isSelected());
		Main.update();
		
		Alert aler = new Alert(AlertType.CONFIRMATION);
		aler.setHeaderText("seccessfully edit");
		aler.showAndWait();	
		Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
		Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
		}catch(NullPointerException e){
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("one of the feilds are null plaese check ");
			aler.showAndWait();
		}catch(SameCookExcpetion e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("this cook is exists ");
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
