package searchEngines.exclusive;

import searchEngines.crawl.DefaultGetChapterContent;
import searchEngines.interfaces.IChapterGetContent;

public class ChapterContentFactory {
	
	private ChapterContentFactory() {}
	
	/**
	 * 通过给定的url拿到实现了IChapterContent的具体实现类,返回章节内容
	 * @param url
	 * @return
	 */
	public static IChapterGetContent getChapterContent(String url) {
		IChapterGetContent chapterContent = null;
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Ddxs :
		case Biqg :
		case Kshuz :
		case Bxwx :
			chapterContent = new DefaultGetChapterContent();
			break;
		}
		return chapterContent;
	}
}
