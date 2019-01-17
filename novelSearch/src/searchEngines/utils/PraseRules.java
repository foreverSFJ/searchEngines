package searchEngines.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import searchEngines.VariableRef;
import searchEngines.exclusive.NovelSiteEnum;

public class PraseRules {
	
	/**
	 * 解析规则的存放格式，为map键值对
	 */
	private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_ENUM_MAP = new HashMap<>();
	
	private static final Map<String, Map<String, String>> CONTEXT_STR_MAP = new HashMap<>();
	
	/**
	 * 初始化
	 */
	public PraseRules(){
		//解析xml规则文件
		SAXReader reader = new SAXReader();
		try {
			//读取规则文件
			Document doc = reader.read(new File(VariableRef.RULE_XML_PATH));
			//解析规则文件,默认使用首页链接作为key
			analysisRule(doc,false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 解析用户指定配置文件地址
	 * @param string
	 */
	public void setConfigPath(String path) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(path));
			analysisRule(doc,false);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * 提取xml规则文件中的具体方法
	 * @param doc
	 */
	@SuppressWarnings("unchecked")
	public void analysisRule(Document doc,boolean bool ) {
		Element root = doc.getRootElement();
		List<Element> sites = root.elements("site");
		for (Element site : sites) {
			List<Element> subs = site.elements();
			
			Map<String, String> subMap = new HashMap<>();
			
			for (Element sub : subs) {
				String name = sub.getName();
				String text = sub.getTextTrim();
				subMap.put(name, text);
			}
			if(bool) {
				CONTEXT_ENUM_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
			}else {
				CONTEXT_STR_MAP.put(subMap.get("url"), subMap);
			}
		}
	}

	/**
	 * 通过枚举拿到对应网站的解析规则
	 */
	public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
		return CONTEXT_ENUM_MAP.get(novelSiteEnum);
	}
	
	/**
	 * 通过首页url拿到对应网站的解析规则
	 * @param url
	 * @return
	 */
	public Map<String, String> getContext(String url) {
		url = transposition(url);
		return CONTEXT_STR_MAP.get(url);
	}
	
	/**
	 * 处理传入的链接，只需要接收该链接指向的首页
	 * @param url
	 * @return
	 */
	public static String transposition(String url) {
		String newURL = "";
		if(url.indexOf(".com/") > 1) {
			newURL = url.substring(0, url.indexOf(".com/")+4);
		}else if(url.indexOf(".org/") > 1) {
			newURL = url.substring(0, url.indexOf(".org/")+4);
		}
		return newURL;
	}
	
}
