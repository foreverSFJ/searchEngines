package searchEngines.searchCon;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searchEngines.crawl.DefaultGetChapterList;
import searchEngines.entitys.Chapter;
import searchEngines.interfaces.IChapterGetList;

public class ChapterListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1502137008009778337L;

	public ChapterListServlet() {
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
		String chapterListUrl = request.getParameter("url");
		IChapterGetList getList = new DefaultGetChapterList();
		List<Chapter> chapterList = getList.getChapterList(chapterListUrl);
		request.setAttribute("chapterList", chapterList);
		request.setAttribute("baseUrl", chapterListUrl);
		RequestDispatcher rd = request.getRequestDispatcher("chapterList.jsp");
		rd.forward(request, response);
	}
	
}
