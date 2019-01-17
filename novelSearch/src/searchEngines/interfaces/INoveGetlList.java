package searchEngines.interfaces;

import java.util.Iterator;
import java.util.List;

import searchEngines.entitys.Novel;

public interface INoveGetlList {

	public List<Novel> getsNovelList(String url, Integer maxTryTimes);						//传入一个URL，返回小说实体链表
	public boolean hasNext();																//判断是否有下一页
	public String next();																	//下一页内容
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);			//迭代，下一页
	
}
