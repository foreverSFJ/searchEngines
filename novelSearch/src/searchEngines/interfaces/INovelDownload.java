package searchEngines.interfaces;

import searchEngines.entitys.Thread;


public interface INovelDownload {
	/**
	 * 通过多线程下载一本小说
	 * @param url 这个url是指某本小说的章节列表页面
	 * @return
	 */
	public String download(String url, Thread thread);
}
