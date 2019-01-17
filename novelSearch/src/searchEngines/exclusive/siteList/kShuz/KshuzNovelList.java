package searchEngines.exclusive.siteList.kShuz;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;

import searchEngines.crawl.abstracts.AbstractGetNovelList;
import searchEngines.entitys.Novel;
import searchEngines.utils.DetailProcess;
import searchEngines.utils.PraseRules;

public class KshuzNovelList extends AbstractGetNovelList {

	@Override
	public List<Novel> getsNovelList(String url, Integer maxTryTimes) {
		List<Novel> novels = new ArrayList<>();
		try {
			Elements uls = super.getGroup(url, maxTryTimes);
			for (int index = 1, size = uls.size() - 1; index < size; index++) {
				Elements lis = uls.get(index).getElementsByTag("li");
				Novel novel = new Novel();
				novel.setType(lis.get(1).text());
				novel.setName(lis.get(2).text());
				novel.setUrl(lis.get(2).getElementsByTag("a").first().absUrl("href"));
				
//				novel.setLastUpdateChapter(lis.get(2).text());
//				novel.setLastUpdateChapterUrl(lis.get(2).getElementsByTag("a").first().absUrl("href"));
				
				novel.setAuthor(lis.get(3).text());
				novel.setStatus(DetailProcess.getNovelStatus(lis.get(5).text()));
				novel.setLastUpdateTime(DetailProcess.getDate(lis.get(6).text(), "MM-dd").toString());
				PraseRules prase = new PraseRules();
				novel.setPlatformId(prase.getContext(url).get("title"));
				novels.add(novel);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return novels;
	}

}
