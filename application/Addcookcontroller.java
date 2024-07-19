package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Exceptions.SameCookExcpetion;
import Model.Cook;
import Utils.Expertise;
import Utils.Gender;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Addcookcontroller implements Initializable {
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
		ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Unkown"); // set combobox in the genders
		    cgender.setItems(list);
		 ObservableList<String> list2 = FXCollections.observableArrayList("Italien", "Mediterranean", "American", "French", "Indian", "Asian"); // set combobox on the expertise
		 cexpert.setItems(list2);
	}
	public void submitaddcook (ActionEvent event) throws IOException {
		try {
			
			for(Cook cook : Main.res.getCooks().values()) { // check if cook is already exist
				if(cook.getFirstName().equals(cfirstname.getText()) && cook.getLastName().equals(clastname.getText()) && cook.getExpert().toString().equals(cexpert.getValue()) && cook.getGender().toString().equals(cgender.getValue()) && cook.getBirthDay().equals(cbirthday.getValue()) && (cook.isChef()==ischef.isSelected()))  {
					throw new SameCookExcpetion();
				}
			}
			if(cfirstname.getText().equals("") || clastname.getText().equals("") || cbirthday.getValue().equals(null)) { // check that nothing null
				throw new NullPointerException(); 
			}
				
			
		Cook cook = new Cook(cfirstname.getText(),clastname.getText(),cbirthday.getValue(),Gender.valueOf(cgender.getValue()), Expertise.valueOf(cexpert.getValue()),ischef.isSelected());
		checkcookid(cook);
		if(Main.res.addCook(cook)) { // add new cook 
			Main.update();
			Alert aler = new Alert(AlertType.CONFIRMATION);
			aler.setHeaderText("seccessfully add");
			aler.showAndWait();	
			root = FXMLLoader.load(getClass().getResource("Add.fxml"));
			 st = (Stage)((Node)event.getSource()).getScene().getWindow();
				 cn = new Scene(root);
				st.setScene(cn);
				st.show();
	}
		else {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("feild add");
			aler.showAndWait();
		}
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
	public void checkcookid(Cook cook) { //check the last id
		int x=0;
		for (int c : Main.res.getCooks().keySet()) {
			if (c>x)
				x=c;
		}
		cook.setId(x+1);
		return;
	}
	public void Back(ActionEvent event) throws IOException{ //back to add screen
		root = FXMLLoader.load(getClass().getResource("Add.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	
	

}
