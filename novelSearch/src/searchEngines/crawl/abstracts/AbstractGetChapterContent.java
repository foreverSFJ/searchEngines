package searchEngines.crawl.abstracts;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import searchEngines.entitys.ChapterContent;
import searchEngines.exclusive.NovelSiteEnum;
import searchEngines.interfaces.IChapterGetContent;
import searchEngines.utils.PraseRules;

public abstract class AbstractGetChapterContent extends AbstractGetHTML implements IChapterGetContent {
	
	@Override
	public ChapterContent getChapterContent(String url) {
		try {
			String html = super.crawl(url);
			
			//处理源网页代码中的字符
			html = html.replace("&nbsp;&nbsp;", "&emsp;").replace("<br />\r\n" + "<br />\r\n", "${line}").replace("<br/><br/>", "${line}").replace("<br/>", "${line}").replace("<br />", "${line}");
			
			Document doc = Jsoup.parse(html);
			doc.setBaseUri(url);
			
			PraseRules prase = new PraseRules();
			//使用url获得选择器
			Map<String, String> contexts = prase.getContext(url);
			//使用枚举获得 选择器
//			Map<String, String> contexts = PraseRules.getContext(NovelSiteEnum.getEnumByUrl(url));
			ChapterContent chapterContent = new ChapterContent();
			
			//获取章节名称
			String titleSelector = contexts.get("chapter-content-title-selector");
			String[] splits = titleSelector.split("\\,");
			splits = parseSelector(splits);
			chapterContent.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			
			//获取章节内容
			String contentSelector = contexts.get("chapter-content-content-selector");
			chapterContent.setContent(doc.select(contentSelector).first().text().replace("${line}", "\n"));
			
			//获取上一章地址
			String prevSelector = contexts.get("chapter-content-prev-selector");
			splits = prevSelector.split("\\,");
			splits = parseSelector(splits);
			chapterContent.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			//获取下一章地址
			String nextSelector = contexts.get("chapter-content-next-selector");
			splits = nextSelector.split("\\,");
			splits = parseSelector(splits);
			chapterContent.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			//返回这个章节实体
			return chapterContent;
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 处理下标
	 */
	private String[] parseSelector(String[] splits) {
		String[] newSplits = new String[2];
		if (splits.length == 1) {
			newSplits[0] = splits[0];
			newSplits[1] = "0";
			return newSplits;
		} else {
			return splits;
		}
	}
}
