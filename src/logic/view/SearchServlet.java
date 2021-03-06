package logic.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import logic.buyticket.BuyTicketController;
import logic.exceptions.NoArtistFoundException;
import logic.exceptions.NoMusicEventFoundException;
import logic.followartist.FollowArtistController;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet{
	private static final Logger logger = Logger.getLogger(SearchServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	private static final String SEARCHSTRING = "searchString";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		BuyTicketController btc = new BuyTicketController();
		FollowArtistController fac = new FollowArtistController();
		
		List<MusicEventBean> musicEvents = new ArrayList<>();
		List<ArtistBean> artists = new ArrayList<>();
		session.setAttribute("origin", "SearchServlet");
		
		String searchString = request.getParameter(SEARCHSTRING);
		
		if(searchString == null) {
			searchString = (String) request.getAttribute(SEARCHSTRING);
		}
		
		request.setAttribute(SEARCHSTRING, searchString);
		
		try {
			musicEvents = btc.getSearchMusicEvent(searchString);
			request.setAttribute("FoundMusicEvents", "Music Events");
		} catch (NoMusicEventFoundException e) {
			request.setAttribute("FoundMusicEvents", e.getMessage());
		}
		
		try {
			artists = fac.getSearchArtist(searchString);
			request.setAttribute("FoundArtists", "Artists");
		} catch (NoArtistFoundException e) {
			request.setAttribute("FoundArtists", e.getMessage());
		}
		
		session.setAttribute(SEARCHSTRING, searchString);
		session.setAttribute("musicEventList", musicEvents);
		
		session.setAttribute("artistList", artists);
		rd = request.getRequestDispatcher("searchResult.jsp");
		
		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
}
