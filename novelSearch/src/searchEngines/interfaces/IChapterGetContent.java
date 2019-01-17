package searchEngines.interfaces;

import searchEngines.entitys.ChapterContent;

public interface IChapterGetContent {

	/**
	 * 传入一个章节的URL，返回对应的章节实体
	 * @param url
	 * @return
	 */
	public ChapterContent getChapterContent(String url);
}
