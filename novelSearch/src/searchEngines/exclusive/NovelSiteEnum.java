package searchEngines.exclusive;

public enum NovelSiteEnum {

	Ddxs(1, "x23us.com"),
	Biqg(2, "biquyun.com"),
	Kshuz(3, "kanshuzhong.com"),
	Bxwx(4, "bxwx9.org");
	private int id;
	private String url;
	private NovelSiteEnum(int id, String url) {
		this.id = id;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static NovelSiteEnum getEnumById(int id) {
		switch (id) {
		case 1 : return Ddxs;
		case 2 : return Biqg;
		default : throw new RuntimeException("id=" + id + "是不被支持的小说网站");
		}
	}
	public static NovelSiteEnum getEnumByUrl(String url) {
		for (NovelSiteEnum novelSiteEnum : values()) {
			if (url.contains(novelSiteEnum.url)) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("url=" + url +  "是不被支持的小说网站");
	}
}
