package searchEngines.exclusive;

import searchEngines.crawl.DefaultGetChapterList;
import searchEngines.exclusive.siteList.bxwx.BxwxChapterListSort;
import searchEngines.interfaces.IChapterGetList;

public class ChapterListFactory {
	private ChapterListFactory() {}
	
	/**
	 * 通过给定的url，返回一个实现了IChapterList接口的实现类,返回一本小说的章节列表
	 * @param url
	 * @return
	 */
	public static IChapterGetList getChapterList(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterGetList chapterList = null;
		switch (novelSiteEnum) {
			case Bxwx: chapterList = new BxwxChapterListSort(); break;
			case Ddxs:
			case Biqg:
			case Kshuz: chapterList = new DefaultGetChapterList(); break;
		}
		return chapterList;
	}
}
