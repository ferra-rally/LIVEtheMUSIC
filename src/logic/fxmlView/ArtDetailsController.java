package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.utils.SessionUser;
import java.io.File;
import logic.bean.ArtistBean;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import logic.followartist.FollowArtistController;
import logic.bean.GeneralUserBean;

public class ArtDetailsController {
	
	@FXML
	private VBox menuBar;
	@FXML 
	private ImageView profileImage;
	@FXML
	private AnchorPane backButton;
	@FXML
	private Label artName;
	@FXML 
	private Button followBtn;
	
	private  ArtistBean myArtist;
	
	private FollowArtistController controller;
	private GeneralUserBean sessionUser;
	private UserGraphicChange ucg;
	
	@FXML
	public void followAction(ActionEvent ev) {
		boolean isFoll=this.controller.isFollowing(this.sessionUser, this.myArtist);
		if(isFoll) {
			controller.unfollow(this.sessionUser,this.myArtist);
			this.followBtn.setText("Follow");
		}else {
			controller.follow(this.sessionUser,this.myArtist);
			this.followBtn.setText("Unfollow");
		}
	}
	
	public void init(ArtistBean ar,String from,String searchString) {
		this.ucg=UserGraphicChange.getInstance();
		this.myArtist=ar;
		
		//INIT CONTROLLER AND SESSIONUSER
		
		this.controller = new FollowArtistController();
		this.sessionUser = SessionUser.getInstance().getSession();
		
		//INIT BUTTON VAL
		
		boolean isFoll=this.controller.isFollowing(this.sessionUser, this.myArtist);
		if(isFoll==false) {
			this.followBtn.setText("Follow");
		}else {
			this.followBtn.setText("Unfollow");
		}
		
		//INIT MENU BAR
		/*MenuBarController mbc=new MenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
		loader.setController(mbc);
		this.menuBar.getChildren().add(loader.load());
		mbc.init("home");*/
		
		this.ucg.menuBar(this.menuBar,"home");
		
		//INIT LABEL NAMES
		this.artName.setText(this.myArtist.getBandName());
		
		//INIT BACK BUTTON
		
		/*BackUserController bc=new BackUserController();
		loader=new FXMLLoader(getClass().getResource("BackButton.fxml"));
		loader.setController(bc);
		bc.init(from,searchString);
		this.backButton.getChildren().add(loader.load());*/
		
		
		this.ucg.backButton(this.backButton, from, searchString);
		
		//INIT IMAGE
		String path=System.getProperty("user.home")+ File.separator
						+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
						+ "trunk" + File.separator + "WebContent" + File.separator
						+ "img" + File.separator + "profilePictures"+File.separator+this.myArtist.getProfilePicture();
				
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		this.profileImage.setImage(image);
		this.profileImage.setFitHeight(400);
		this.profileImage.setFitWidth(1200);
		
		
	}
}
