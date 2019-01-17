package searchEngines.interfaces;

/**
 * 用于处理器接口
 * @author SFJ
 *
 */
public interface IProcess {
	/**
	 * 一个处理方法，用于抓取元数据
	 * 多线程
	 */
	public void metadataCrawl();
}
