package searchEngines.interfaces;

import java.util.List;

import searchEngines.entitys.Chapter;

public interface IChapterGetList {

	/**
	 * 传入一本小说的url，返回对应小说的所有的章节列表
	 * @param url
	 * @return
	 */
	public List<Chapter> getChapterList(String url);
}
