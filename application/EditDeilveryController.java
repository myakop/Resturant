package application;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.ExpressDelivery;
import Model.Order;
import Model.RegularDelivery;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sorted.sortmachine;

public class EditDeilveryController implements Initializable{
	@FXML
	 ComboBox<DeliveryPerson> devperson;
	@FXML
	 ComboBox<DeliveryArea> devarea;
	@FXML
	DatePicker date;
	@FXML
	CheckBox isdeliverd;
	@FXML
	ListView<Order> list1 , list2;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		devarea.getItems().addAll(Main.res.getAreas().values());
		devperson.getItems().addAll(Main.res.getDeliveryPersons().values());
		for(Order ord :Main.res.getOrders().values()) {
			if(ord.getDelivery()==null) {
				list1.getItems().add(ord);
			}
		}
		devarea.setValue(Main.dev.getArea()); // fill the elements in the details of that delivery that we need to change 
		devperson.setValue(Main.dev.getDeliveryPerson());
		if(Main.dev instanceof RegularDelivery) {
		list2.getItems().addAll(((RegularDelivery)Main.dev).getOrdertree());
		}
		if(Main.dev instanceof ExpressDelivery) {
			list2.getItems().add(((ExpressDelivery)Main.dev).getExorder());
		}
	
		
	}
	
	
	public void addact(ActionEvent event) throws IOException {
		try {
		int Select = list1.getSelectionModel().getSelectedIndex();
		list2.getItems().add(list1.getItems().get(Select));
		list1.getItems().remove(list1.getItems().get(Select));
		}catch(IndexOutOfBoundsException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("no selected thing");
			aler.showAndWait();
		}
	}                                                           // edit the orders to this delivery 
	 public void remove(MouseEvent Action) {
		 try {
			int Select = list2.getSelectionModel().getSelectedIndex();
			list1.getItems().add(list2.getItems().get(Select));
			list2.getItems().remove(Select);
			}catch(IndexOutOfBoundsException e) {
				Alert aler = new Alert(AlertType.ERROR);
				aler.setHeaderText("no selected thing");
				aler.showAndWait();
			}
		}
	  public void submitadddelivery(ActionEvent event) throws IOException{ // create a new relevant delivers to this orders
		  try {
				if(list2.getItems().isEmpty()) {
					throw new NullPointerException();
				}
		  TreeSet<Delivery> tr = new  TreeSet<Delivery>( new sortmachine());
		  TreeSet<Order> tr2 = new  TreeSet<Order>();
		
	 
		  if(list2.getItems().size() < 3) { // if we have less than 3 orders -> create a express delivery for every one
			  for(Order ord :list2.getItems()) {
				  Delivery dev1 = new ExpressDelivery(devperson.getValue(),devarea.getValue()  ,isdeliverd.isSelected(),ord,date.getValue());
				  tr.add(dev1);			 
			  }
		  }
		  else{
			  for(Order lorder : list2.getItems()) { // make express delivery for every customer with sensitive and one  regular delivery for the other 
			  if(lorder.getCustomer().isSensitiveToGluten() || lorder.getCustomer().isSensitiveToLactose()) {
				  Delivery dev = new ExpressDelivery(devperson.getValue(),devarea.getValue(),isdeliverd.isSelected(),lorder,date.getValue());
				  tr.add(dev);   
			  }
			  else {
				  tr2.add(lorder);
			  }  
		}
		 if(tr2.size() <2) {
			 Delivery dev = new ExpressDelivery(devperson.getValue(),devarea.getValue()  ,isdeliverd.isSelected(),tr2.first(),date.getValue());
				  tr.add(dev);
			  }
		  if(tr2!= null) {
		  Delivery d = new RegularDelivery(tr2,devperson.getValue(),devarea.getValue(),isdeliverd.isSelected(),date.getValue());
		  tr.add(d);
		  
		  }
		  }
		  
		  int i = Main.dev.getId();
		  Main.res.removeDelivery(Main.dev); // add all the delivers that we create
		  if(tr.size()==1) {
			  tr.first().setId(i);
			  if(Main.res.addDelivery(tr.first()))
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
		  else{
			  Iterator<Delivery> iter = tr.iterator();
			  while(iter.hasNext()) {
				  
				  Delivery d = iter.next();
					checkid(d);
					if(Main.res.addDelivery(d)){
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
		  }
		   
	 }
		  }catch(NullPointerException e) {
			Alert aler = new Alert(AlertType.ERROR);
			aler.setHeaderText("one of the feilds is null");
			aler.showAndWait();
		}
	  }
	  public void checkid(Delivery dev) {
			int x=0;
			for (int c : Main.res.getDeliveries().keySet()) {
				if (c>x)
					x=c;
			}
			dev.setId(x+1);
			return;
		}
		public void Back(ActionEvent event) throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		  
		  
	  }















	


