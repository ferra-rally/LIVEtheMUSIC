package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BackAdminController implements BackController {

	private String from;
	@FXML
	private Button buttonBack;

	private AdminGraphicChange agc;

	@FXML
	public void backAction(ActionEvent ev){

		if(this.from.equals("home")){
			this.agc.toHomepage(this.buttonBack.getScene());
		}else if(this.from.equals("news")) {
			//go to accept news
			this.agc.toNews(this.buttonBack.getScene());
		}

	}

	@Override
	public void init(String from, String searchString) {
		this.agc = AdminGraphicChange.getInstance();
		this.from = from;
	}

}
