package application;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Model.Customer;


import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class logincontroller implements Initializable  {
	Parent root;
	Stage st;
	Scene cn;
	AnchorPane pn;
	@FXML
	BorderPane pane2;
	@FXML
	MediaView mediaView;
	
	
	@FXML
	TextField text1 ;
	@FXML
	PasswordField text2 ;
	
	
	
	
	
	HashMap<String,String> hash = new HashMap<>();
	
	
	
	public void login(ActionEvent event) throws IOException {
		
		
		if(text1.getText().equals("manager") && text2.getText().equals("manager"))
		{
		 root = FXMLLoader.load(getClass().getResource("manger.fxml"));
	 st = (Stage)((Node)event.getSource()).getScene().getWindow();
		 cn = new Scene(root);
		st.setScene(cn);
		st.show();
		}
		
		else {
			for(Customer cust : Main.res.getCustomers().values()) { //check that password and username is true if true loging
				if(cust.getUsername().equals(text1.getText()) && cust.getPassword().equals(text2.getText())) {
					Main.cust=cust;
					 root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
					 st = (Stage)((Node)event.getSource()).getScene().getWindow();
						 cn = new Scene(root);
						st.setScene(cn);
						st.show();
				}
			}
		}
	
		
	}
	public void signup(ActionEvent event) throws IOException {
		
		 root = FXMLLoader.load(getClass().getResource("signup.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
		 cn = new Scene(root);
		st.setScene(cn);
		st.show();
	}
	

	
	public static <T extends Node> T LoadFXML(Class<?> cls ,String path) throws IOException {
		return FXMLLoader.load(cls.getResource(path));
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Media media = new Media(getClass().getResource("/images/java.mp4").toString());
		MediaPlayer player = new MediaPlayer(media);
		mediaView.setMediaPlayer(player);
		player.setVolume(0);
		player.play();
				
		
	}
	

}
	
	
         
	


