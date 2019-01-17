package searchEngines.entitys;

public class Thread {

	public static final int DEFAULT_SIZE = 100;					//每个线程默认可以下载的最大章节数量
	public static final int DEFAULT_TRY_TIMES = 3;				//每个线程下载每一章所允许的最大尝试次数
	private String path;										//下载后的文件保存的基地址
	private int size;											//每个线程能够下载的最大章节数量
	private int tryTimes;										//自定义的每个线程下载每一章的尝试次数
	private boolean Ismerge;									//设置下载完成后是否合并
	
	public Thread() {
		this.size = DEFAULT_SIZE;
		this.tryTimes = DEFAULT_TRY_TIMES;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getTryTimes() {
		return tryTimes;
	}
	
	public void setTryTimes(int tryTimes) {
		this.tryTimes = tryTimes;
	}
	
	public static int getDefaultSize() {
		return DEFAULT_SIZE;
	}
	
	public static int getDefaultTryTimes() {
		return DEFAULT_TRY_TIMES;
	}
	
	public boolean isIsmerge() {
		return Ismerge;
	}
	
	public void setIsmerge(boolean ismerge) {
		Ismerge = ismerge;
	}
	
}
