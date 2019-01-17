package searchEngines.exclusive.siteList.bxwx;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import searchEngines.crawl.abstracts.AbstractGetChapterList;
import searchEngines.entitys.Chapter;

public class BxwxChapterListSort extends AbstractGetChapterList{

	public List<Chapter> getChapterList(String url) {
		List<Chapter> chapters = super.getChapterList(url);
		//对章节列表进行重新排序
		Collections.sort(chapters, new Comparator<Chapter>() {
			@Override
			public int compare(Chapter o1, Chapter o2) {
				String o1Url = o1.getUrl();
				String o2Url = o2.getUrl();
				String o1Index = o1Url.substring(o1Url.lastIndexOf('/') + 1, o1Url.lastIndexOf('.'));
				String o2Index = o2Url.substring(o2Url.lastIndexOf('/') + 1, o2Url.lastIndexOf('.'));
				return o1Index.compareTo(o2Index);
			}
		});
		return chapters;
	}
}
