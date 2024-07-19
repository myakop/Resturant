package application;

import java.io.IOException;

import Exceptions.compNameException;
import Model.Component;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class addcontroller  {
	Parent root;
	Stage st;
	Scene cn;
	
	@FXML
	BorderPane pane2;
	@FXML
	TextField  compname , price;
	
	
	@FXML
	CheckBox  hasgeloten, haslacoste;
	
	//go to add.fxml for all the elemnts - cook,dish,order ....
	public void getcookisbyexpert(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("getCookiesByExpertise.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void gotodeliverybyperson(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("getDeliveryByPerson.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void gotoblacklist(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("BlackList.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void backmanger(ActionEvent event) throws IOException{
		Parent	root = FXMLLoader.load(getClass().getResource("manger.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
	public void getdilver(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("getdilver.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void getnumberofdelivery(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("getNumberOfdelivery.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void getpopularcomp(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("getpopularcompenet.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void getprofitrelation(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("getProfitRelation.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void getrevenuefromexpress(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("getrevenuefromexpress.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	
	
	
	public void gotorelventdish(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("getReleventDish.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	
	public void goaddeliveryarea(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("Adddeliveryarea.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	public void goadddish(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("Adddish.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	public void goaddcomponanet(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("Addcomponanet.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}
	
	public void goaddcook (ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("addcook.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();

	}
	public void goaddorder (ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("Addorder.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();

	}
	public void goadddeliveryperson (ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("Adddeliveryperson.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();

	}
	public void goadddcustomer (ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("Addcustbymanger.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();

	}
	public void goadddelivery (ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("Adddelivery.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();

	}
	
	
	public void submitaddcomp (ActionEvent event) throws IOException {
		
		try {
			if(compname.getText()=="") {
				throw new NullPointerException();
				}
			for(Component comp : Main.res.getComponenets().values()) {
				if(comp.getComponentName().equals(compname.getText())) {
					throw new compNameException();
				}
			}
				
		
		int x =Integer.parseInt(price.getText());
		Component comp = new Component(compname.getText(),haslacoste.isSelected(),hasgeloten.isSelected(),x);
		checkcompid(comp);
		if(Main.res.addComponent(comp)) {
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

		
	
	

	public void checkcompid(Component comp) {
		int x=0;
		for(int c : Main.res.getComponenets().keySet()) {
			if(c>x)
				x=c;
		}
		comp.setId(x+1);
		return;
	}
	public void Back(ActionEvent event) throws IOException{
		root = FXMLLoader.load(getClass().getResource("Add.fxml"));
		 st = (Stage)((Node)event.getSource()).getScene().getWindow();
			 cn = new Scene(root);
			st.setScene(cn);
			st.show();
	}

	
	public static <T extends Node> T LoadFXML(Class<?> cls ,String path) throws IOException {
		return FXMLLoader.load(cls.getResource(path));
		
	}
}
