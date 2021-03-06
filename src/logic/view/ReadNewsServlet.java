package logic.view;

import java.io.IOException;
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

import logic.addnews.AddNewsController;
import logic.bean.GeneralUserBean;
import logic.bean.NewsBean;
import logic.readnews.ReadNewsController;

@WebServlet("/ReadNewsServlet")
public class ReadNewsServlet extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(ReadNewsServlet.class.getName());
	private static final long serialVersionUID = 102831973239L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("news.jsp");
		GeneralUserBean gu = (GeneralUserBean) session.getAttribute("user");
		List<NewsBean> nb = null;
		
		session.setAttribute("origin", "ReadNewsServlet");
		session.setAttribute("userRole", gu.getRole());
		
		if(gu.getRole().equals("admin")) {
			AddNewsController anc = new AddNewsController();
			nb = anc.getNews();
		} else {
			ReadNewsController rnc = new ReadNewsController();
			nb = rnc.getNewsUser(gu);
		}
		
		request.setAttribute("news", nb);
		
		try {
			rd.forward(request, response);
		} catch(Exception e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
}
