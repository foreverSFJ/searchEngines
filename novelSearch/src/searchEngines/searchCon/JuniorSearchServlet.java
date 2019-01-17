package searchEngines.searchCon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;

import searchEngines.VariableRef;

public class JuniorSearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4463531692925847236L;

	public JuniorSearchServlet() {
		super();
	}

	public void destroy() {
		super.destroy();

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 高级查询页面所对应Servlet，这里共用到了TermQuery、PrefixQuery、QueryParser、WildcardQuery、RangeQuery、BooleanQuery。

		request.setCharacterEncoding("UTF-8");
		// 内容里的第一个模块数据传入
		String content1 = request.getParameter("content1");
		String txt1 = request.getParameter("txt1");
		String boolean2 = request.getParameter("boolean2");
		String txt2 = request.getParameter("txt2");
		String fuzzy1 = request.getParameter("fuzzy1");
		// 内容里的第二个模块数据传入
		String boolean3 = request.getParameter("boolean3");// 第一个模块和第二个模块的布尔关系
		String content2 = request.getParameter("content2");
		String txt3 = request.getParameter("txt3");
		String boolean4 = request.getParameter("boolean4");
		String txt4 = request.getParameter("txt4");
		
		String fuzzy2 = request.getParameter("fuzzy2");
		
		if (content1.equals("1")) {
			content1 = "name";
		} else {
			content1 = "author";
		}
		
		if (content1.equals("1")) {
			content1 = "name";
		} else {
			content1 = "author";
		}
		if (content2.equals("1")) {
			content2 = "name";
		} else {
			content2 = "author";
		}

		IndexSearcher indexSearcher = new IndexSearcher(VariableRef.INDEX_PATH);
		BooleanQuery sumb = new BooleanQuery();// 总布尔查询条

		// 正文或者标题1用PrefixQuery前缀查询
		// 正文或者标题2用FuzzyQuery模糊查询（包含两个文本内容，两者关系用布尔查询表示）
		// 这里在用FuzzyQuery模糊查询查询时，如果用户选择精确，则调用QueryParser；如果用户选择模糊，则调用FuzzyQuery
		if (!txt1.isEmpty() || !txt2.isEmpty()) {
			BooleanQuery firstb = new BooleanQuery();
			if (!txt1.isEmpty()) {
				Term Term2 = new Term(content1, txt1);
				Query query2 = new PrefixQuery(Term2);
				firstb.add(query2, BooleanClause.Occur.MUST);
			}
			if (!txt2.isEmpty()) {
				Term Term3 = new Term(content1, txt2);
				//Query query3 = new PrefixQuery(Term3);
				//firstb.add(query3, BooleanClause.Occur.SHOULD);

				Query query3 = null;
				if (fuzzy1 == "2") {
					query3 = new FuzzyQuery(Term3);
				} else {
					QueryParser qp = new QueryParser(content1, new StandardAnalyzer());
					try {
						query3 = qp.parse(txt2);
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
				if (boolean2.equals("1")) {
					firstb.add(query3, BooleanClause.Occur.MUST);
				} else if (boolean2.equals("2")) {
					firstb.add(query3, BooleanClause.Occur.SHOULD);
				} else {
					firstb.add(query3, BooleanClause.Occur.MUST_NOT);
				}
			}
			sumb.add(firstb, BooleanClause.Occur.MUST);// 对应标题或正文1
		}
		// 第二个正文或者标题1用WildcardQuery通配符查询
		// 正文或者标题2用FuzzyQuery模糊查询（包含两个文本内容，两者关系用布尔查询表示）
		if (!txt3.isEmpty() || !txt4.isEmpty()) {
			BooleanQuery secondb = new BooleanQuery();
			if (!txt3.isEmpty()) {
				Term Term4 = new Term(content2, txt3);
				Query query4 = new WildcardQuery(Term4);
				secondb.add(query4, BooleanClause.Occur.MUST);
			}
			if (!txt4.isEmpty()) {
				Term Term5 = new Term(content2, txt4);
				Query query5 = null;
				if (fuzzy2.equals("2")) {
					query5 = new FuzzyQuery(Term5);
				} else {
					QueryParser qp = new QueryParser(content2, new StandardAnalyzer());
					try {
						query5 = qp.parse(txt4);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (boolean4.equals("1")) {
					secondb.add(query5, BooleanClause.Occur.MUST);
				} else if (boolean4.equals("2")) {
					secondb.add(query5, BooleanClause.Occur.SHOULD);
				} else {
					secondb.add(query5, BooleanClause.Occur.MUST_NOT);
				}
			}
			if (boolean3.equals("1")) {
				sumb.add(secondb, BooleanClause.Occur.MUST);// 对应标题或正文2
			} else if (boolean3.equals("2")) {
				sumb.add(secondb, BooleanClause.Occur.SHOULD);
			} else {
				sumb.add(secondb, BooleanClause.Occur.MUST_NOT);
			}
		}

		// 用布尔查询将以上查询条组合起来
		//System.out.println(sumb);
		Hits hits = indexSearcher.search(sumb);
		// hits是存放有序搜索结果指针的简单容器。检索结果是指匹配一个已知查询的一系列文档
		System.out.println("查询结果数：" + hits.length());
		System.out.println(indexSearcher.maxDoc());
		request.setAttribute("flag", "juniorSearch");
		request.setAttribute("hits", hits);
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	}
	
}
