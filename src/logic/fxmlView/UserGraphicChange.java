package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import logic.login.*;
import logic.utils.*;
import logic.view.SearchServlet;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;
import logic.buyticket.*;

import java.util.List;
import java.util.logging.Level;

import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;

public class UserGraphicChange {
	
	private static UserGraphicChange myInstance=null;
	private static final Logger logger = Logger.getLogger(UserGraphicChange.class.getName());
	
	private UserGraphicChange(){}
	
	public static UserGraphicChange getInstance(){
		if(myInstance==null) {
			myInstance=new UserGraphicChange();
		}
		return myInstance;
	}
	
	public void toHomepage(Scene scene) {
		try {
			HomepageUserController huc=new HomepageUserController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("HomepageUser.fxml"));
			loader.setController(huc);
			scene.setRoot(loader.load());
			huc.init();
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	public void toSearchEv(Scene scene,String searchString) {
		try {
			SearchEventsArtistsController evc=new SearchEventsArtistsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchEventsArtists.fxml"));
			loader.setController(evc);
			scene.setRoot(loader.load());
			evc.init(searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	public void toLogin(Scene scene) {
		try {
			LoginViewController lvc=new LoginViewController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
			loader.setController(lvc);
			scene.setRoot(loader.load());
			lvc.init();
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}	
	}
	
	public void menuBar(VBox pos,String sel) {
		try {
			MenuBarController mbc=new MenuBarController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
			loader.setController(mbc);
			pos.getChildren().add(loader.load());
			mbc.init(sel);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void searchBar(AnchorPane ap,int kind) {
		try {
			SearchBarController sc=SearchBarControllerFactory.getInstance().creator(kind);
			FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchBar.fxml"));
			loader.setController(sc);
			ap.getChildren().add(loader.load());
			sc.init();
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void eventPreview(HBox box,MusicEventBean event,String from,String searchString) {
		try {
			EventController ev=new EventController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Event.fxml"));
			loader.setController(ev);
			box.getChildren().add(loader.load());
			ev.init(event, from, searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void artistPreview(HBox box, ArtistBean ar,String from,String searchString) {
		try {	
			ArtistController ac=new ArtistController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Artist.fxml"));
			loader.setController(ac);
			box.getChildren().add(loader.load());
			ac.init(ar, from, searchString);
			
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void backButton(AnchorPane ap,String from,String searchString) {
		try {
			BackController bc= BackControllerFactory.getInstance().creator(1);
			FXMLLoader loader=new FXMLLoader(getClass().getResource("BackButton.fxml"));
			loader.setController(bc);
			ap.getChildren().add(loader.load());
			bc.init(from, searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void evDeatilsButtons(HBox box,MusicEventBean event) {
		try {
			UserEvButtonsController btc=new UserEvButtonsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("UserEvDetButtons.fxml"));
			loader.setController(btc);
			box.getChildren().add(loader.load());
			btc.init(event);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void toArtistDetails(Scene scene,ArtistBean ar, String from,String searchString) {
		try {
			ArtDetailsController adc=new ArtDetailsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ArtDetails.fxml"));
			loader.setController(adc);
			scene.setRoot(loader.load());
			adc.init(ar,from,searchString);
			
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	public void toEventDetails(Scene scene,MusicEventBean meb,String from,String searchString ) {
		try {
			EvDetailsController edc=new EvDetailsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("EvDetails.fxml"));
			loader.setController(edc);
			scene.setRoot(loader.load());
			edc.init(meb, from, searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	
	
}
