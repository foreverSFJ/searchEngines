package test;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import searchEngines.crawl.DefaultGetChapterContent;
import searchEngines.crawl.DefaultGetChapterList;
import searchEngines.crawl.abstracts.AbstractGetHTML;
import searchEngines.entitys.Chapter;
import searchEngines.entitys.ChapterContent;
import searchEngines.entitys.Novel;
import searchEngines.exclusive.siteList.bxwx.BxwxNovelList;
import searchEngines.index.CreateIndex;
import searchEngines.interfaces.IChapterGetContent;
import searchEngines.interfaces.IChapterGetList;
import searchEngines.storage.Connector;
import searchEngines.utils.PraseRules;

public class Testcase {
	/**
	 * 测试是否可以获得配置信息
	 */
	@Test
	public void testPrase() {
		PraseRules prase = new PraseRules();
		System.out.println(prase.getContext("https://www.bxwx9.org/b/210/210426/45878315.html"));
		String cahrset = prase.getContext("https://www.bxwx9.org/b/210/210426/45878315.html").get("charset");
		System.out.println(cahrset);
	}
	
	/**
	 * 测试是否可以获得源代码
	 * @throws Exception
	 */
	@Test
	public void testHtml() throws Exception {
		AbstractGetHTML getHtml = new AbstractGetHTML();
		String html = getHtml.crawl("https://www.bxwx9.org/b/210/210426/45878315.html");
		System.out.println(html);
	}
	
	/**
	 * 测试数据库连接
	 */
	@Test
	public void testConn() {
		Connector cons = new Connector();
		@SuppressWarnings("static-access")
		Connection conn = cons.getconnect();
		System.out.println(conn);
	}
	
	/**
	 * 测试是否可以在数据库中插入一个页面中的小说列表
	 * @throws Exception
	 */
	@Test
	public void testInsert() throws Exception {
		BxwxNovelList bxwx = new BxwxNovelList();
		List<Novel> novelList = bxwx.getsNovelList("https://www.bxwx9.org/modules/article/index.php?fullflag=1&page=8", 4);
		Connector cor = new Connector();
		cor.insert(novelList);
	}
	
	/**
	 * 测试创建索引
	 */
	@Test
	public void testCreat() {
		CreateIndex creat = new CreateIndex();
		creat.createIndex();
	}
	
	/**
	 * 测试是否可以获得章节列表
	 */
	@Test
	public void testGetChapterList() {
		IChapterGetList getList = new DefaultGetChapterList();
		List<Chapter>  chapterList = getList.getChapterList("https://www.bxwx9.org/b/2/2396/");
		for (Chapter chapter : chapterList) {
			System.out.println(chapter);
		}
	}
	
	/**
	 * 测试是否可以获得章节内容
	 */
	@Test
	public void testGetChapterContent() {
		IChapterGetContent getContent = new DefaultGetChapterContent();
		ChapterContent content = getContent.getChapterContent("https://www.bxwx9.org/b/112/112268/41473322.html");
		System.out.println(content);
	}
}
