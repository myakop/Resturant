package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Exceptions.compNameException;
import Model.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EidtComponentController implements Initializable {
	@FXML
	TextField  compname , price;
	@FXML
	CheckBox  hasgeloten, haslacoste;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		compname.setText(Main.comp.getComponentName());
		price.setText(Double.toString(Main.comp.getPrice()));
		hasgeloten.setSelected(Main.comp.isHasGluten());
		haslacoste.setSelected(Main.comp.isHasLactose()); //fill the elements in the comp that we need to edit 
	
	}
	 public void submit (ActionEvent event) throws IOException{
		 try {
				if(compname.getText()=="") {
					throw new NullPointerException(); //check that nothing null
					}
				for(Component comp : Main.res.getComponenets().values()) {
					if(comp.getComponentName().equals(compname.getText())) {
						if(!Main.comp.getComponentName().equals(compname.getText()))
						throw new compNameException();
					}
				}
		Main.comp.setComponentName(compname.getText());
		double x = Double.parseDouble(price.getText());
		Main.comp.setPrice(x);
		Main.comp.setHasGluten(hasgeloten.isSelected());
		Main.comp.setHasLactose(haslacoste.isSelected());  //update this comp with the new details
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
				
			}
	         catch(NumberFormatException e){
	        	 Alert aler = new Alert(AlertType.ERROR);
	 			aler.setHeaderText("you inter a ilegal text ");
	 			aler.showAndWait();
				
			}
	         catch(compNameException e){
	        	 Alert aler = new Alert(AlertType.ERROR);
	  			aler.setHeaderText("this name comp is exsits ");
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
