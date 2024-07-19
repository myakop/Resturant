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

public class getRevenueController {
	@FXML
	Label revenue ;
	
	public void getrevenue (ActionEvent event)throws IOException{ // get the revenue from express delivery
    double d  = Main.res.revenueFromExpressDeliveries();
    revenue.setText("the revenue is" + Double.toString(d) );
   

	}
	public void gottoquery(ActionEvent event) throws IOException{
		Parent	root = FXMLLoader.load(getClass().getResource("query.fxml"));
			Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene	 cn = new Scene(root);
				st.setScene(cn);
				st.show();
		}
		
}
