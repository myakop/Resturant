package application;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class getNumberofDeliveryController {
	@FXML
	Label s ;
	@FXML
	Label d;
	
	public void getNumberofdelivery (ActionEvent event)throws IOException{
		if(Main.res.getNumberOfDeliveriesPerType().get("Regular delivery") != null) {
		int x =Main.res.getNumberOfDeliveriesPerType().get("Regular delivery");
		s.setText("regular delivery: "+Integer.toString(x));
		}
		else {                                         // check number if the express delivery and regular delivery
			s.setText("regular delivery: 0");
		}
		if(Main.res.getNumberOfDeliveriesPerType().get("Express delivery") != null) {
		int y = Main.res.getNumberOfDeliveriesPerType().get("Express delivery");
		d.setText("Express delivery" + Integer.toString(y));
		}
		else {
			d.setText("Express delivery: 0");
		}
	}
	public void gottoquery(ActionEvent event) throws IOException{
		Parent	root = FXMLLoader.load(getClass().getResource("query.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		

}
