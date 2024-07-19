package application;




	import java.io.IOException;
	import java.net.URL;

	import java.util.ResourceBundle;

	import Model.Customer;
	import Model.Restaurant;
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
	import javafx.scene.layout.AnchorPane;
	import javafx.scene.layout.BorderPane;
	import javafx.stage.Stage;
	
	public class Addcustbymangerconroller implements Initializable {

	
		Parent root;
		Stage st;
		Scene cn;
		AnchorPane pn;
		@FXML
		BorderPane pane2;
		@FXML
		Label labe1 , labe2 ,labe3 ,labe4 , doubleuser;
		
		@FXML
		TextField text1 ;
		@FXML
		PasswordField text2 , password;
		@FXML
		TextField firstname , lastname , username;
		@FXML
		DatePicker birthday;
		@FXML
		ComboBox<String> gend   ;
		@FXML
		ComboBox<String> neigh;
		@FXML
		CheckBox sensg,sensl;
		
		  
		
		@Override
		
		public void initialize(URL location, ResourceBundle resources) {
			 ObservableList<String> list = FXCollections.observableArrayList("Male","Female","Unkown"); //set the genders
			    gend.setItems(list); 
			 ObservableList<String> list2 = FXCollections.observableArrayList("Neve_Shanan", "Kiriat_Haim", "DownTown", "Vardia", "Bat_Galim", "Merkaz_Karmel"," Denya", "Kiriat_Eliezer",
						"Hadar", "Romema", "German_Colony", "Vadi_Nisnas", "Vadi_Saliv", "Neot_Peres", "Kababir"," Neve_David",
						"Karmelia", "Halisa"," French_Karmel", "Ramat_Hanasi", "Neve_Yosef", "Ramat_Almogi");
			    neigh.setItems(list2);   
			
		}
		public void submit (ActionEvent event)throws IOException{
			try {
				
				if(firstname.getText().equals("") || lastname.getText().equals("") || birthday.getValue().equals(null) || username.getText().equals("") || password.getText().equals("")  ) {
					throw new NullPointerException(); // check that nothing null
				}
			
			
			boolean check=true;
			for(Customer c : Main.res.getCustomers().values()) {
				if(c.getUsername().equals(username.getText())) {
				doubleuser.setText("username is Already exists , plaese inter another username ");
				check=false; // check if the username is not exists
			}
			}
			Customer c = new Customer(firstname.getText(),lastname.getText(),birthday.getValue(),Gender.valueOf(gend.getValue()),Neighberhood.valueOf(neigh.getValue()),sensg.isSelected(),sensl.isSelected(),username.getText(),password.getText());
			checkid(c);
			if(check) {
			if(Main.res.addCustomer(c)) { // add new customer
				Main.update();
				 Alert aler = new Alert(AlertType.CONFIRMATION);
					aler.setHeaderText("seccessfully sign up");
					aler.showAndWait();	
				 root = FXMLLoader.load(getClass().getResource("add.fxml"));
				 st = (Stage)((Node)event.getSource()).getScene().getWindow();
					 cn = new Scene(root);
					st.setScene(cn);
					st.show();
					
			}
			
			}
			}catch(NullPointerException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("one of the feild is null");
				aler.showAndWait();
			}
		}
		
		public void checkid (Customer c) { // check the last id
			int x=0;
			for(int max : Main.res.getCustomers().keySet()) {
			if(max>x)
				x=max;
				
			}
			c.setId(x+1);
			return ;
		}
		public void back(ActionEvent event) throws IOException { // back to add screen
			 root = FXMLLoader.load(getClass().getResource("add.fxml"));
			 st = (Stage)((Node)event.getSource()).getScene().getWindow();
				 cn = new Scene(root);
				st.setScene(cn);
				st.show();

		}

	}


