package searchEngines.crawl.abstracts;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import searchEngines.VariableRef;
import searchEngines.entitys.Novel;
import searchEngines.exclusive.NovelSiteEnum;
import searchEngines.interfaces.INoveGetlList;
import searchEngines.utils.PraseRules;

public abstract class AbstractGetNovelList extends AbstractGetHTML implements INoveGetlList {

	protected Element nextPageElement;										//下一页
	protected String nextPage;												//下一页的url
	
	/**
	 * 默认的抓取方法，最多会尝试 {@link INovelSpider#MAX_TRY_TIMES} 次下载
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected Elements getGroup(String url) throws Exception {
		return getGroup(url, VariableRef.MAX_TRY_TIMES);
	}
	
	/**
	 * 以指定次数的形式去解析对应网页
	 * @param url
	 * @param maxTryTimes 如果为null， 则 默认是 {@link INovelSpider#MAX_TRY_TIMES}
	 * @return
	 * @throws Exception
	 */
	protected Elements getGroup(String url, Integer maxTryTimes) throws Exception {
		maxTryTimes = maxTryTimes == null ? VariableRef.MAX_TRY_TIMES : maxTryTimes;
		Elements groups = null;
		for (int i = 0; i < maxTryTimes ; i++) {
			try {
				String html = super.crawl(url);
				
				PraseRules prase = new PraseRules();
				Map<String, String> context = prase.getContext(url);
				
//				Map<String, String> context = PraseRules.getContext(NovelSiteEnum.getEnumByUrl(url));				
				
				String novelSelector = context.get("novel-selector");
				if (novelSelector == null) {
					throw new RuntimeException(context.get("url") + "链接是目前不支持抓取小说网站");
				}
				Document doc = Jsoup.parse(html);
				doc.setBaseUri(url);
				groups = doc.select(novelSelector);
				
				String nextPageSelector = context.get("novel-next-page-selector");
				if (nextPageSelector != null) {
					Elements nextPageElements = doc.select(nextPageSelector);
					nextPageElement = nextPageElements == null ? null : nextPageElements.first();
					
					if (nextPageElement != null) {
						nextPage = nextPageElement.absUrl("href");
					} else {
						nextPage = "";
					}
				}
				return groups;
			}catch (Exception e) {
			}
		}
		throw new RuntimeException(url + ",尝试了" + maxTryTimes + "次依然下载失败了！");
	}
	
	/**
	 * 判断是否有下一页
	 * @return
	 */
	@Override
	public boolean hasNext() {
		return !nextPage.isEmpty();
	}

	/**
	 * 返回下一页地址
	 * @return
	 */
	@Override
	public String next() {
		return nextPage;
	}
	
	/**
	 * 进行下一页迭代器
	 * @param firstPage
	 * @param maxTryTimes
	 * @return
	 */
	@Override
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
		nextPage = firstPage;
		return new NovelIterator(maxTryTimes);
	}
	
	/**
	 * 一个迭代器，专门用于对小说网站数据列表抓取
	 * @author SFJ
	 */
	private class NovelIterator implements Iterator<List<Novel>> {
		private Integer maxTryTimes;
		public NovelIterator(Integer maxTryTimes) {
			this.maxTryTimes = maxTryTimes;
		}
		@Override
		public boolean hasNext() {
			return AbstractGetNovelList.this.hasNext();
		}
		@Override
		public List<Novel> next() {
			List<Novel> novels = getsNovelList(nextPage, maxTryTimes);
			return novels;
		}
	}
}
