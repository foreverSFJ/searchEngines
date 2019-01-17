package searchEngines.entitys;

import java.io.Serializable;
import java.util.UUID;

/**
 * 小说的实体
 * @author SFJ
 * @date   2016年10月14日
 */
public class Novel implements Serializable {
	private static final long serialVersionUID = 4834523404092493662L;
	private String id;							//小说的编号（索引）
	private String name;						//书名
	private String author;						//作者名 
	private String url;							//小说的链接 
	private String type;						//小说的类别：如武侠修真，都市言情 
	private String lastUpdateChapter;			//最后一章的章节名 
	private String lastUpdateChapterUrl;		//最后一章的链接 
	private String lastUpdateTime;				//小说最后更新的时间 
	private String status;						//小说的状态：1 连载 2 完结
	private String firstLetter;					//书名的首字母
	private String platformId;						//小说平台的id
	private String addTime;						//存储到本地数据库的时间 
	
	public Novel() {
		this.id = UUID.randomUUID().toString().replace("-", "");
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getLastUpdateChapter() {
		return lastUpdateChapter;
	}
	
	public void setLastUpdateChapter(String lastUpdateChapter) {
		this.lastUpdateChapter = lastUpdateChapter;
	}
	
	public String getLastUpdateChapterUrl() {
		return lastUpdateChapterUrl;
	}
	
	public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
		this.lastUpdateChapterUrl = lastUpdateChapterUrl;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFirstLetter() {
		return firstLetter;
	}
	
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	
	public String getPlatformId() {
		return platformId;
	}
	
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	@Override
	public String toString() {
		return "Novel [name=" + name + ", author=" + author + ", url=" + url + ", type=" + type + ", lastUpdateChapter="
				+ lastUpdateChapter + ", lastUpdateChapterUrl=" + lastUpdateChapterUrl + ", lastUpdateTime="
				+ lastUpdateTime + ", status=" + status + ", firstLetter=" + firstLetter + ", platformId=" + platformId
				+ ", addTime=" + addTime + "]";
	}
}
