package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CustomerController implements Initializable {
	
	@FXML
	Label welcome;
	// going to all screens that we need  - menu , edit details , new order
	
	   public void gotomenu(ActionEvent event) throws IOException{
			 Parent root = FXMLLoader.load(getClass().getResource("viewmenu.fxml"));
			 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
	   public void gotoedit(ActionEvent event) throws IOException{
			 Parent root = FXMLLoader.load(getClass().getResource("EditDetailsCustmoer.fxml"));
			 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
	   public void gotoorders(ActionEvent event) throws IOException{
			 Parent root = FXMLLoader.load(getClass().getResource("vieworderstocustomer.fxml"));
			 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
	   public void logout(ActionEvent event) throws IOException{
			 Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
	   
	   public void goquery(ActionEvent event) throws IOException{
			Parent	root = FXMLLoader.load(getClass().getResource("customerquery.fxml"));
				Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene	 cn = new Scene(root);
					st.setScene(cn);
					st.show();
			}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcome.setText("welcome  " + Main.cust.getFirstName()+ " to javares");
		
	}
	

}
