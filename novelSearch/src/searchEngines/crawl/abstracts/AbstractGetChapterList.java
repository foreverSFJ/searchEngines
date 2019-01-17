package searchEngines.crawl.abstracts;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import searchEngines.entitys.Chapter;
import searchEngines.exclusive.NovelSiteEnum;
import searchEngines.interfaces.IChapterGetList;
import searchEngines.utils.PraseRules;

public class AbstractGetChapterList extends AbstractGetHTML implements IChapterGetList {

	@Override
	public List<Chapter> getChapterList(String url) {
		try {
			String html = super.crawl(url);
			Document doc = Jsoup.parse(html);
			doc.setBaseUri(url);
			PraseRules prase = new PraseRules();
			//默认使用url获取配置
			Elements es = doc.select(prase.getContext(url).get("chapter-list-selector"));
			//使用枚举获取配置
//			Elements es = doc.select(PraseRules.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			
			List<Chapter> chapterList = new ArrayList<>();
			for (Element e : es) {
				Chapter chapter = new Chapter();
				chapter.setTitle(e.text());
				chapter.setUrl(e.absUrl("href"));
				chapterList.add(chapter);
			}
			return chapterList;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
