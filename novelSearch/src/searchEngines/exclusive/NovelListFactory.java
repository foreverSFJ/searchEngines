package searchEngines.exclusive;

import searchEngines.exclusive.siteList.bxwx.BxwxNovelList;
import searchEngines.exclusive.siteList.ddxs.DdxsNovelList;
import searchEngines.exclusive.siteList.kShuz.KshuzNovelList;
import searchEngines.interfaces.INoveGetlList;

public final class NovelListFactory {
	private NovelListFactory() { }
	public static INoveGetlList getNovelList(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch (novelSiteEnum) {
		case Bxwx: return new BxwxNovelList();
		case Kshuz : return new KshuzNovelList();
		case Ddxs : return new DdxsNovelList();
		/*还可以接其他网站*/
		default:  throw new RuntimeException(url + "暂时不被支持");
	}
	}
}
