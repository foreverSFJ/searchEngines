package searchEngines.searchCon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searchEngines.crawl.DefaultGetChapterContent;
import searchEngines.entitys.ChapterContent;
import searchEngines.interfaces.IChapterGetContent;

public class ChapterContenrServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4153729604391528047L;

	public ChapterContenrServlet() {
		super();
	}

	public void destroy() {
		super.destroy();

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String chapterUrl = request.getParameter("url");
		String baseUrl = request.getParameter("baseUrl");
		IChapterGetContent getContent = new DefaultGetChapterContent();
		ChapterContent chapter = getContent.getChapterContent(chapterUrl);
		request.setAttribute("chapter", chapter);
		request.setAttribute("baseUrl", baseUrl);
		RequestDispatcher rd = request.getRequestDispatcher("chapterContent.jsp");
		rd.forward(request, response);
	}
	
	
}
