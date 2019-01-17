package searchEngines.exclusive.siteList.ddxs;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;

import searchEngines.crawl.abstracts.AbstractGetNovelList;
import searchEngines.entitys.Novel;
import searchEngines.utils.DetailProcess;
import searchEngines.utils.PraseRules;

public class DdxsNovelList extends AbstractGetNovelList {

	@Override
	public List<Novel> getsNovelList(String url, Integer maxTryTimes) {
		List<Novel> novels = new ArrayList<>();
		try {
			//顶点小说的小说列表结构是tr-td,每一本小说都是一个tr，小说的具体细节在td中，所以获取所有的tr就可以了
			Elements trs = super.getGroup(url, maxTryTimes);
			for (int index = 1, size = trs.size(); index < size; index++) {
				Elements tds = trs.get(index).getElementsByTag("td");
				Novel novel = new Novel();
				novel.setName(tds.first().getElementsByTag("a").get(1).text());
				String novelUrl = tds.first().getElementsByTag("a").get(1).absUrl("href");
				novel.setUrl(novelUrl);
				novel.setLastUpdateChapter(tds.get(1).text());
				novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
				novel.setAuthor(tds.get(2).text());
				novel.setLastUpdateTime(DetailProcess.getDate(tds.get(4).text(), "yy-MM-dd").toString());
				novel.setStatus(DetailProcess.getNovelStatus(tds.get(5).text()));
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
