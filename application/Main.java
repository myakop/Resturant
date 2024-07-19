package application;
	
import java.io.EOFException;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Dish;
import Model.Order;
import Model.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Restaurant res ;
	static Customer cust;
	 static ArrayList<Dish> saveorder = new ArrayList<>();
	 static Order order;
	 static Dish dish;
	 static Component comp;
	 static Delivery dev;
	 static DeliveryPerson devperson;
	 static DeliveryArea devarea;
	 static Cook cook;
	 
	@Override
	
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
public static void main(String[] args) {
		
		deSerialize();
		if(res==null) {
			serialize();
		}
		launch(args);
	}
	public static void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("Res.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			res=Restaurant.getInstance();
			out.writeObject(res);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in Rest.ser");
		}catch(EOFException ignoredFirstTime) {
			
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
	public static void deSerialize() {
		try {
			FileInputStream fileIn = new FileInputStream("Res.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			res=(Restaurant) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Successfully deSerialized the data");
		}catch(IOException i) {
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c) {
			System.out.println("class not found");
			c.printStackTrace();
			return;
		}
	}
	public static void update() {
		try {
			FileOutputStream fileOut = new FileOutputStream("Res.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Main.res);
			out.close();
			fileOut.close();
		}catch(IOException i) {
			i.printStackTrace();
			return;
		}
	}



	
	
	
	

	public static <T extends Node> T LoadFXML(Class<?> cls ,String path) throws IOException {
		return FXMLLoader.load(cls.getResource(path));
		
	}

}
