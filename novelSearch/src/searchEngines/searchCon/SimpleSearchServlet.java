package searchEngines.searchCon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import searchEngines.VariableRef;

public class SimpleSearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2117064229731106440L;

	public SimpleSearchServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 简单查询页面对应Servlet，这里直接使用MultiFieldQueryParser，兼具多域查询功能和QueryParser的关键词转换功能。

		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		// 建立MultiFieldQueryParser多域查询
		String[] fields = { "name", "author","lastUpdateChapter","status", "platformId"};
		//索引分析
		MultiFieldQueryParser mp = new MultiFieldQueryParser(fields, new StandardAnalyzer());
		System.out.println(keyword);
		//keyword判空
		keyword.isEmpty();
		Query query = null;
		try {		
			query = mp.parse(keyword);
		// 未指明传入布尔关系时，默认为SHOULD，也就是说这里是指在标题或在正文里出现
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println(query);
		//读取索引文档
		IndexSearcher indexSearcher = new IndexSearcher(VariableRef.INDEX_PATH);
		//在索引中查找
		Hits hits = indexSearcher.search(query);
		// hits是存放有序搜索结果指针的简单容器。检索结果是指匹配一个已知查询的一系列文档
		System.out.println(hits.length());
		request.setAttribute("flag", "simpleSearch");
		request.setAttribute("hits", hits);
		request.setAttribute("keyword", keyword);
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	} 
	
}
