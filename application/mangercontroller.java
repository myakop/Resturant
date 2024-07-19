package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mangercontroller {
	Parent root;
	Stage st;
	Scene cn;
	
	@FXML
	BorderPane pane2;
	
	// going to the screens that we need - query , logout, addd,remove, edit
	public void gotoquey(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("query.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void logout(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("login.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	public void goremove(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("remove.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void godata(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("viewdata.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	
	public void addbymanger (ActionEvent event) throws IOException {
		Pane root = LoadFXML(getClass(),"Add.fxml");
		pane2.setCenter(root);

	}
	public void goedit(ActionEvent event) throws IOException{
		 Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
		 Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
		 Scene	 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	

	
	
	
	
	public static <T extends Node> T LoadFXML(Class<?> cls ,String path) throws IOException {
		return FXMLLoader.load(cls.getResource(path));
		
	}

}
