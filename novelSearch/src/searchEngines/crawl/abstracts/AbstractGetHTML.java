package searchEngines.crawl.abstracts;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import searchEngines.exclusive.NovelSiteEnum;
import searchEngines.utils.PraseRules;

public class AbstractGetHTML {
	public String crawl(String url){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		String charset = getCharset(url);
		String html= "";
		try {
			CloseableHttpResponse reponses = httpClient.execute(httpget);
			//使用url获得字符
			html = EntityUtils.toString(reponses.getEntity(), charset);
			//使用枚举获得字符
//			html = EntityUtils.toString(reponses.getEntity(), PraseRules.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
			
			return html;
		} catch (Exception e) {
			System.out.println(charset);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取网页相应的编码
	 * @param url
	 * @return
	 */
	private String getCharset(String url) {
		PraseRules prase = new PraseRules();
		String charset = prase.getContext(url).get("charset");
		return charset;
	}
}
